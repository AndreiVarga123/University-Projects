//
// Created by varga on 5/27/2022.
//

#pragma once
#include "../../t3-AndreiVarga123/Repo.h"

class Service{
public:
    Repo r;
    Service()=default;
    void add(Equation e);
};
