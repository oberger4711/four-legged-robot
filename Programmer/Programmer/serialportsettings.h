#ifndef SERIALPORTSETTINGS_H
#define SERIALPORTSETTINGS_H

#include <QString>
#include <termios.h>

struct SerialPortSettings
{
    QString portname;
    speed_t baudrate;
};

#endif // SERIALPORTSETTINGS_H
