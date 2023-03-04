#include "Header files/Offer.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

Offer* create_offer(char* type, char* destination, char* date,int price)
{
    Offer* o =malloc(sizeof(Offer));
    if (o==NULL)
        return NULL;
    o->type=malloc(sizeof(char)*(strlen(type)+1));
    if (o->type!=NULL)
        strcpy(o->type,type);
    o->destination=malloc(sizeof(char)*(strlen(destination)+1));
    if (o->destination!=NULL)
        strcpy(o->destination,destination);
    o->date=malloc(sizeof(char)*(strlen(date)+1));
    if (o->date!=NULL)
        strcpy(o->date,date);
    o->price=price;
    return o;
}

void destroy_offer(Offer* o)
{
    if (o==NULL)
        return;
    free(o->type);
    free(o->destination);
    free(o->date);
    free(o);
}

char* get_type(Offer* o)
{
    if (o==NULL)
        return NULL;
    return o->type;
}

char* get_destination(Offer* o)
{
    if(o==NULL)
        return NULL;
    return o->destination;
}

char* get_date(Offer* o)
{
    if(o==NULL)
        return NULL;
    return o->date;
}

int get_price(Offer* o)
{
    if(o==NULL)
        return -1;
    return o->price;
}

void toString(Offer* o)
{
    if(o==NULL)
        return ;
    printf("Offer of type %s, at %s, on %s, for %d.\n",o->type,o->destination,o->date,o->price);
}

