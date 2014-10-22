#ifndef SERIALPORT_H
#define SERIALPORT_H

#include <QObject>
#include <termios.h>
#include <iostream>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <errno.h>
#include "serialportsettings.h"

class SerialPort : public QObject
{
    Q_OBJECT

private:
    struct SerialPortSettings* serialPortSettings;
    int fdPort;

public:
    explicit SerialPort();
    void setSerialPortSettings(const struct SerialPortSettings& settings);
    void connect();
    void disconnect();

signals:
    void onConnectedChanged(bool established);

};

#endif // SERIALPORT_H
