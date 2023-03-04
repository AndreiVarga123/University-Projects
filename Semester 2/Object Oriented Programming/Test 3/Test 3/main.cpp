#include <QApplication>
#include <QPushButton>
#include "../../t3-AndreiVarga123/Gui.h"

int main(int argc, char *argv[]) {
    QApplication a(argc, argv);
    Gui gui;
    gui.show();
    return a.exec();
}
