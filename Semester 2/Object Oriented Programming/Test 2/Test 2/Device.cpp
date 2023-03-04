//
// Created by varga on 5/6/2022.
//

#include "Device.h"
#include <fstream>
#include <cstring>

void Device::addSensor(Sensor *s) {
    arr.push_back(s);
}

std::vector<Sensor *> Device::get_all_sensors() {
    return arr;
}

std::vector<Sensor *> Device::get_alerting_sesnors() {
    std::vector<Sensor*> alarr;
    for(int i=0;i<arr.size();i++)
        if(arr[i]->sendAlert())
            alarr.push_back(arr[i]);
    return alarr;
}

void Device::write_to_file(std::string file) {
    for(int i=0;i<arr.size()-1;i++)
        for(int j=i+1;j<arr.size();j++)
            if(std::strcmp(arr[i]->producer.c_str(),arr[j]->producer.c_str())>0)
                std::swap(arr[i],arr[j]);
    int price=19;
    if(this->hasWifi)
        price+=20;

    for(int i=0;i<arr.size();i++)
        price+=arr[i]->getPrice();

    file="C:\\Users\\varga\\CLionProjects\\t2-AndreiVarga123-1\\"+file;
    std::ofstream f(file);
    if(f.is_open())
    {
        f<<std::to_string(price);
        f<<"\n";
        f<<std::to_string(hasWifi);
        f<<"\n";
        for(int i=0;i<arr.size();i++)
            f<<arr[i]->toString();
    }

}
