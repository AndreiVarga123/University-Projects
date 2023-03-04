//
// Created by varga on 6/8/2022.
//

#pragma once
#include <fstream>
#include <string>

class Code{
public:
    std::string name;
    std::string status;
    std::string creator;
    std::string reviewer;
    Code()=default;
    Code(std::string n,std::string s,std::string c,std::string r);
    friend std::istream& operator>>(std::istream& is,Code& c);
};

