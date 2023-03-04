//
// Created by varga on 5/6/2022.
//

#pragma once

#include "Sensor.h"
#include "HumiditySensor.h"
#include "SmokeSensor.h"
#include "TemperatureSensor.h"

class Device{
private:
    std::vector<Sensor*> arr;

public:
    bool hasWifi;
    Device()
    {
        arr.clear();
    }

    void addSensor(Sensor* s);
    std::vector<Sensor*> get_all_sensors();
    std::vector<Sensor*> get_alerting_sesnors();
    void write_to_file(std::string file);
};