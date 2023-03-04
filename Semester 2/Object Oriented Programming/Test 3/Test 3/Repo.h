//
// Created by varga on 5/27/2022.
//

#pragma once
#include <vector>
#include "../../t3-AndreiVarga123/Domain.h"

class Repo{
public:
    std::vector<Equation> arr;
    Repo();
    void read_from_file();
};
