#include "controllerconnect.h"

ControllerConnect::ControllerConnect(SerialPort* const serialPort) :
    serialPort(serialPort)
{
}

void ControllerConnect::connect(const struct SerialPortSettings settings)
{
    std::cout << "Try connect to " << settings.portname.toStdString() << "." << std::endl;
    serialPort->setSerialPortSettings(settings);
    serialPort->connect();
}
