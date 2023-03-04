//
// Created by varga on 6/8/2022.
//

#pragma once
#include "Repo.h"

class Service{
public:
    Repo r;
    Service()=default;
    void sort_by_filename();
    void add(std::string name,int member_index);
    void revise(std::string name,int member_index);
};