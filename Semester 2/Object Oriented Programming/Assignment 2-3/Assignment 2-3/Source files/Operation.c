#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Header files/Operation.h"

Operation *create_operation(char* type,Offer* offer,char* destination,char* date)
{
    Operation* op= malloc(sizeof (Operation));
    if(op==NULL)
        return NULL;
    op->type=malloc(sizeof(char)*(strlen(type)+1));
    strcpy(op->type,type);
    if(offer!=NULL)
        op->offer = create_offer(get_type(offer), get_destination(offer), get_date(offer), get_price(offer));
    else
        op->offer=NULL;
    if(destination!=NULL)
    {
        op->destination= malloc(sizeof (char)*(strlen(destination)+1));
        strcpy(op->destination, destination);
    }
    else
        op->destination=NULL;
    if(date!=NULL)
    {
        op->date= malloc(sizeof (char)*(strlen(date)+1));
        strcpy(op->date, date);
    }
    else
        op->date=NULL;
    return op;
}

void destroy_operation(Operation* op)
{
    if (op==NULL)
        return;
    free(op->type);
    if(op->offer!=NULL)
        destroy_offer(op->offer);
    if(op->destination!=NULL)
        free(op->destination);
    if(op->date!=NULL)
        free(op->date);
    free(op);
}

Offer *get_op_offer(Operation *op)
{
    return op->offer;
}

char *get_op_destination(Operation *op)
{
    return op->destination;
}

char *get_op_date(Operation *op)
{
    return op->date;
}

char* get_op_type(Operation* op)
{
    return op->type;
}


