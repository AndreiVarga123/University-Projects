//
// Created by varga on 6/8/2022.
//

#pragma once
#include "Member.h"
#include "Code.h"
#include <vector>

class Repo
{
public:
    std::vector<Member> marr;
    std::vector<Code> carr;
    Repo();
    void read_from_files();
};