#pragma once
#include <stdlib.h>
#include <string>


class Patient{
private:
    std::string name;
    int age;
    bool infected;
    int room_number;
    bool quarantined;
public:
    Patient(std::string name,int age,bool infected,int room_number,bool quarantined);
    ~Patient();

    std::string get_name();
    bool get_infected();
    void set_quarantine();
    int get_room();
    int get_age();
    void to_string();
};
