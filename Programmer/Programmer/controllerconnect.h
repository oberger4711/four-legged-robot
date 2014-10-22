#ifndef CONTROLLERCONNECT_H
#define CONTROLLERCONNECT_H

#include <QObject>
#include <iostream>
#include "serialport.h"
#include "serialportsettings.h"

class ControllerConnect : public QObject
{
    Q_OBJECT

private:
    SerialPort* serialPort;

public:
    explicit ControllerConnect(SerialPort* const serialPort);

public slots:
    void connect(const struct SerialPortSettings settings);
};

#endif // CONTROLLERCONNECT_H
