#pragma once
#include "Domain.h"
#include <vector>
#include <string>

class Repo
{
private:
    std::vector<Patient> arr;
public:
    Repo();
    bool remove_patient(std::string name);
    std::vector<Patient>get_list();
    void quarantine();
    ~Repo();
};
