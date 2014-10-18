#include "CommandSerialPrint.h"

CommandSerialPrint::CommandSerialPrint(PeripheryContainer& periphery, const char* const printMessage) : Command(periphery)
{
	int messageLength = strlen(printMessage);
	message = new char[messageLength];
	strncpy(message, printMessage, messageLength + 1);
}

CommandSerialPrint::~CommandSerialPrint()
{
	delete message;
}

void CommandSerialPrint::beforeExecution(PeripheryContainer& peripheryContainer)
{

}

void CommandSerialPrint::onUpdate(unsigned int execTimeElapsedInMs, PeripheryContainer& periphery)
{
	Serial.println(message);
	finishExecution();
}
