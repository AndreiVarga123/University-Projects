//
// Created by varga on 5/27/2022.
//

#pragma once
#include "Service.h"
#include "QWidget"
#include "QBoxLayout"
#include "QListWidget"
#include "QListWidgetItem"
#include "QColor"
#include "QBrush"
#include "QPushButton"
#include "QLineEdit"
#include "QString"

class Gui:public QWidget{
private:
    Q_OBJECT
public:

    Service s;

    Gui();
    void init_gui();
    void populate_gui();
    void connect_gui();

    QListWidget* equations;
    QLineEdit* a;
    QLineEdit* b;
    QLineEdit* c;
    QPushButton* add;

    QPushButton* show_solution;
    QLineEdit* solution1;
    QLineEdit* solution2;

    void _add();
    void _solution();
};