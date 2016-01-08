import processing.serial.*;
import controlP5.*;
import java.util.*;

ControlP5 cp5;
Serial myPort; 

//Topology Variables
int numControlPumps;
int numInputs = 10;
List<String> inputList = new ArrayList<String>();

//Routing Variables
int dropdownIndex = 0;
IntList destLevel = new IntList();
IntList currentLevel = new IntList();
IntList levelDifference = new IntList();
boolean greedyFail = false;

//Graph display variables
boolean error = false;
int missingOutput = 0;
DirectedGraph g = new DirectedGraph();
ArrayList<XposerNode> xposernodes = new ArrayList<XposerNode>();
ArrayList<Node> nodes = new ArrayList<Node>();


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

//Create arrays of control and flow pumps
ArrayList<Pump> controlPumps = new ArrayList<Pump>();
//Pump[] controlPumps; 
ArrayList<PumpFlow> flowPumps = new ArrayList<PumpFlow>();
//PumpFlow[] flowPumps = new PumpFlow[numInputs];

//Constants
boolean PUSH = true;
boolean PULL = false;

//Flags
boolean initControl = false;

//Display Variables
int margin = 50;
int textBoxWidth = 100;
int textBoxHeight = 35;
int buttonHeight = 35;
int buttonWidth = 100;

PImage cross, straight;
PFont font;

void setup() {
  fullScreen();
  font = createFont("AndaleMono-48.vlw",15, false);
  textFont(font);
  ControlFont cfont = new ControlFont(font,241);

  //Setup Serial Connection
  println(Serial.list());
  //myPort = new Serial(this, Serial.list()[7], 9600); // Open the port you are using at the rate you want:
  
  numControlPumps = 2 * numXposers(numInputs);

  for (int j = 0; j < numControlPumps; j++){
    controlPumps.add(new Pump(myPort, j)); 
  }
  for (int i = 0; i < numInputs; i++){
    flowPumps.add(new PumpFlow(myPort, i)); 
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
     
  cp5.addTab("graph")
     .setLabel(" Graph ")
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
     .setPosition(margin+3*buttonWidth,(height/4)+buttonHeight)
     .setSize(textBoxWidth,textBoxHeight)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setText("4000")
     .setLabel("Air Displacement (uL)")
     .setColorCaptionLabel(#FFFFFF)
     ;   
  cp5.getController("controlVolume")
     .getCaptionLabel()
     .setFont(font)
     .setSize(12)
     ;

 
  drawFlowSettings("settings", 100, 100);
  drawControlSettings("settings", 100, 210);

  cp5.addButton("startFlow")
     .setPosition(margin, height/16+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel(" Start Flow ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;

  cp5.addButton("test")
     .setPosition(margin,13*(height/16)+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel(" Test n ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;

  cp5.addButton("randomize")
     .setPosition(margin+4.5*buttonWidth,7*(height/16)+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel(" Random ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;

  cp5.addButton("fullSwap")
     .setPosition(margin+3*buttonWidth,7*(height/16)+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel(" full Swap ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;

  cp5.addButton("setOutput")
     .setPosition(margin+1.5*buttonWidth,7*(height/16)+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel(" Set Output ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;

  cp5.addButton("actuate")
     .setPosition(margin+1.5*buttonWidth,(height/4)+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel(" Actuate ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;

  cp5.addButton("route")
     .setPosition(margin,(height/4)+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel("Route")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;

  cp5.addButton("return")
     .setPosition(margin+4.5*buttonWidth,(height/4)+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel(" Return ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;

  cp5.addTextfield("output")
     .setPosition(margin, 7*(height/16)+buttonHeight)
     .setSize(textBoxWidth,textBoxHeight)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Select from dropdown menu")
     .setVisible(true)
     ;  
  cp5.getController("output")
     .getCaptionLabel()
     .setFont(font)
     .setSize(12)
     ;

  //for (int j = 0; j < numInputs; j++){
   //cp5.addTextfield("Output" + j)
     //.setPosition(width-margin, (3*(height/8)-(textBoxHeight/2)) + (textBoxHeight*2) * (j+1))
     //.setSize(25,textBoxHeight)
     //.setFont(font)
     //.setColor(color(50,50,50))
     //.setColorCursor(color(0,0,0))
     //.setText(str(j))
     //.setLabel("Output "+ j)
     //.setVisible(true)
     //.setId(j)
     //;   
  //}

    cp5.addTextfield("numInputsTxt")
     .setPosition(margin+buttonWidth*1.5, height/16+buttonHeight)
     .setSize(25,textBoxHeight)
     .setFont(font)
     .setColor(color(50,50,50))
     .setColorCursor(color(0,0,0))
     .setLabel("Number of Inputs")
     .setText(str(numInputs))
     .setColorCaptionLabel(#FFFFFF)
     //.setTab("inputs")
     ;     
  cp5.getController("numInputsTxt")
     .getCaptionLabel()
     .setFont(font)
     .setSize(12)
     ;

    cp5.addButton("numInputsBtn")
     .setPosition(margin+buttonWidth*2, height/16+buttonHeight)
     .setSize(buttonWidth,buttonHeight)
     .setLabel(" Enter ")
     .setColorBackground(0xff00ff00 + 0x88000000)
     .setColorForeground(0xff00ff00)
     .setOff()
     //.setTab("inputs")
     .getCaptionLabel()
     .setFont(font)
     .setSize(15)
     ;



  for (int i=0; i<numInputs; i++){
    inputList.add(i + ": " + i);
  }

  //List l = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
  /* add a ScrollableList, by default it behaves like a DropdownList */
  cp5.addScrollableList("dropdown")
     .setPosition(20+width/2, height/16)
     .setSize(width/2-40, height-40)
     .setBarHeight(buttonHeight)
     .setItemHeight(buttonHeight)
     .addItems(inputList)
     .setLabel("Routing: Input - Output")
     .setColorBackground(#00ffff + 0x88000000)
     //.getCaptionLabel()
     //.setFont(font)
     //.setSize(15)
     .setType(ScrollableList.DROPDOWN)
     .setOpen(true) 
     .getValueLabel()
     .setColor(0)
     .setFont(font)
     .setSize(15)
     ;
  cp5.getController("dropdown")
     .getCaptionLabel()
     .setColor(#FF0000)
     .setFont(font)
     .setSize(15)
     ; 
}

void draw() {
  background(0xffaaaaaa);
  textSize(20);
  fill(245);
  noStroke();
  rect(0 , 20, width, height-40);
  if (cp5.getTab("default").isActive()) guiDefault();
  if (cp5.getTab("settings").isActive()) guiSettings();
  if (cp5.getTab("graph").isActive()) guiGraph();
  if (error == true && cp5.getTab("default").isActive() == true) errorMessage();
}

void guiDefault() {  
  fill(0); 
  dispenseVolume = int(cp5.get(Textfield.class,"controlVolume").getText().trim());   
  fill(#0000ff + 0x88000000);
  rect(20, height/16, width/2-40, height/8, 10);
  fill(#ff0000 + 0x88000000);
  rect(20, 4*height/16, width/2-40, height/8, 10);
  fill(#ffff00 + 0x88000000);
  rect(20, 7*height/16, width/2-40, height/8, 10);
  fill(0);
  text("Flow", 20, height/16-5);
  text("Control", 20, 4*height/16-5);
  text("Routing", 20, 7*height/16-5);
  cp5.get(ScrollableList.class, "dropdown").open();
  //text("Flow Pumps:", 20, 1*(height/8) - textBoxHeight/2);
  //text("Control Pumps:", 20, 2*(height/8) - textBoxHeight/2); 
  //text("Flow Routing:", 20 , 3*(height/8));
  //for (int j = 0; j < numInputs; j++){
    //text(j, 25, 3*(height/8) + 7 + (textBoxHeight*2) * (j+1));
  //}
}

void actuate() {

}

void numInputsBtn(){
  controlPumps.clear();
  flowPumps.clear();
  inputList.clear();
  numInputs = int(cp5.get(Textfield.class, "numInputsTxt").getText().trim());
  numControlPumps = 2 * numXposers(numInputs);

  for (int i=0; i<numInputs; i++){
    inputList.add(i + ": " + i);
  }

  cp5.get(ScrollableList.class, "dropdown").setItems(inputList);

  //Create Pump Objects
  for (int j = 0; j < numControlPumps; j++){
    controlPumps.add(new Pump(myPort, j)); 
  }
  for (int i = 0; i < numInputs; i++){
    flowPumps.add(new PumpFlow(myPort, i)); 
  }
  updateSettings();

  cp5.get(Button.class, "numInputsBtn").setLabel("SAVED!");

  //for (int j = 0; j < numInputs; j++){
   //cp5.get(Textfield.class, "Output"+j).setVisible(true);
  //}
  //if (numInputs < 10) {
    //for (int i = numInputs; i < 10; i++){
      //cp5.get(Textfield.class, "Output"+i).setVisible(false);
    //}
  //}
  xposernodes.clear();
  nodes.clear();
  g.clearNodes();
  populateNodes();
  //makeGraph();
  makeGraphXposerNodes();
}

void errorMessage(){
  fill(#F57676);
  textSize(20);
  text("Missing Output Value " + missingOutput, 50, 10*height/16);
}

void startFlow() {
  if (cp5.get(Button.class,"startFlow").isOn()) {
    cp5.get(Button.class,"startFlow").setLabel(" Stop Flow")
       .setColorBackground(0xffff0000 + 0x88000000)
       .setColorForeground(0xffff0000);
    for (int j = 0; j < numInputs; j++){
      //println(flowPumps.get(j).motorPort);
      //PumpFlow disp = flowPumps.get(j);
      //pump.dispenseFlow(pwmSpeed);
      flowPumps.get(j).dispenseFlow(pwmSpeed);
    }
  }
  else {
    cp5.get(Button.class,"startFlow").setLabel(" Start Flow")
       .setColorBackground(0xff00ff00 + 0x88000000)
       .setColorForeground(0xff00ff00);
    for (int j = 0; j < flowPumps.size(); j++){
      //println(flowPumps.get(j).motorPort);
      flowPumps.get(j).dispenseFlow(0);
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

void guiGraph() {
  fill(0);
  textSize(10);
  g.draw();
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

void dropdown(int n) {
  /* request the selected item based on index n */
  //println(n, cp5.get(ScrollableList.class, "dropdown").getItem(n));
  
  /* here an item is stored as a Map  with the following key-value pairs:
   * name, the given name of the item
   * text, the given text of the item by default the same as name
   * value, the given value of the item, can be changed by using .getItem(n).put("value", "abc"); a value here is of type Object therefore can be anything
   * color, the given color of the item, how to change, see below
   * view, a customizable view, is of type CDrawable 
   */
    cp5.getController("output")
     .setLabel("Output Number " + n)
     ;
    cp5.getController("output")
     .getCaptionLabel()
     .setFont(font)
     .setSize(12)
     ;
  dropdownIndex = n;
 
   //CColor c = new CColor();
  //c.setBackground(color(255,0,0));
  //cp5.get(ScrollableList.class, "dropdown").getItem(n).put("color", c);
}

void setOutput() {
  inputList.set(dropdownIndex, dropdownIndex + ": " + int(cp5.get(Textfield.class,"output").getText().trim()));
  cp5.get(ScrollableList.class, "dropdown").setItems(inputList);
}

void fullSwap() {
  inputList.clear();

  for (int i=0; i<numInputs; i++){
    inputList.add(i + ": " + (numInputs-1-i));
  }

  cp5.get(ScrollableList.class, "dropdown").setItems(inputList);
}

void randomize() {
  int[] randomarray =  new int[numInputs];
  for(int t=0; t<randomarray.length; t++){
    randomarray[t]=t;
  }
  
  for (int k=0; k < numInputs; k++) {
    int temp = randomarray[k]; 
    // make rnd index x
    int x = (int)random(0, randomarray.length);    
    // overwrite value at current pos k with value at rnd index x
    randomarray[k]=randomarray[x];
    // finish swapping by giving the old value at pos k to the 
    // pos x. 
    randomarray[x]=temp;
  }

  inputList.clear();

  for (int i=0; i<numInputs; i++) {
    inputList.add(i + ": " + randomarray[i]);
  }

  cp5.get(ScrollableList.class, "dropdown").setItems(inputList);
}

void test() {
  inputList.clear();
  int[] testArray = new int[numInputs];
  for (int t=0; t<testArray.length; t++){
    testArray[t] = t;
  }
  testArray = sort(testArray);
  for (int i=0; i<numInputs; i++) {
    inputList.add(i + ": " + testArray[i]);
  }
  route();
  inputList.clear();
  testArray = reverse(testArray);
  for (int i=0; i<numInputs; i++) {
    inputList.add(i + ": " + testArray[i]);
  }
  route();
  inputList.clear();
}
