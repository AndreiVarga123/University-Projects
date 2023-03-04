#pragma once
#include "Repo.h"

class Service{
private:
    Repo repo;
public:
    explicit Service(Repo r);
    bool remove_patient(std::string name);
    std::vector<Patient>get_sorted_list();
    void quarantine();
    ~Service();
};
