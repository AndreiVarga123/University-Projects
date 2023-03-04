#include <QApplication>
#include <QPushButton>
#include "Gui.h"

int main(int argc, char *argv[]) {
    QApplication a(argc, argv);
    Gui g;
    return a.exec();
}
