#pragma once
#include "Offer.h"

typedef struct{
    char* type;
    Offer* offer;
    char* destination;
    char* date;
}Operation;

Operation* create_operation(char* type,Offer* offer,char* destination,char* date);
void destroy_operation(Operation* op);

char* get_op_type(Operation* op);
Offer* get_op_offer(Operation* op);
char* get_op_destination(Operation* op);
char* get_op_date(Operation* op);