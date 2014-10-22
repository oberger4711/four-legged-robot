#-------------------------------------------------
#
# Project created by QtCreator 2014-10-19T21:31:53
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = Programmer
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    serialport.cpp \
    gui.cpp \
    controllerconnect.cpp \
    controllerdisconnect.cpp

HEADERS  += mainwindow.h \
    serialport.h \
    serialportsettings.h \
    gui.h \
    controllerconnect.h \
    controllerdisconnect.h

FORMS    += mainwindow.ui
