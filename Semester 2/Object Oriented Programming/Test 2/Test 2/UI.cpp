//
// Created by varga on 5/6/2022.
//

#include "UI.h"
#include <iostream>

void UI::print_menu() {
    std::cout<<"0. Exit\n";
    std::cout<<"1. Add sensor\n";
    std::cout<<"2. Show all sensors\n";
    std::cout<<"3. Show all alerting sensors\n";
    std::cout<<"4. write to file\n";
}

void UI::start() {
    this->intial();
    while(true)
    {
        this->print_menu();
        int option;
        std::cout<<"Option: ";
        std::cin>>option;
        if(option == 0)
            return;
        else if(option == 1)
        {
            std::cout<<"What type of sesnor?(1- temp, 2- smoke,3- humidity)";
            int type,n;
            std::cin>>type;
            std::string prod;
            std::vector<double> rec;
            std::cout<<"Producer:";
            std::getchar();
            std::getline(std::cin,prod);
            std::cout<<"Nr of recs:";
            std::cin>>n;
            std::cout<<"Recordings:";
            double r;
            for(int i=0;i<n;i++) {
                std::cin >> r;
                rec.push_back(r);
            }
            if(type==1)
            {
                int d,l;
                std::cout<<"Diameter:";
                std::cin>>d;
                std::cout<<"Length:";
                std::cin>>l;
                TemperatureSensor * t= new TemperatureSensor(prod,rec,d,l);
                this->d.addSensor(t);
            }
            else if(type==2)
            {
                SmokeSensor * s= new SmokeSensor(prod,rec);
                this->d.addSensor(s);
            }
            else if(type==3)
            {
                HumiditySensor * h= new HumiditySensor(prod,rec);
                this->d.addSensor(h);
            }
        }
        else if(option==2)
        {
            std::vector<Sensor*> arr;
            arr=d.get_all_sensors();
            for(int i=0;i<arr.size();i++)
                std::cout<<arr[i]->toString();
        }
        else if(option==3)
        {
            std::vector<Sensor*> arr;
            arr= d.get_alerting_sesnors();
            for(int i=0;i<arr.size();i++)
                std::cout<<arr[i]->toString();
        }
        else if(option==4)
        {
            std::cout<<"Has wifi?";
            std::cin>>d.hasWifi;
            std::cout<<"File:";
            std::string file;
            std::cin>>file;
            d.write_to_file(file);
        }
    }

}

void UI::intial() {
    std::vector<double> rec;
    rec.push_back(10);
    rec.push_back(1700);
    SmokeSensor * s= new SmokeSensor("b",rec);
    rec.clear();
    rec.push_back(50);
    HumiditySensor * h= new HumiditySensor("c",rec);
    d.addSensor(s);
    d.addSensor(h);
    rec.clear();
    rec.push_back(2);
    rec.push_back(3);
    TemperatureSensor * t= new TemperatureSensor("a", rec,10,20);
    d.addSensor(t);
}
