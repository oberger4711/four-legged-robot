#include "controllerdisconnect.h"

ControllerDisconnect::ControllerDisconnect(SerialPort* serialPort) :
    serialPort(serialPort)
{
}

void ControllerDisconnect::disconnect()
{
    serialPort->disconnect();
}
