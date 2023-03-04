#include "Header files/Ui.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

Ui* create_ui(OfferService* service)
{
    Ui* ui= malloc(sizeof (Ui));
    if(ui==NULL)
        return NULL;

    ui->service=service;

    return ui;
}

void destroy_ui(Ui* ui)
{
    if(ui==NULL)
        return;

    destroy_service(ui->service);
    free(ui);
}

void print_menu()
{
    printf("1. Add offer\n");
    printf("2. Delete offer\n");
    printf("3. Update offer\n");
    printf("4. Filter by destination\n");
    printf("5. Filter by type\n");
    printf("6. Print offers of given type after given date\n");
    printf("7. Undo\n");
    printf("8. Redo\n");
    printf("9. Exit\n");
}

int read_option()
{
    char option[30];
    char* remaining;
    int option_nr;
    printf("Input an option:");
    scanf_s("\n%[^\n]",&option);
    option_nr= strtol(option,&remaining,10);
    return option_nr;
}

int add_offer_ui(Ui* ui)
{
    char type[30],destination[30],date[30],price[30];
    printf("Input type of offer to add:");
    scanf_s("\n%[^\n]",&type);
    printf("Input destination of offer to add:");
    scanf_s("\n%[^\n]",&destination);
    printf("Input date (dd/mm/yyyy) of offer to add:");
    scanf_s("\n%[^\n]",&date);
    printf("Input price of offer to add:");
    scanf_s("\n%[^\n]",&price);
    return add_offer(ui->service,type,destination,date,price);
}

int delete_offer_ui(Ui* ui)
{
    char destination[30],date[30];
    printf("Input destination of offer to delete:");
    scanf_s("\n%[^\n]",&destination);
    printf("Input date (dd/mm/yyyy) of offer to delete:");
    scanf_s("\n%[^\n]",&date);
    return delete_offer(ui->service,destination,date);
}

int update_offer_ui(Ui* ui)
{
    char destination[30],date[30],new_type[30],new_destination[30],new_date[30],new_price[30];
    printf("Input destination of offer to update:");
    scanf_s("\n%[^\n]",&destination);
    printf("Input date (dd/mm/yyyy) of offer to update:");
    scanf_s("\n%[^\n]",&date);
    printf("Input new type:");
    scanf_s("\n%[^\n]",&new_type);
    printf("Input new destination:");
    scanf_s("\n%[^\n]",&new_destination);
    printf("Input new date (dd/mm/yyyy):");
    scanf_s("\n%[^\n]",&new_date);
    printf("Input new price:");
    scanf_s("\n%[^\n]",&new_price);
    return update_offer(ui->service,destination,date,new_type,new_destination,new_date,new_price);
}

void filter_by_destination_ui(Ui* ui)
{
    char string[30];
    int length=0;
    int *p=&length;
    Offer* array[100];
    printf("Input string:");
    getchar();
    gets(string);
    if(strcmp(string,"")==0)
        filter_by_destination(ui->service,array,p,"");
    else
        filter_by_destination(ui->service,array,p,string);
    for(int i=0;i<length;i++)
        toString(array[i]);
}

void filter_by_type_ui(Ui* ui)
{
    char string[30];
    int length=0;
    int *p=&length;
    Offer* array[100];
    printf("Input string:");
    getchar();
    gets(string);
    filter_by_type(ui->service,array,p,string);
    for(int i=0;i<length;i++)
        toString(array[i]);
}

void print_offers_of_given_type_after_date(Ui* ui)
{
    char type[30],date[30];
    int length=0;
    int *p=&length;
    Offer* array[100];
    printf("Input type:");
    scanf_s("\n%[^\n]",&type);
    printf("Input date:");
    scanf_s("\n%[^\n]",&date);
    offers_for_given_type_after_date(ui->service,array,p,type,date);
    for(int i=0;i<length;i++)
        toString(array[i]);

}

void start_ui(Ui* ui)
{
    int option;
    while(1)
    {
        print_menu();
        option=read_option();
        if(option==1)
        {
            int was_added= add_offer_ui(ui);
            if (was_added==1)
                printf("Offer successfully added!\n");
            else
                printf("Please input a valid offer!\n");
        }
        else if(option==2)
        {
            int was_deleted = delete_offer_ui(ui);
            if (was_deleted==1)
                printf("Offer successfully deleted!\n");
            else
                printf("Please input a valid offer!\n");
        }
        else if(option==3)
        {
            int was_updated=update_offer_ui(ui);
            if (was_updated==1)
                printf("Offer successfully updated!\n");
            else
                printf("Invalid details of offer to update, or invalid details of new offer!\n");
        }
        else if(option==4)
            filter_by_destination_ui(ui);
        else if(option==5)
            filter_by_type_ui(ui);
        else if(option==6)
            print_offers_of_given_type_after_date(ui);
        else if(option==7)
        {
            int was_undone= undo_redo(ui->service,0);
            if(was_undone)
                printf("Successful undo\n");
            else
                printf("Can't undo anymore\n");
        }
        else if(option==8)
        {
            int was_redone= undo_redo(ui->service,1);
            if(was_redone)
                printf("Successful redo\n");
            else
                printf("Can't redo anymore\n");
        }
        else if(option==9)
            return;
        else
            printf("Please input a valid command!\n");
    }
}