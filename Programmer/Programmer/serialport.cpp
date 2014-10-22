#include "serialport.h"

SerialPort::SerialPort()
{
}

void SerialPort::setSerialPortSettings(const struct SerialPortSettings& settings)
{
    this->serialPortSettings = new struct SerialPortSettings(settings);
}

void SerialPort::connect()
{
    bool success = true;
    if (serialPortSettings != NULL)
    {
        const char* portnameCharArray = serialPortSettings->portname.toStdString().c_str();
        fdPort = open(portnameCharArray, O_RDWR | O_NOCTTY | O_NDELAY);

        if (fdPort != -1)
        {
            // Successfully opened
            // Apply settings to the fd
            struct termios myTermios;
            tcgetattr(fdPort, &myTermios);
            cfsetispeed(&myTermios, serialPortSettings->baudrate);
            cfsetospeed(&myTermios, serialPortSettings->baudrate);
            if (tcsetattr(fdPort, TCSANOW, &myTermios) == 0)
            {
                // Applied settings correctly
                success = true;
            }
            else
            {
                // Could not set baudrate
                perror(NULL);
                success = false;
            }
        }
        else
        {
            // Could not open
            perror(NULL);
            success = false;
        }

    }
    else
    {
        // Settings are not set
        std::cout << "Tried to connect with no settings set." << std::endl;
        success = false;
    }

    if (success)
    {
        std::cout << "Connection established." << std::endl;
        onConnectedChanged(true);
    }
    else
    {
        std::cout << "Connection failed." << std::endl;
        onConnectedChanged(false);
    }
}


void SerialPort::disconnect()
{
    close(fdPort);
    std::cout << "Connection closed." << std::endl;
    onConnectedChanged(false);
}
