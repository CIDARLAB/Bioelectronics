//Input spin rate in steps/second via the serial terminal

#include <AccelStepper.h>
AccelStepper stepper(1, 3, 2); //step pin = pin 3, direction pin = pin 2
float maxSpeed = 1000; 
unsigned long time;
unsigned long previousAccel = 0;
int interval = 100;  
String inputString = "";
char inChar = '0';
boolean stringComplete = false;  
boolean flag = false;

void setup()
{
  stepper.setMaxSpeed(0);
  stepper.setAcceleration(100.0);
  Serial.begin(9600);
  //Serial.println("Speed 0 to 2000");
}

void loop()
{
    Serial.println("0");
    stepper.setMaxSpeed(590);
    stepper.runToNewPosition(20000);
    Serial.println("1");
    inChar = '0';
    waitForPi();
    Serial.println("0");
    stepper.setMaxSpeed(1333);
    stepper.runToNewPosition(0);
    Serial.println("1");
    inChar = '0';
    waitForPi();
    Serial.println("0");
    stepper.setMaxSpeed(2106);
    stepper.runToNewPosition(20000);
    Serial.println("1");
    inChar = '0';
    waitForPi();
    Serial.println("0");
    stepper.setMaxSpeed(5110);
    stepper.runToNewPosition(0);
    Serial.println("1");
    inChar = '0';
}

void serialEvent() {
  while (Serial.available() > 0) {
    inChar = (char)Serial.read();
  }
}

void waitForPi() {
    while (inChar != '1') {  
      serialEvent();
    }
}

