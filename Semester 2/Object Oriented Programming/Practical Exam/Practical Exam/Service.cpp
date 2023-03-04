//
// Created by varga on 6/8/2022.
//

#include "Service.h"

void Service::sort_by_filename() {
    for(int i=0;i<r.carr.size()-1;i++)
        for(int j=i+1;j<r.carr.size();j++)
            if(r.carr[i].name>r.carr[j].name)
                std::swap(r.carr[i],r.carr[j]);
}

void Service::add(std::string name,int member_index) {
    if(name=="")
        throw std::exception();
    for(auto c:r.carr)
    {
        if(c.name==name)
            throw std::exception();
    }
    r.carr.push_back(Code(name,"not_revised",r.marr[member_index].name,""));
}

void Service::revise(std::string name,int member_index) {
    for(auto& c:r.carr)
        if(c.name==name)
        {
            if(c.status!="not_revised"||r.marr[member_index].name==c.creator)
                throw std::exception();
            c.status="revised";
            c.reviewer=r.marr[member_index].name;
            r.marr[member_index].number_of_revised_files++;
            break;
        }
}
