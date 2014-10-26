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
            // Get settings from the file.
            struct termios myTermios;
            if (tcgetattr(fdPort, &myTermios) == 0)
            {
                // Reset flags. See http://stackoverflow.com/questions/9165324/reading-from-serial-port-on-ubuntu-linux-11-10-in-c?rq=1
                myTermios.c_cflag = serialPortSettings->baudrate;
                myTermios.c_oflag = 0;
                myTermios.c_lflag = 0;
                // Enable receiver and local control options.
                myTermios.c_cflag |= CREAD | CLOCAL;
                // Set 8-N-1 control options.
                myTermios.c_cflag &= ~PARENB;
                myTermios.c_cflag &= ~CSTOPB;
                myTermios.c_cflag &= ~CSIZE;
                myTermios.c_cflag |= CS8;
                // Set no flow control option.
                myTermios.c_cflag &= ~CRTSCTS;
                // Disable software flow input option.
                myTermios.c_iflag &= ~(IXON | IXOFF | IXANY);
                // Enable ignore CR input option.
                myTermios.c_iflag |= IGNCR;
                // Set raw output option.
                myTermios.c_oflag &= ~OPOST;
                // Enable map LF to LFCR.
                myTermios.c_oflag |= ONLCR;
                // Set baudrate.
                cfsetispeed(&myTermios, serialPortSettings->baudrate);
                cfsetospeed(&myTermios, serialPortSettings->baudrate);

                // Try applying the settings.
                if (tcsetattr(fdPort, TCSANOW, &myTermios) == 0)
                {
                    // Applied settings correctly.
                    success = true;

                    // Start ReaderThread.
                    serialReaderThread = new SerialReaderThread(fdPort);
                    QObject::connect(serialReaderThread, SIGNAL(resultReady(QString)), this, SLOT(onSerialRead(QString)));
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
                // Could not get settings.
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
}

void SerialPort::serialWrite(QString writeString)
{
    if (!writeString.isEmpty())
    {
        if (fdPort != -1)
        {
            const char* writeStringCharArray = writeString.toStdString().data();
            ssize_t writtenBytes = 0;
            int firstByteToWrite = 0;
            // Write the whole string to the device.
            do
            {
                writtenBytes += write(fdPort, (writeStringCharArray + firstByteToWrite), writeString.size() - writtenBytes);
            }
            while (0 <= writtenBytes && writtenBytes < writeString.size());

            if (writtenBytes < 0)
            {
                // Could not write anything.
                disconnect();
            }
        }
    }
}

void SerialPort::disconnect()
{
    // Stop the ReaderThread if it is still running.
    if (serialReaderThread != NULL)
    {
        serialReaderThread->stop();
        serialReaderThread->wait();
        delete serialReaderThread;
    }
    // Close the fd.
    if (fdPort != -1)
    {
        close(fdPort);
        fdPort = -1;
        std::cout << "Connection closed." << std::endl;

        // Notify GUI.
        emit connectedChanged(false);
    }
}
