#ifndef SERIALREADERTHREAD_H
#define SERIALREADERTHREAD_H

#include <QThread>
#include<QAtomicInt>
#include <unistd.h>
#include <iostream>
#include <string.h>

#define BUFFER_SIZE 255

class SerialReaderThread : public QThread
{
    Q_OBJECT

    void run();

private:
    QAtomicInt keepRunning;
    int fdSerialPort;

public:
    explicit SerialReaderThread(int fdSerialPort);
    void start(Priority priority = InheritPriority);
    void stop();

signals:
    void resultReady(QString readString);

public slots:

};

#endif // SERIALREADERTHREAD_H
