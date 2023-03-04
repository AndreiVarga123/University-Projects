#pragma once

typedef struct
{
    char* type;
    char* destination;
    char* date;
    int price;
}Offer;

Offer* create_offer(char* type, char* destination, char* date,int price);
void destroy_offer(Offer* o);

char* get_type(Offer* o);
char* get_destination(Offer* o);
char* get_date(Offer* o);
int get_price(Offer* o);

void toString(Offer* o);


