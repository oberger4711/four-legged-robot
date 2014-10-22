#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    ui->comboBoxBaudrate->addItem("2400", B2400);
    ui->comboBoxBaudrate->addItem("4800", B4800);
    ui->comboBoxBaudrate->addItem("9600", B9600);
    ui->comboBoxBaudrate->addItem("19200", B19200);
    ui->comboBoxBaudrate->addItem("38400", B38400);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::clickConnect()
{
    struct SerialPortSettings settings;
    settings.portname = ui->editPort->text();
    settings.baudrate = (speed_t)ui->comboBoxBaudrate->currentData().toUInt();

    connect(settings);
}

void MainWindow::clickDisconnect()
{
    disconnect();
}

void MainWindow::onConnectedChanged(bool established)
{
    setSerialPortControlsEnabled(established);
}

void MainWindow::setSerialPortControlsEnabled(bool connected)
{
    ui->btnConnect->setEnabled(!connected);
    ui->btnDisconnect->setEnabled(connected);
    ui->editPort->setEnabled(!connected);
    ui->comboBoxBaudrate->setEnabled(!connected);
    ui->btnUpload->setEnabled(connected);
    ui->editMessage->setEnabled(connected);
    ui->btnSend->setEnabled(connected);
}
