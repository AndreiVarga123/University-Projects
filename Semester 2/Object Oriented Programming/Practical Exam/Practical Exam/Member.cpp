//
// Created by varga on 6/8/2022.
//

#include "Member.h"

Member::Member(std::string n, int r, int t) {
    name=n;
    number_of_revised_files=r;
    total_number_of_flies=t;
}

std::istream &operator>>(std::istream &is,Member& m) {
    if(is.eof())
    {
        is.setstate(std::ios_base::failbit);
        return is;
    }
    std::getline(is,m.name,',');
    if(m.name.empty())
        return is;
    std::string var;
    std::getline(is,var,',');
    m.number_of_revised_files=std::stoi(var);
    std::getline(is,var,'\n');
    m.total_number_of_flies=std::stoi(var);
    return is;
}
