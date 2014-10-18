#ifndef CommandSerialPrint_h
#define CommandSerialPrint_h

#include "Command.h"
#include <string.h>

class CommandSerialPrint : public Command
{
private:
	char* message;

protected:
	void beforeExecution(PeripheryContainer& peripheryContainer);
	void onUpdate(unsigned int timeElapsedInMs, PeripheryContainer& periphery);

public:
	CommandSerialPrint(PeripheryContainer& periphery, const char* const message);
	~CommandSerialPrint();
};

#endif
