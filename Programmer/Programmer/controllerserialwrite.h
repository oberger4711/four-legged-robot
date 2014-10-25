#ifndef CONTROLLERSERIALWRITE_H
#define CONTROLLERSERIALWRITE_H

#include <QObject>
#include <serialport.h>

class ControllerSerialWrite : public QObject
{
    Q_OBJECT

private:
    SerialPort* serialPort;

public:
    explicit ControllerSerialWrite(SerialPort* serialPort);

signals:

public slots:
    void onSerialWrite(QString writeString);
};

#endif // CONTROLLERSERIALWRITE_H
