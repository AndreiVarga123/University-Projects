//
// Created by varga on 5/6/2022.
//

#pragma once
#include "Sensor.h"

class SmokeSensor: public Sensor{
public:
    SmokeSensor()
    {
        producer="";
        recordings.clear();
    }

    SmokeSensor(std::string prod, std::vector<double> rec)
    {
        producer=prod;
        recordings=rec;
    }

    SmokeSensor(const SmokeSensor& s){
        this->producer=s.producer;
        this->recordings=s.recordings;
    }

    bool sendAlert() const override{
        for(int i=0;i<recordings.size();i++)
            if(recordings[i]>1600)
                return true;
        return false;
    }

    double getPrice() const override{
        return 25;
    }

    std::string toString() const override{
        std::string str="Smoke sensor, "+producer+", recording: ";
        for(int i=0;i<recordings.size();i++)
            str+=std::to_string(recordings[i])+" ";
        str+="\n";
        return str;
    }
};
