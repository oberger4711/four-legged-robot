#ifndef SERIALPORT_H
#define SERIALPORT_H

#include <QObject>
#include <termios.h>
#include <iostream>
#include <unistd.h>
#include <pthread.h>
#include <stdio.h>
#include <fcntl.h>
#include <errno.h>
#include "serialportsettings.h"
#include "serialreaderthread.h"

class SerialPort : public QObject
{
    Q_OBJECT

private:
    int fdPort;
    struct SerialPortSettings* serialPortSettings;
    SerialReaderThread* serialReaderThread;

public:
    explicit SerialPort();
    void setSerialPortSettings(const struct SerialPortSettings& settings);
    void connect();
    void disconnect();

signals:
    void connectedChanged(bool established);
    void serialRead(QString readString);

public slots:
    void onSerialRead(QString readString);
    void onSerialReadThreadFinished();

};

#endif // SERIALPORT_H
