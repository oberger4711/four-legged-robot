#include "serialport.h"

SerialPort::SerialPort() :
    fdPort(-1)
{
}

void SerialPort::setSerialPortSettings(const struct SerialPortSettings& settings)
{
    this->serialPortSettings = new struct SerialPortSettings(settings);
    serialReaderThread = NULL;
}

void SerialPort::connect()
{
    // Disconnect for the case there is already a connection.
    disconnect();
    bool success = true;
    if (serialPortSettings != NULL)
    {
        // Settings are set.
        // Open the fd.
        const char* portnameCharArray = serialPortSettings->portname.toStdString().c_str();
        fdPort = open(portnameCharArray, O_RDWR | O_NOCTTY | O_NDELAY);

        if (fdPort != -1)
        {
            // Successfully opened.
            // Apply settings to the fd.
            struct termios myTermios;
            tcgetattr(fdPort, &myTermios);
            cfsetispeed(&myTermios, serialPortSettings->baudrate);
            cfsetospeed(&myTermios, serialPortSettings->baudrate);
            if (tcsetattr(fdPort, TCSANOW, &myTermios) == 0)
            {
                // Applied settings correctly.
                success = true;

                // Start ReaderThread.
                serialReaderThread = new SerialReaderThread(fdPort);
                QObject::connect(serialReaderThread, SIGNAL(resultReady(QString)), this, SLOT(onSerialRead(QString)));
                QObject::connect(serialReaderThread, SIGNAL(finished()), this, SLOT(onSerialReadThreadFinished()));
                serialReaderThread->start();


            }
            else
            {
                // Could not set baudrate.
                perror(NULL);
                success = false;
            }
        }
        else
        {
            // Could not open.
            perror(NULL);
            success = false;
        }

    }
    else
    {
        // Settings are not set.
        std::cout << "Tried to connect with no settings set." << std::endl;
        success = false;
    }

    if (success)
    {
        std::cout << "Connection established." << std::endl;
    }
    else
    {
        std::cout << "Connection failed." << std::endl;
    }

    // Notify GUI.
    emit connectedChanged(success);
}

void SerialPort::onSerialRead(QString readString)
{
    emit serialRead(readString);
    std::cout << "Received " << readString.toStdString() << "." << std::endl;
}

void SerialPort::onSerialReadThreadFinished()
{
    // Restart the thread if needed.
    if (serialReaderThread != NULL)
    {
        if (fdPort != -1)
        {
            // Port still open. Continue reading.
            serialReaderThread->start();
        }
        else
        {
            serialReaderThread = NULL;
        }
    }
}

void SerialPort::disconnect()
{
    // Close the fd.
    if (fdPort != -1)
    {
        close(fdPort);
        fdPort = -1;
        std::cout << "Connection closed." << std::endl;
    }

    // Join the ReaderThread.
    if (serialReaderThread != NULL)
    {
        serialReaderThread->wait();
        serialReaderThread = NULL;
    }

    // Notify GUI.
    emit connectedChanged(false);
}
