//
// Created by varga on 5/27/2022.
//

#include "Domain.h"

Equation::Equation(double a, double b,double c) {
    this->a=a;
    this->b=b;
    this->c=c;
}

std::istream &operator>>(std::istream& is, Equation &e) {
    if(is.eof())
    {
        is.setstate(std::ios_base::failbit);
        return is;
    }
    std::string var;
    std::getline(is,var,',');
    if(var.empty())
        return is;
    e.a=std::stoi(var);
    std::getline(is,var,',');
    e.b=std::stoi(var);
    std::getline(is,var,'\n');
    e.c=std::stoi(var);

    return is;
}
