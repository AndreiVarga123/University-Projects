//
// Created by varga on 5/27/2022.
//

#pragma once
#include <fstream>

class Equation{
public:
    double a;
    double b;
    double c;

    Equation()=default;
    Equation(double a,double b,double c);
    friend std::istream& operator>>(std::istream& is,Equation& e);
};
