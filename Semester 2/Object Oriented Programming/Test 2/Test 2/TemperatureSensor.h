//
// Created by varga on 5/6/2022.
//

#pragma once
#include "Sensor.h"

class TemperatureSensor:public Sensor{
private:
    double diameter;
    double length;

public:
    TemperatureSensor(){
        producer="";
        recordings.clear();
        diameter=0;
        length=0;
    }

    TemperatureSensor(std::string prod,std::vector<double> rec,double diam,double len){
        producer=prod;
        recordings=rec;
        diameter=diam;
        length=len;

    }

    TemperatureSensor(const TemperatureSensor& t){
        this->producer=t.producer;
        this->recordings=t.recordings;
        this->diameter=t.diameter;
        this->length=t.length;
    }

    bool sendAlert() const override{
        int alert=0;
        for(int i=0;i<recordings.size();i++)
            if(recordings[i]<10 || recordings[i]>35)
                alert++;
        if(alert>=2)
            return true;
        return false;
    }

    double getPrice() const override{
        if(diameter<3&&length<50)
            return 17;
        return 9;
    }

    std::string toString() const override{
        std::string str="Temprature sensor, "+producer+", recording: ";
        for(int i=0;i<recordings.size();i++)
            str+=std::to_string(recordings[i])+", ";
        str+=std::to_string(this->getPrice())+", "+std::to_string(this->diameter)+", "+std::to_string(length)+"\n";
        return str;
    }
};
