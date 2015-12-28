import processing.serial.*;
import controlP5.*;

ControlP5 cp5;
Serial myPort; 

//Graph display variables
DirectedGraph g=null;
int padding=30;
int numNodes = 12;
Node[] nodes = new Node[numNodes];
boolean inputFlag = false;


//Flow Pump variables
int pwmSpeed = 100;	//PWM duty cycle from 0-255

//Control Pump variables
// Hardware 
float syringeInnerD = 14.74; // mm
int syringeMaxCap = 10000; // uL
float pitch = 0.8; // mm/rev
float stepAngle = 1.8; // deg/step
int uStepsPerStep = 1; // uSteps/step
int motorMaxSpeed = 1500; // uSteps/s
// Flow Profile
float flowAcc = 2000; // uL/s/s
float flowSpeed = 300; // uL/s    
// Calculated Values - update when hardware parameters are updated
float ulPerUStep; // ul/ustep
float flowMaxSpeed; // ul/s
int uStepsAcc; // uL/s/s * uSteps/uL
int uStepsSpeed; // uL/s * uSteps/uL
int uStepsMove; // uL * uSteps/uL
int dispenseVolume;

//Topology Variables
int numControlPumps = 6;
int numInputs = 6;

//Create arrays of control and flow pumps
Pump[] controlPumps = new Pump[numControlPumps];
PumpFlow[] flowPumps = new PumpFlow[numInputs];

//Constants
boolean PUSH = true;
boolean PULL = false;

//Flags
boolean initControl = false;

//test variables
int count;

PImage cross, straight;
PFont font;

void setup() {
  size(800,650);
  font = createFont("AndaleMono-48.vlw",15, false);
  textFont(font);
  ControlFont cfont = new ControlFont(font,241);
  controlPumps = new Pump[numControlPumps];
  flowPumps = new PumpFlow[numInputs];

  //Setup Serial Connection
  println(Serial.list());
  myPort = new Serial(this, Serial.list()[7], 9600); // Open the port you are using at the rate you want:
  

  //Create Pump Objects
  for (int j = 0; j < controlPumps.length; j++){
    controlPumps[j] = new Pump(myPort, j); 
  }
  for (int i = 0; i < flowPumps.length; i++){
    flowPumps[i] = new PumpFlow(myPort, i); 
  }
  updateSettings();
    
  cp5 = new ControlP5(this);

  cp5.setColorForeground(0xffaaaaaa)
     .setColorBackground(0xffffffff)
     .setColorValueLabel(0xff00ff00)
     ;
     
  cp5.getTab("default")
     .setLabel(" Controller ")
     .setColorLabel(0xff000000)
     .setColorActive(0xffaaaaaa)
     .setWidth(width/3)
     ;  
     
  cp5.addTab("inputs")
     .setLabel(" Inputs ")
     .setColorLabel(0xff000000)
     .setColorActive(0xffaaaaaa)
     .setWidth(width/3)
     .activateEvent(true)
     ;

  cp5.addTab("settings")
     .setLabel(" Settings ")
     .setColorLabel(0xff000000)
     .setColorActive(0xffaaaaaa)
     .setWidth(width/3)
     .activateEvent(true)
     ;

   cp5.addTextfield("controlVolume")
     .setPosition(250,200)
     .setSize(100,35)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setText("4000")
     //.setVisible(false)
     .setLabel("Air Displacement (uL)")
     ;   
   
  drawFlowSettings("settings", 100, 100);
  drawControlSettings("settings", 100, 210);

  cp5.addButton("startFlow")
     .setPosition(150, 100)
     .setSize(60,35)
     .setLabel(" Start Flow ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     //.setVisible(false)
     .setOff()
     ;

  cp5.addButton("actuate")
     .setPosition(150,200)
     .setSize(75,35)
     .setLabel(" Actuate Control ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     //.setVisible(false)
     .setOff()
     ;

  cp5.addButton("return")
     .setPosition(375,200)
     .setSize(75,35)
     .setLabel(" Return to Origin")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     //.setVisible(false)
     .setOff()
     ;

  for (int j = 0; j < flowPumps.length; j++){
   cp5.addTextfield("Output" + j)
     .setPosition(width-100, 230 + 50 * (j+1))
     .setSize(25,25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setText(str(j))
     .setLabel("Output "+ j)
     .setVisible(true)
     .setId(j)
     ;   
  }

    cp5.addTextfield("numInputsTxt")
     .setPosition(width/2-50, height/2)
     .setSize(25,25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Number of Inputs")
     .setText(str(numInputs))
     .setTab("inputs")
     ;     

    cp5.addButton("numInputsBtn")
     .setPosition(width/2+50, height/2)
     .setSize(60,35)
     .setLabel(" Enter ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .setTab("inputs")
     ;
}

void draw() {
  background(0xffaaaaaa);
  fill(245);
  noStroke();
  rect(0 , 20, width, height-40);
  if (cp5.getTab("default").isActive()) guiDefault();
  if (cp5.getTab("settings").isActive()) guiSettings();
  if (cp5.getTab("inputs").isActive()) guiInputs();
}

void guiDefault() {  
  fill(0); 
  dispenseVolume = int(cp5.get(Textfield.class,"controlVolume").getText().trim());   
  text("Flow Pumps:", 20, 110);
  text("Control Pumps:", 20, 200, 125, 170); 
  text("Flow Routing:", 20 , 300);
  for (int j = 0; j < numInputs; j++){
    text(j, 150, 250 + 50 * (j+1));
  }
  makeGraph();
  g.setFlowAlgorithm(new ForceDirectedFlowAlgorithm());
  g.draw();
}

void actuate() {

}

void numInputsBtn(){
  numInputs = int(cp5.get(Textfield.class, "numInputsTxt").getText().trim());
  cp5.get(Button.class, "numInputsBtn").setLabel("SAVED!");
  for (int j = 0; j < numInputs; j++){
   cp5.get(Textfield.class, "Output"+j).setVisible(true);
  }
  if (numInputs < 6) {
    for (int i = numInputs; i < 6; i++){
      cp5.get(Textfield.class, "Output"+i).setVisible(false);
    }
  }
}

void startFlow() {
  if (cp5.get(Button.class,"startFlow").isOn()) {
    cp5.get(Button.class,"startFlow").setLabel(" Stop Flow")
       .setColorBackground(0xffff0000 + 0x88000000)
       .setColorForeground(0xffff0000);
    for (int j = 0; j < numInputs; j++){
      flowPumps[j].dispenseFlow(pwmSpeed);
    }
  }
  else {
    cp5.get(Button.class,"startFlow").setLabel(" Start Flow")
       .setColorBackground(0xff00ff00 + 0x88000000)
       .setColorForeground(0xff00ff00);
    for (int j = 0; j < numInputs; j++){
      flowPumps[j].dispenseFlow(0);
    }
  }
}


void shutDown(String message) {
  textSize(20);
  fill(#F57676);
  text(message, 50, 270);
  textSize(12);
  fill(0); 
  noLoop();
}

void guiInputs() {
  fill(0);
} 

void guiSettings() {
  fill(#0000ff + 0x88000000);
  rect(20, 60, width-40, 105, 10);
  fill(#ff0000 + 0x88000000);
  rect(20, 200, width-40, 105, 10);
  fill(0);
  text("Input Pumps (Peristaltic)", 20, 54);
  text("Control Pumps (Syringe)", 20, 194);
} 

void controlEvent(ControlEvent theControlEvent) {
  if (theControlEvent.isTab()) {
    cp5.get(Button.class, "FlowUpdate").setLabel("SET VALUES");
    cp5.get(Button.class, "ControlUpdate").setLabel("SET VALUES");
    cp5.get(Button.class, "numInputsBtn").setLabel("SET VALUES");
    pumpGetControlValues();
    pumpGetFlowValues();
  }
}

void FlowUpdate() {
  pumpSetFlowValues();
  cp5.get(Button.class, "FlowUpdate").setLabel("SAVED!");
}

void ControlUpdate() {
  pumpSetControlValues();
  cp5.get(Button.class, "ControlUpdate").setLabel("SAVED!");
}

void pumpGetFlowValues() {  
    cp5.get(Textfield.class, "PWM").setText(str(pwmSpeed));  
}

void pumpGetControlValues() {  
    cp5.get(Textfield.class, "ID").setText(str(syringeInnerD));  
    cp5.get(Textfield.class, "MaxCap").setText(str(syringeMaxCap/1000));  
    cp5.get(Textfield.class, "Pitch").setText(str(pitch));  
    cp5.get(Textfield.class, "StepAngle").setText(str(stepAngle));  
    cp5.get(Textfield.class, "MicrostepsPerStep").setText(str(uStepsPerStep));  
    cp5.get(Textfield.class, "MotorMaxSpeed").setText(str(motorMaxSpeed));
    cp5.get(Textfield.class, "FlowAcc").setText(str(flowAcc));  
    cp5.get(Textfield.class, "FlowSpeed").setText(str(flowSpeed));
}

void updateSettings() {      
        // Calculated Values - update when hardware parameters are updated
        ulPerUStep = syringeInnerD * syringeInnerD * 3.14159 / 4 * pitch / (360 * uStepsPerStep / stepAngle); // mm^3 /(deg * usteps/step * steps/deg)
        //flowMaxSpeed = motorMaxSpeed * ulPerUStep; // uSteps/s * uL/uSteps
        flowMaxSpeed = motorMaxSpeed; // Just use the max motor speed for the speed of rotation for each stepper 
	uStepsAcc = (int)(flowAcc / ulPerUStep); // uL/s/s * uSteps/uL
	uStepsSpeed = (int)(flowSpeed / ulPerUStep); // uL/s * uSteps/uL
	uStepsMove = (int)(dispenseVolume / ulPerUStep); // uL * uSteps/uL
}

void pumpSetFlowValues() {
  setFlowHardware(int(cp5.get(Textfield.class, "PWM").getText().trim()) );
  printFlowValues();
}

void pumpSetControlValues() {
  setHardware( float(cp5.get(Textfield.class, "ID").getText().trim()),  
                 int(cp5.get(Textfield.class, "MaxCap").getText().trim())*1000,  
                 float(cp5.get(Textfield.class, "Pitch").getText().trim()),  
                 float(cp5.get(Textfield.class, "StepAngle").getText().trim()),  
                 int(cp5.get(Textfield.class, "MicrostepsPerStep").getText().trim()),  
                 int(cp5.get(Textfield.class, "MotorMaxSpeed").getText().trim()) );
  setFlowProfile( float(cp5.get(Textfield.class, "FlowAcc").getText().trim()),  
                    float(cp5.get(Textfield.class, "FlowSpeed").getText().trim()) );
  printValues();
}

void drawFlowSettings(String tabName, int x, int y) {  
  cp5.addTextfield("PWM")
     .setPosition(x, y)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("PWM Duty Cycle (0-255)")
     .setTab(tabName)  
     ;   
  cp5.addButton("FlowUpdate")
     .setPosition(x+125,y)
     .setSize(60,25)
     .setLabel("SET VALUES")
     .setColorBackground(0xffdddddd)
     .setTab(tabName)
     ;
}

void drawControlSettings(String tabName, int x, int y) {  
  cp5.addTextfield("ID")
     .setPosition(x, y)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Syringe Inner Diameter (mm)")
     .setTab(tabName)  
     ;  
  cp5.addTextfield("MaxCap")
     .setPosition(x + 150, y)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Syringe Capacity (ml)")
     .setTab(tabName)  
     ;  
  cp5.addTextfield("FlowAcc")
     .setPosition(x + 300, y)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Flow Acceleration (uL/s/s)")
     .setTab(tabName)  
     ;
  cp5.addTextfield("FlowSpeed")
     .setPosition(x + 450, y)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Flow Velocity (uL/s)")
     .setTab(tabName)  
     ;  

  cp5.addTextfield("Pitch")
     .setPosition(x, y + 50)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Pitch (mm/rev)")
     .setTab(tabName)  
     ;  
  cp5.addTextfield("MotorMaxSpeed")
     .setPosition(x + 150, y + 50)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Maximum Motor Speed (uSteps/s)")
     .setTab(tabName)  
     ; 
  cp5.addTextfield("StepAngle")
     .setPosition(x + 300, y + 50)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Step Angle (deg/step)")
     .setTab(tabName)  
     ;  
  cp5.addTextfield("MicrostepsPerStep")
     .setPosition(x + 450, y + 50)
     .setSize(100, 25)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Microsteps per Step")
     .setTab(tabName)  
     ;

  cp5.addButton("ControlUpdate")
     .setPosition(x+580,y+25)
     .setSize(60,25)
     .setLabel("SET VALUES")
     .setColorBackground(0xffdddddd)
     .setTab(tabName)
     ;
}

void printValues() {
        println("Syringe Inner Diameter:", syringeInnerD, "mm");
        println("Syringe Max Capacity:", syringeMaxCap, "uL");
        println("Pitch:", pitch, "mm/rev");
        println("Step Angle:", stepAngle, "deg/step");
        println("Microsteps Per Step:", uStepsPerStep, "uSteps/step");
        println("Motor Max Speed:", motorMaxSpeed, "uSteps/s");
        println("Flow Acceleration:", flowAcc, "uL/s/s");
        println("Flow Speed:", flowSpeed, "uL/s ");   
        println("Microliters Per Microstep:", ulPerUStep, "ul/ustep");
        println("Flow Max Speed:", flowMaxSpeed, "ul/s");
        println("Microsteps for acceleration:", uStepsAcc, "uL/s/s * uSteps/uL");
        println("Microsteps for speed:", uStepsSpeed, "uL/s * uSteps/uL");
        println("Microsteps for motion:", uStepsMove, "uL * uSteps/uL");
}

void setFlowProfile(float acc, float speed) {
        flowAcc = acc > 0 ? acc : flowAcc;
        flowSpeed = (speed > 0 && speed < flowMaxSpeed) ? speed : flowMaxSpeed; // Can't set the speed to higher than the motorMaxSpeed
}

void printFlowValues() {
  println("PWM speed: ", pwmSpeed);
}

void setHardware(float temp_syringeInnerD, int temp_syringeMaxCap, float temp_pitch, float temp_stepAngle, int temp_uStepsPerStep, int temp_motorMaxSpeed) { 
    syringeInnerD = temp_syringeInnerD > 0 ? temp_syringeInnerD : syringeInnerD; // Only update if input is a positive number
    syringeMaxCap = temp_syringeMaxCap > 0 ? temp_syringeMaxCap : syringeMaxCap;
    pitch = temp_pitch > 0 ? temp_pitch : pitch;
    stepAngle = temp_stepAngle > 0 ? temp_stepAngle : stepAngle;
    uStepsPerStep = temp_uStepsPerStep > 0 ? temp_uStepsPerStep : uStepsPerStep;
    motorMaxSpeed = temp_motorMaxSpeed > 0 ? temp_motorMaxSpeed : motorMaxSpeed;
    updateSettings();
}

void setFlowHardware(int temp_pwmSpeed) { 
  pwmSpeed = ((temp_pwmSpeed >= 0) && (temp_pwmSpeed <= 255))  ? temp_pwmSpeed : pwmSpeed; // Only update if input is a positive number
}

void makeGraph()
{
  // define a graph
  g = new DirectedGraph();

  // define some nodes
  //Node n0 = new Node("0",width,padding);
  //Node n1 = new Node("1",padding,padding);
  //Node n2 = new Node("2",padding,height-padding);
  //Node n3 = new Node("3",width-padding,height-padding);
  //Node n4 = new Node("4",width-padding,padding);
  //Node n5 = new Node("5",width-3*padding,height-2*padding);
  //Node n7 = new Node("6",width-3*padding,2*padding);
  //Node n8 = new Node("6",width-3*padding,2*padding);
  //Node n9 = new Node("6",width-3*padding,2*padding);
  //Node n10 = new Node("6",width-3*padding,2*padding);
  //Node n11 = new Node("6",width-3*padding,2*padding);

  nodes[0] = new Node("0",width/6, 300);
  nodes[1] = new Node("1",2*(width/6), 300);
  nodes[2] = new Node("2",150 + 4*(width/6), 300);
  nodes[3] = new Node("3",150 + 5*(width/6), 300);
  nodes[4] = new Node("4",150 + (width/6),350);
  nodes[5] = new Node("5",150 + 2*(width/6),350);
  nodes[6] = new Node("6",150 + 3*(width/6),350);
  nodes[7] = new Node("7",150 + 4*(width/6),350);
  nodes[8] = new Node("8",150 + 5*(width/6),350);
  nodes[9] = new Node("9",150 + (width/6), 400);
  nodes[10] = new Node("10",150 + 3*(width/6), 400);
  nodes[11] = new Node("11",150 + 5*(width/6), 400);

  // add nodes to graph
  //g.addNode(n1);
  //g.addNode(n2);
  //g.addNode(n3);
  //g.addNode(n4);
  //g.addNode(n5);
  //g.addNode(n6);

  for (int j = 0; j< nodes.length; j++){
    g.addNode(nodes[j]);
  }

  // link nodes
  //g.linkNodes(n1,n2);
  //g.linkNodes(n2,n3);
  //g.linkNodes(n3,n4);
  //g.linkNodes(n4,n1);
  //g.linkNodes(n1,n3);
  //g.linkNodes(n2,n4);
  //g.linkNodes(n5,n6);
  //g.linkNodes(n1,n6);
  //g.linkNodes(n2,n5);
  g.linkNodes(nodes[0], nodes[1]);
  g.linkNodes(nodes[1], nodes[2]);
  g.linkNodes(nodes[1], nodes[6]);
  g.linkNodes(nodes[2], nodes[3]);
  g.linkNodes(nodes[2], nodes[8]);
  g.linkNodes(nodes[4], nodes[5]);
  g.linkNodes(nodes[5], nodes[2]);
  g.linkNodes(nodes[5], nodes[6]);
  g.linkNodes(nodes[6], nodes[7]);
  g.linkNodes(nodes[6], nodes[11]);
  g.linkNodes(nodes[7], nodes[3]);
  g.linkNodes(nodes[7], nodes[8]);
  g.linkNodes(nodes[9], nodes[10]);
  g.linkNodes(nodes[10], nodes[7]);
  g.linkNodes(nodes[10], nodes[11]);
}

