#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "serialportsettings.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT
private:
    Ui::MainWindow *ui;

    void setSerialPortControlsEnabled(bool connected);

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void clickConnect();
    void clickDisconnect();
    void clickSend();

    void onConnectedChanged(bool established);
    void onSerialRead(QString readString);

signals:
    void connect(const struct SerialPortSettings settings);
    void disconnect();
    void serialWrite(QString writeString);
};

#endif // MAINWINDOW_H
