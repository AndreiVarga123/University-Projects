//
// Created by varga on 6/8/2022.
//

#include "Code.h"

Code::Code(std::string n, std::string s, std::string c, std::string r) {
    name=n;
    status=s;
    creator=c;
    reviewer=r;
}

std::istream &operator>>(std::istream &is, Code &c) {
    if(is.eof())
    {
        is.setstate(std::ios_base::failbit);
        return is;
    }
    std::getline(is,c.name,',');
    if(c.name.empty())
        return is;
    std::getline(is,c.status,',');
    std::getline(is,c.creator,',');
    std::getline(is,c.reviewer,'\n');
    return is;
}
