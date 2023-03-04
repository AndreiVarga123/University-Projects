//
// Created by varga on 6/8/2022.
//

#pragma once
#include <fstream>
#include <string>

class Member{
public:
    std::string name;
    int number_of_revised_files;
    int total_number_of_flies;
    Member()=default;
    Member(std::string n,int r,int t);
    friend std::istream& operator>>(std::istream& is,Member& m);
};
