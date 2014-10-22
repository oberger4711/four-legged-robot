#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "gui.h"
#include "serialportsettings.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow, public Gui
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
    void onConnectedChanged(bool established);
signals:
    void connect(const struct SerialPortSettings settings);
    void disconnect();
};

#endif // MAINWINDOW_H
