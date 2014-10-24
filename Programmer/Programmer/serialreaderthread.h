#ifndef SERIALREADERTHREAD_H
#define SERIALREADERTHREAD_H

#include <QThread>
#include <unistd.h>
#include <iostream>
#include <string.h>

#define BUFFER_SIZE 255

class SerialReaderThread : public QThread
{
    Q_OBJECT

    void run();

private:
    int fdSerialPort;

public:
    explicit SerialReaderThread(int fdSerialPort);

signals:
    void resultReady(QString readString);

public slots:

};

#endif // SERIALREADERTHREAD_H
