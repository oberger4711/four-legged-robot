#include "mainwindow.h"
#include <QApplication>
#include "controllerconnect.h"
#include "controllerdisconnect.h"
#include "serialport.h"
#include "serialportsettings.h"

int main(int argc, char *argv[])
{
    // View
    QApplication a(argc, argv);
    MainWindow w;

    // Model
    SerialPort serialPort;
    QObject::connect(&serialPort, SIGNAL(onConnectedChanged(bool)), &w, SLOT(onConnectedChanged(bool)));

    // Controller
    ControllerConnect controllerConnect(&serialPort);
    QObject::connect(&w, SIGNAL(connect(const struct SerialPortSettings)), &controllerConnect, SLOT(connect(const struct SerialPortSettings)));

    ControllerDisconnect controllerDisconnect(&serialPort);
    QObject::connect(&w, SIGNAL(disconnect()), &controllerDisconnect, SLOT(disconnect()));

    w.show();

    return a.exec();
}
