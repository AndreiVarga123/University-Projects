//
// Created by varga on 6/8/2022.
//

#include "Gui.h"

Gui::Gui() {
    init_gui();
    populate_gui();
    connect_gui();
}

void Gui::init_gui() {
    std::vector<QWidget*> window;
    std::vector<QVBoxLayout*> layout;

    scene=new QGraphicsScene();
    stats=new QGraphicsView(scene);
    stats->setWindowTitle("Stats");
    stats->show();

    for(int i=0;i<s.r.marr.size();i++)
    {
        window.push_back(new QWidget);
        layout.push_back(new QVBoxLayout(window[i]));
        window[i]->setWindowTitle(QString::fromStdString(s.r.marr[i].name));
        code.push_back(new QListWidget());
        done.push_back(new QLabel());
        not_done.push_back(new QLabel());
        name.push_back(new QLineEdit());
        add.push_back(new QPushButton("Add"));
        revise.push_back(new QPushButton("Revise"));
        layout[i]->addWidget(code[i]);
        layout[i]->addWidget(done[i]);
        layout[i]->addWidget(not_done[i]);
        layout[i]->addWidget(name[i]);
        layout[i]->addWidget(add[i]);
        layout[i]->addWidget(revise[i]);
        window[i]->show();
    }
}

void Gui::populate_gui() {
    s.sort_by_filename();
    QFont f;
    QColor c(0,255,0);
    QBrush b(c);
    f.setBold(true);
    QColor c1(0,0,255);
    QBrush b1(c1);
    int index=0;
    scene->clear();
    for(int i=0;i<s.r.marr.size();i++)
    {
        code[i]->clear();
        for(auto c:s.r.carr)
        {
            QString str=QString::fromStdString(c.name+", "+c.status+", "+c.creator+", "+c.reviewer);
            QListWidgetItem* item=new QListWidgetItem(str);
            if(c.status=="not_revised")
                item->setFont(f);
            else
                item->setBackground(b);
            code[i]->addItem(item);
        }
        done[i]->setText(QString::fromStdString(std::to_string(s.r.marr[i].number_of_revised_files)));
        not_done[i]->setText(QString::fromStdString(std::to_string(s.r.marr[i].total_number_of_flies-s.r.marr[i].number_of_revised_files)));

        QGraphicsEllipseItem* circle=new QGraphicsEllipseItem();
        QGraphicsTextItem* programmer=new QGraphicsTextItem();
        programmer->setPlainText(QString::fromStdString(s.r.marr[i].name));
        programmer->setPos(0,index);
        circle->setRect(programmer->boundingRect().width()+10,index,s.r.marr[i].number_of_revised_files*100,s.r.marr[i].number_of_revised_files*100);
        if(not_done[i]->text().toStdString()=="0")
        {
            programmer->setDefaultTextColor(c1);
            circle->setBrush(b1);
        }
        scene->addItem(programmer);
        scene->addItem(circle);
        index+=s.r.marr[i].number_of_revised_files*100+50;

        if(not_done[i]->text().toStdString()=="0")
        {
            QMessageBox* box=new QMessageBox();
            box->setText("Congratulations!");
            box->show();
        }
    }
}

void Gui::connect_gui() {
    for(int i=0;i<s.r.marr.size();i++)
    {
        QObject::connect(add[i],&QPushButton::clicked, this,&Gui::_add);
        QObject::connect(revise[i],&QPushButton::clicked, this,&Gui::_revised);
    }
}

void Gui::_add() {
    int windowIndex;
    for(int i=0;i<s.r.marr.size();i++)
        if(sender()==add[i])
        {
            windowIndex=i;
            break;
        }

    std::string data=name[windowIndex]->text().toStdString();
    try{
        s.add(data,windowIndex);
        this->populate_gui();
    }
    catch(...)
    {
        QErrorMessage* error=new QErrorMessage();
        error->showMessage("Invalid add");
    }
}

void Gui::_revised() {
    int windowIndex;
    for(int i=0;i<s.r.marr.size();i++)
        if(sender()==revise[i])
        {
            windowIndex=i;
            break;
        }
    std::string data=code[windowIndex]->currentItem()->text().toStdString();
    std::istringstream f(data);
    std::getline(f,data,',');
    try{
        s.revise(data,windowIndex);
        this->populate_gui();
    }
    catch(...)
    {
        QErrorMessage* error=new QErrorMessage();
        error->showMessage("Invalid revise");
    }
}
