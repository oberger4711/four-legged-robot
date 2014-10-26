#include "serialreaderthread.h"

SerialReaderThread::SerialReaderThread(int fdSerialPort) :
    fdSerialPort(fdSerialPort)
{
}

void SerialReaderThread::stop()
{
    this->keepRunning.store(false);
}

void SerialReaderThread::start(Priority priority)
{
    this->keepRunning.store(true);
    QThread::start(priority);
}

void SerialReaderThread::run()
{
    char readBuffer[BUFFER_SIZE];
    while (keepRunning.load())
    {
        ssize_t receivedBytes = read(fdSerialPort, readBuffer, BUFFER_SIZE); // TODO: Resolve deadlock

        if (receivedBytes > 0)
        {
            // Received string from the port.
            std::string receivedString(readBuffer, receivedBytes);

            emit resultReady(QString::fromStdString(receivedString));
        }
    }
}
