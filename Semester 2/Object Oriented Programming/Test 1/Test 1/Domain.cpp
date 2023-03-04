//
// Created by varga on 4/8/2022.
//

#include "Domain.h"

Patient::Patient(std::string name, int age, bool infected, int room_number, bool quarantined) {
    this->name=name;
    this->age=age;
    this->infected=infected;
    this->room_number=room_number;
    this->quarantined=quarantined;
}

std::string Patient::get_name() {
    return this->name;
}

bool Patient::get_infected() {
    return this->infected;
}

void Patient::to_string() {
    std::string s1,s2;
    if(this->infected)
        s1="true";
    else
        s1="false";
    if(this->quarantined) {
        s2 = "true";
    }
    else
        s2="false";
    if(quarantined)
        printf("Name:%s, Age:%d, Infected:%s, Room number:Q%d, Quarantined:%s\n",this->name.c_str(),this->age,s1.c_str(),this->room_number,s2.c_str());
    else
        printf("Name:%s, Age:%d, Infected:%s, Room number:%d, Quarantined:%s\n",this->name.c_str(),this->age,s1.c_str(),this->room_number,s2.c_str());
}

int Patient::get_age() {
    return this->age;
}

void Patient::set_quarantine()
{
    this->quarantined=true;
}

int Patient::get_room() {
    return this->room_number;
}

Patient::~Patient() =default;
