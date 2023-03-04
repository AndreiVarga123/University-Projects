#include "Header files/Repo.h"
#include "Header files/DynamicArray.h"
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <stdio.h>

OfferRepo* create_repo()
{
    OfferRepo* repo= malloc(sizeof(OfferRepo));
    if(repo==NULL)
        return NULL;
    repo->offers = create_arr(CAPACITY, &destroy_offer);
    return repo;
}

void destroy_repo(OfferRepo* repo)
{
    if(repo==NULL)
        return;
    destroy_arr(repo->offers);
    free(repo);
}

int add(OfferRepo* repo,Offer* o)
{
    for(int i=0; i < get_length_arr(repo->offers); i++)
        if(strcmp(get_destination(o), get_destination(get(repo->offers,i)))==0 && strcmp(get_date(o), get_date(get(repo->offers,i)))==0)
        {
            destroy_offer(o);
            return 0;
        }
    add_elem(repo->offers,o);
    return 1;
}

int delete(OfferRepo* repo,char const* destination,char const* date)
{
    for(int i=0; i < get_length_arr(repo->offers); i++)
        if(strcmp(destination,get_destination(get(repo->offers,i)))==0 && strcmp(date,get_date(get(repo->offers,i)))==0)
        {
            delete_elem(repo->offers,i);
            return 1;
        }
    return 0;
}

int update(OfferRepo* repo,char const* destination, char const* date, Offer* new_o)
{
    for (int i=0; i < get_length_arr(repo->offers); i++)
        if(strcmp(destination,get_destination(get(repo->offers,i)))==0 && strcmp(date,get_date(get(repo->offers,i)))==0){
            update_elem(repo->offers,i,new_o);
            return 1;
        }
    destroy_offer(new_o);
    return 0;
}

int get_length(OfferRepo* repo)
{
    return get_length_arr(repo->offers);
}

Offer* get_offer_on_pos(OfferRepo* repo,int pos)
{
    return get(repo->offers,pos);
}

Offer *get_offer_at_destination_and_date(OfferRepo *repo, char *destination, char *date)
{
    for (int i=0;i<get_length(repo);i++)
        if(strcmp(destination,get_destination(get(repo->offers,i)))==0 && strcmp(date,get_date(get(repo->offers,i)))==0)
            return get(repo->offers,i);
    return NULL;
}

void test_add(OfferRepo* r)
{
    int was_added;
    was_added=add(r, create_offer("mountain","Alps","20/04/2022",1800));
    assert(get_length(r)==1);
    assert(was_added==1);
    was_added=add(r, create_offer("mountain","Alps","20/04/2022",1800));
    assert(get_length(r)==1);
    assert(was_added==0);
}

void test_delete(OfferRepo* r)
{
    int was_deleted;
    was_deleted= delete(r,"l","01/01/2022");
    assert(was_deleted==0);
    assert(get_length(r)==1);
    was_deleted= delete(r,"Alps","20/04/2022");
    assert(was_deleted==1);
    assert(get_length(r)==0);
}
void test_update(OfferRepo* r)
{
    add(r, create_offer("mountain","Alps","20/04/2022",1800));
    Offer* o=create_offer("city break","Rome","05/05/2022",1200);
    int was_updated;
    was_updated= update(r,"Alps","20/04/2022",o);
    assert(was_updated==1);
    assert(get_type(get_offer_at_destination_and_date(r,get_destination(o), get_date(o)))== get_type(o));
    assert(get_destination(get_offer_at_destination_and_date(r,get_destination(o), get_date(o)))== get_destination(o));
    assert(get_date(get_offer_at_destination_and_date(r,get_destination(o), get_date(o)))== get_date(o));
    assert(get_price(get_offer_at_destination_and_date(r,get_destination(o), get_date(o)))== get_price(o));
    Offer* o2=create_offer("city break","Rome","05/05/2022",1200);
    was_updated= update(r,"l","01/01/2000",o2);
    assert(was_updated==0);
}

void test_repo()
{
    OfferRepo* r=create_repo();
    test_add(r);
    test_delete(r);
    test_update(r);
    destroy_repo(r);
}
