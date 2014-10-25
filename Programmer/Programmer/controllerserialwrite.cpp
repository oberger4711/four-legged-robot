#include "controllerserialwrite.h"

ControllerSerialWrite::ControllerSerialWrite(SerialPort* serialPort) :
    serialPort(serialPort)
{
}

void ControllerSerialWrite::onSerialWrite(QString writeString)
{
    serialPort->serialWrite(writeString);
}
