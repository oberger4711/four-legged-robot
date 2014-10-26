#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "serialportsettings.h"

#define OUTPUT_COLOR_RECEIVED "Black"
#define OUTPUT_COLOR_CONNECTION "DarkGray"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT
private:
    Ui::MainWindow *ui;

    void addOutputText(QString string, QString htmlColorName);

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
