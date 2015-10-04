#include <Wire.h>
#include <Adafruit_PWMServoDriver.h>

#include "Leg.h"
#include "PeripheryContainer.h"

#include "Command.h"
#include "CommandMove.h"
#include "CommandProcedure.h"
#include "CommandSerialPrint.h"
#include "MovementReverse.h"
#include "MovementWalkForward.h"
#include "MovementTurnLeft.h"
#include "MovementStandUp.h"

Adafruit_PWMServoDriver servoShield = Adafruit_PWMServoDriver();

void setup()
{
	Serial.begin(9600);
	servoShield.begin();
	servoShield.setPWMFreq(60);
}

void initPeriphery(PeripheryContainer& periphery)
{
	// Back Right
	periphery.legBR = new Leg(new Servo(&servoShield, 0, true, -5), new Servo(&servoShield, 1, false, 0));

	// Back Left
	periphery.legBL = new Leg(new Servo(&servoShield, 2, false, -10), new Servo(&servoShield, 3, true, 0));

	// Front Right
	periphery.legFR = new Leg(new Servo(&servoShield, 4, false, 10), new Servo(&servoShield, 5, false, 0));

	// Front Left
	periphery.legFL = new Leg(new Servo(&servoShield, 6, true, -8), new Servo(&servoShield, 7, true, -10));
}

void returnLegsToHome(PeripheryContainer& periphery)
{
    periphery.legBL->setRotationX(ANGLE_X_LAY_DOWN);
    periphery.legBL->setRotationY(ANGLE_Y_STAND);

    periphery.legBR->setRotationX(ANGLE_X_LAY_DOWN);
    periphery.legBR->setRotationY(ANGLE_Y_STAND);

    periphery.legFL->setRotationX(ANGLE_X_LAY_DOWN);
    periphery.legFL->setRotationY(ANGLE_Y_STAND);

    periphery.legFR->setRotationX(ANGLE_X_LAY_DOWN);
    periphery.legFR->setRotationY(ANGLE_Y_STAND);
}

void executeCommand(Command& command)
{
    command.prepareExecution();
    do
    {
        command.update();
    }
    while (!command.isComplete());
}

void loop()
{
    Serial.println("Begin of program.");
    
	Serial.println("Initialize legs...");

	PeripheryContainer periphery;
	initPeriphery(periphery);
	returnLegsToHome(periphery);

    // Create commands
	Movement* mvWalkForward = new MovementWalkForward();
	Movement* mvWalkBackward = new MovementReverse(mvWalkForward);
	Movement* mvTurnLeft = new MovementTurnLeft();
	Movement* mvTurnRight = new MovementReverse(mvTurnLeft);
	Movement* mvStandUp = new MovementStandUp();
	Movement* mvLayDown = new MovementReverse(mvStandUp);
	CommandSerialPrint cmdPrint(periphery, "Executing Commands...");
	CommandMove cmdWalkForward(periphery, mvWalkForward);
	CommandMove cmdWalkBackward(periphery, mvWalkBackward);
	CommandMove cmdTurnLeft(periphery, mvTurnLeft);
	CommandMove cmdTurnRight(periphery, mvTurnRight);
	CommandMove cmdStandUp(periphery, mvStandUp);
	CommandMove cmdLayDown(periphery, mvLayDown);

	delay(5000);
	cmdPrint.prepareExecution();
	cmdPrint.update();

	executeCommand(cmdStandUp);
	delay(2000);
	
	for (int i = 0; i < 6; i++)
	{
		executeCommand(cmdWalkForward);
	}
	/*
	delay(200);

	for (int i = 0; i < 4; i++)
	{
		executeCommand(cmdTurnLeft);
	}
	delay(200);

	for (int i = 0; i < 4; i++)
	{
		executeCommand(cmdTurnRight);
	}
	delay(200);

	for (int i = 0; i < 2; i++)
	{
		executeCommand(cmdWalkBackward);
	}
	delay(200);
	*/

	executeCommand(cmdLayDown);
	Serial.println("End of program.");

	delete mvLayDown;
	delete mvStandUp;
	delete mvTurnRight;
	delete mvTurnLeft;
	delete mvWalkBackward;
	delete mvWalkForward;

	while (true);
}
