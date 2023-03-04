#pragma once
#include "Header files/Repo.h"
#include "Operation.h"

typedef struct
{
    OfferRepo* repo;
    Operation* op_history[100][2];
    int history_len;
    int history_index;
}OfferService;

OfferService* create_service(OfferRepo* repo);
void destroy_service(OfferService* service);

int add_offer(OfferService* service,char* type, char* destination, char* date,char* price);
int delete_offer(OfferService* service, char* destination, char* date);
int update_offer(OfferService* service, char* destination, char* date,char* new_type, char* new_destination, char* new_date,char* new_price);

void filter_by_destination(OfferService* service,Offer* array[],int * length, char* string);
void filter_by_type(OfferService* service,Offer* array[],int * length, char* string);
void offers_for_given_type_after_date(OfferService* service,Offer* array[],int * length, char* type,char* date);

void record_operations(OfferService* service,Operation* undo_op,Operation* redo_op);
int undo_redo(OfferService* service,int index);

void test_service();
