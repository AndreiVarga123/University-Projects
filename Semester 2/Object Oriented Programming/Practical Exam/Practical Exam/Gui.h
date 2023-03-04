//
// Created by varga on 6/8/2022.
//

#pragma once
#include "Service.h"
#include "QListWidget"
#include "QListWidgetItem"
#include "QWidget"
#include "QLabel"
#include "QPushButton"
#include "QBoxLayout"
#include "QFont"
#include "QLineEdit"
#include "QErrorMessage"
#include "QMessageBox"
#include <sstream>
#include "QGraphicsEllipseItem"
#include "QGraphicsScene"
#include "QGraphicsView"
#include "QGraphicsTextItem"

class Gui:public QWidget{
private:
    Q_OBJECT
public:
    Service s;
    Gui();
    void init_gui();
    void populate_gui();
    void connect_gui();

    std::vector<QListWidget*> code;
    std::vector<QLabel*> done;
    std::vector<QLabel*> not_done;
    std::vector<QLineEdit*> name;
    std::vector<QPushButton*>add;
    std::vector<QPushButton*> revise;

    QGraphicsScene* scene;
    QGraphicsView* stats;

    void _add();
    void _revised();
};