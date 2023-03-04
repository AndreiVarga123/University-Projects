#include "Ui.h"
#include <iostream>

Ui::Ui(Service s): serv(s){
}

void print_menu()
{
    printf("1. Remove patient\n");
    printf("2. Show all patiens\n");
    printf("3. Quarantine\n");
    printf("0. Exit\n");
}

void Ui::start() {
    int option;
    while(true)
    {
        print_menu();
        printf("Input option:");
        scanf_s("%d",&option);
        if(option==1){
            std::string name;
            printf("Input name:");
            std::getchar();
            std::getline(std::cin,name);
            bool was_removed=serv.remove_patient(name);
            if(was_removed)
                printf("Was removed\n");
            else
                printf("Not removed\n");
        }
        else if(option==2)
        {
            std::vector<Patient> arr=serv.get_sorted_list();
            for(int i=0;i<arr.size();i++)
                arr[i].to_string();
        }
        else if(option==3)
        {
            serv.quarantine();
            printf("Patients quarantined\n");
        }
        else if(option==0)
            return;
        else
            printf("Invalid command\n");
    }

}

Ui::~Ui()=default;
