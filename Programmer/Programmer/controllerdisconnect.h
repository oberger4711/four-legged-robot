#ifndef CONTROLLERDISCONNECT_H
#define CONTROLLERDISCONNECT_H

#include <QObject>
#include "serialport.h"

class ControllerDisconnect : public QObject
{
    Q_OBJECT

private:
    SerialPort* serialPort;

public:
    explicit ControllerDisconnect(SerialPort* serialPort);

signals:

public slots:
    void disconnect();
};

#endif // CONTROLLERDISCONNECT_H
