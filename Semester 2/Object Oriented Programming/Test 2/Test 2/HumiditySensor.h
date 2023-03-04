//
// Created by varga on 5/6/2022.
//

#pragma once
#include "Sensor.h"

class HumiditySensor: public Sensor{
public:
    HumiditySensor()
    {
        producer="";
        recordings.clear();
    }

    HumiditySensor(std::string prod,std::vector<double> rec)
    {
        producer=prod;
        recordings=rec;
    }

    HumiditySensor(const HumiditySensor& h)
    {
        this->producer=h.producer;
        this->recordings=h.recordings;
    }

    bool sendAlert() const override{
        int alert=0;
        for(int i=0;i<recordings.size();i++)
            if(recordings[i]<30 || recordings[i]>85)
                alert++;
        if(alert>=2)
            return true;
        return false;
    }

    double getPrice() const override{
        return 4;
    }

    std::string toString() const override{
        std::string str="Humidity sensor, "+producer+", recording: ";
        for(int i=0;i<recordings.size();i++)
            str+=std::to_string(recordings[i])+" ";
        str+="\n";
        return str;
    }
};