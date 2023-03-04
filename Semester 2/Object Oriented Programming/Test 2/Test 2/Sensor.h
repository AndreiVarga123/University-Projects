//
// Created by varga on 5/6/2022.
//

#pragma once
#include <string>
#include <vector>

class Sensor{
public:
    std::string producer;
    std::vector<double> recordings;
    virtual bool sendAlert() const = 0;
    virtual double getPrice() const = 0;
    virtual std::string toString() const =0;
};
