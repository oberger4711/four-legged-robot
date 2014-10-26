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

    ui->editOutput->setText("");
    emit connect(settings);
}

void MainWindow::clickDisconnect()
{
    emit disconnect();
}

void MainWindow::clickSend()
{
    emit serialWrite(ui->editMessage->text());
    ui->editMessage->setText("");
}

void MainWindow::onConnectedChanged(bool established)
{
    ui->btnConnect->setEnabled(!established);
    ui->btnDisconnect->setEnabled(established);
    ui->editPort->setEnabled(!established);
    ui->comboBoxBaudrate->setEnabled(!established);
    ui->btnUpload->setEnabled(established);
    ui->editMessage->setEnabled(established);
    ui->btnSend->setEnabled(established);

    QString outputString;
    if (established)
    {
        outputString = "Connected";
    }
    else
    {
        outputString = "Not Connected";
    }
    addOutputText(" - " + outputString + " - \n", OUTPUT_COLOR_CONNECTION);
}

void MainWindow::onSerialRead(QString readString)
{
    addOutputText(readString, OUTPUT_COLOR_RECEIVED);
}


void MainWindow::addOutputText(QString string, QString htmlColorName)
{
    string.replace("\n", "<br>");
    QString insertString = "<font color=\"" + htmlColorName + "\">" + string + "</font>";
    ui->editOutput->moveCursor(QTextCursor::End);
    ui->editOutput->insertHtml(insertString);
    ui->editOutput->moveCursor(QTextCursor::End);
}
