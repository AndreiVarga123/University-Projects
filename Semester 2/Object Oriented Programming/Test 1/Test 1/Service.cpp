#include "Service.h"

Service::Service(Repo r) {
    this->repo=r;

}

///returns if the patient was removed or not
///if name exists it will be false,true otherwise
bool Service::remove_patient(std::string name) {
    return repo.remove_patient(name);
}

std::vector<Patient> Service::get_sorted_list() {
    std::vector<Patient> sorted=repo.get_list();
    for(int i=0;i<sorted.size()-1;i++)
        for(int j=i+1;j<sorted.size();j++)
            if(sorted[i].get_age()>sorted[j].get_age())
                std::swap(sorted[i],sorted[j]);
    return sorted;
}

///tells repo to execute the quarantine function
void Service::quarantine()
{
    repo.quarantine();
}

Service::~Service() =default;
