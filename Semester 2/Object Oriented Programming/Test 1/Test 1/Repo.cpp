//
// Created by varga on 4/8/2022.
//

#include "Repo.h"

Repo::Repo(){
    arr.emplace_back("Jessica_Thompson",42,false,3,false);
    arr.push_back(Patient("Lidia_Aspen",30,true,3,true));
    arr.push_back(Patient("Scott_Smith",86,false,2,false));
    arr.push_back(Patient("Zeno_Hardy",37,true,8,false));
    arr.push_back(Patient("Andrew_Scott",62,false,2,false));
}

Repo::~Repo() =default;


///searches patients name and if found removes it
///if remove is succesful return true,otherwise false
bool Repo::remove_patient(std::string name) {
    for(int i=0;i<arr.size();i++)
        if(arr[i].get_name()==name) {
            arr.erase(arr.begin() + i);
            return true;
        }
    return false;
}

std::vector<Patient> Repo::get_list() {
    return this->arr;
}

///Create a list in which it adds the rooms with infected patients, by going thru the list
///After that go thru the list again and if the patient is in an infected room we set quarantine to true
void Repo::quarantine()
{
    std::vector<int> infected_rooms;
    for(int i=0;i<arr.size();i++)
        if(arr[i].get_infected()) {
            infected_rooms.push_back(arr[i].get_room());
        }

    for(int i=0;i<arr.size();i++)
        for(int j=0;j<infected_rooms.size();j++)
            if(arr[i].get_room()==infected_rooms[j])
            {
                arr[i].set_quarantine();
                break;
            }
}
