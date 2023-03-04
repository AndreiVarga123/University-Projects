#pragma once
#include "Header files/Offer.h"
#include "DynamicArray.h"

typedef struct
{
    DynamicArray* offers;
}OfferRepo;

OfferRepo* create_repo();
void destroy_repo(OfferRepo* repo);

int add(OfferRepo* repo,Offer* o);
int delete(OfferRepo* repo,char const* destination,char const* date);
int update(OfferRepo* repo,char const* destination, char const* date, Offer* new_o);

int get_length(OfferRepo* repo);

Offer* get_offer_on_pos(OfferRepo* repo,int pos);
Offer* get_offer_at_destination_and_date(OfferRepo* repo, char* destination,char* date);

void test_repo();