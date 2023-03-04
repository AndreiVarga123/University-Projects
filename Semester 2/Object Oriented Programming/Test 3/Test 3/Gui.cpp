//
// Created by varga on 5/27/2022.
//

#include "Gui.h"

Gui::Gui() {
    init_gui();
    populate_gui();
    connect_gui();

}

void Gui::init_gui() {
    QVBoxLayout* layout = new QVBoxLayout(this);
    equations=new QListWidget();
    a=new QLineEdit();
    b=new QLineEdit();
    c=new QLineEdit();
    add=new QPushButton("Add");
    show_solution=new QPushButton("Solution");
    solution1=new QLineEdit();
    solution2=new QLineEdit();
    layout->addWidget(equations);
    layout->addWidget(a);
    layout->addWidget(b);
    layout->addWidget(c);
    layout->addWidget(add);
    layout->addWidget(show_solution);
    layout->addWidget(solution1);
    layout->addWidget(solution2);
}

void Gui::populate_gui() {
    equations->clear();
    QColor color(0,255,0);
    QBrush brush(color);
    for(auto& e:s.r.arr)
    {
        std::string eq;
        eq=std::to_string(e.a)+"*x^2";
        if(e.b<0)
            eq+="- "+std::to_string(e.b)+"*x";
        else
            eq+="+ "+std::to_string(e.b)+"*x";

        if(e.c<0)
            eq+="- "+std::to_string(e.c);
        else
            eq+="+ "+std::to_string(e.c);
        QString str = QString::fromStdString(eq);
        QListWidgetItem* item=new QListWidgetItem(str);
        if(e.b*e.b-(4*e.a*e.c)>=0||e.a==0.0)
            item->setBackground(brush);
        equations->addItem(item);
    }
}

void Gui::connect_gui() {
    QObject::connect(add,&QPushButton::clicked,this,&Gui::_add);
    QObject::connect(show_solution,&QPushButton::clicked,this,&Gui::_solution);

}

void Gui::_add()
{
    std::string coef1=a->text().toStdString();
    std::string coef2=b->text().toStdString();
    std::string coef3=c->text().toStdString();
    Equation e;
    e.a= std::stoi(coef1);
    e.b= std::stoi(coef2);
    e.c= std::stoi(coef3);
    s.add(e);
    populate_gui();
}

void Gui::_solution() {
    std::string data=equations->currentItem()->text().toStdString();
    for(auto& e:s.r.arr )
    {
        std::string eq;
        eq=std::to_string(e.a)+"*x^2";
        if(e.b<0)
            eq+="- "+std::to_string(e.b)+"*x";
        else
            eq+="+ "+std::to_string(e.b)+"*x";

        if(e.c<0)
            eq+="- "+std::to_string(e.c);
        else
            eq+="+ "+std::to_string(e.c);
        if(eq==data)
        {
            if(e.a==0)
            {
                if(e.b!=0)
                {
                    double sol = -e.c/e.b;
                    solution1->setText(QString::fromStdString(std::to_string(sol)));
                }
            }
            else {
                double x1, x2;
                if (e.b * e.b - (4 * e.a * e.c) < 0) {
                    std::string sol;
                    sol += std::to_string(-e.b / (2 * e.a));
                    sol += " + " + std::to_string((sqrt(abs(e.b * e.b - (4 * e.a * e.c)))) / (2 * e.a)) + "*i";
                    solution1->setText(QString::fromStdString(sol));
                    sol.clear();
                    sol += std::to_string(-e.b / (2 * e.a));
                    sol += " - " + std::to_string((sqrt(abs(e.b * e.b - (4 * e.a * e.c)))) / (2 * e.a)) + "*i";
                    solution2->setText(QString::fromStdString(sol));
                } else {
                    x1 = (-e.b + sqrt(e.b * e.b - (4 * e.a * e.c))) / (2 * e.a);
                    x2 = (-e.b - sqrt(e.b * e.b - (4 * e.a * e.c))) / (2 * e.a);
                    solution1->setText(QString::fromStdString(std::to_string(x1)));
                    solution2->setText(QString::fromStdString(std::to_string(x2)));
                }
            }
            break;
        }
    }
}

