#include "Header files/Service.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <assert.h>

OfferService* create_service(OfferRepo* repo)
{
    OfferService* service = malloc(sizeof (OfferService));
    if (service==NULL)
        return NULL;
    service->repo=repo;
    service->history_len=0;
    service->history_index=-1;
    return service;
}

void destroy_service(OfferService* service)
{
    if(service==NULL)
        return;
    destroy_repo(service->repo);
    for(int i=0;i<service->history_len;i++)
    {
        destroy_operation(service->op_history[i][0]);
        destroy_operation(service->op_history[i][1]);
    }
    free(service);
}

int validate_type(char* type)
{
    return strcmp(type,"seaside")==0 || strcmp(type,"mountain")==0 || strcmp(type,"city break")==0;
}

int validate_date(char* date)
{
    int day,month,year;
    char *remaining;
    day=strtol(date,&remaining,10);
    if(day<1)
        return 0;
    remaining+=1;
    month=strtol(remaining,&remaining,10);
    if(month<1||month>12)
        return 0;
    remaining+=1;
    year= strtol(remaining,&remaining,10);
    if(year==0)
        return 0;
    if ((month==2&&day>28)||(((month%2==1&&month<=7)||(month%2==0&&month>=8))&&day>31)||(((month%2==0&&month<=7)||(month%2==1&&month>=8)&&month!=2)&&day>30))
        return 0;
    return 1;
}

int price_to_number(char* price)
{
    char *remaining;
    int price_number;
    price_number=strtol(price,&remaining,10);
    return price_number;
}

int add_offer(OfferService* service,char* type, char* destination, char* date,char* price)
{
    int price_number;
    price_number = price_to_number(price);
    if(validate_type(type)==0||validate_date(date)==0||price_number==0)
        return 0;

    Offer* o= create_offer(type,destination,date,price_number);
    Operation *undo_op = create_operation("delete",NULL , destination, date);
    Operation *redo_op = create_operation("add", o, NULL, NULL);
    int was_added=add(service->repo,o);
    if(was_added)
        record_operations(service, undo_op, redo_op);
    else
    {
        destroy_operation(undo_op);
        destroy_operation(redo_op);
    }
    return was_added;
}

int delete_offer(OfferService* service, char* destination, char* date)
{
    if(validate_date(date)==0)
        return 0;
    Operation* undo_op= create_operation("add",get_offer_at_destination_and_date(service->repo,destination,date),NULL,NULL);
    Operation* redo_op= create_operation("delete",NULL,destination,date);
    int was_deleted= delete(service->repo,destination,date);
    if(was_deleted)
        record_operations(service,undo_op,redo_op);
    else
    {
        destroy_operation(undo_op);
        destroy_operation(redo_op);
    }
    return was_deleted;
}

int update_offer(OfferService* service, char* destination, char* date,char* new_type, char* new_destination, char* new_date,char* new_price)
{
    int price_number;
    price_number = price_to_number(new_price);
    if(validate_type(new_type)==0||validate_date(new_date)==0||price_number==0)
        return 0;
    Offer* new_o= create_offer(new_type,new_destination,new_date,price_number);
    Operation* undo_op= create_operation("update",get_offer_at_destination_and_date(service->repo,destination,date),new_destination,new_date);
    Operation* redo_op= create_operation("update",new_o,destination,date);
    int was_updated= update(service->repo,destination,date,new_o);
    if(was_updated)
        record_operations(service,undo_op,redo_op);
    else
    {
        destroy_operation(undo_op);
        destroy_operation(redo_op);
    }
    return was_updated;
}

void order_array_by_price(Offer* array[],int length)
{
    Offer* aux;
    for(int i=0;i<length-1;i++)
    {
        for (int j = i + 1; j < length; j++)
            if (get_price(array[i]) > get_price(array[j])) {
                aux = array[i];
                array[i] = array[j];
                array[j] = aux;
            }
    }
}

int is_after_date(Offer* o,char* date)
{
    char offer_date[20],given_date[20];
    strcpy(offer_date, get_date(o));
    strcpy(given_date,date);
    if(strcmp(offer_date+6,given_date+6)<0)
        return 0;
    strcpy(offer_date+5,"");
    strcpy(given_date+5,"");
    if(strcmp(offer_date+3,given_date+3)<0)
        return 0;
    strcpy(offer_date+2,"");
    strcpy(given_date+2,"");
    if(strcmp(offer_date,given_date)<0)
        return 0;
    return 1;

}

void filter_by_destination(OfferService* service,Offer* array[],int* length, char* string)
{
    for (int i = 0; i < get_length(service->repo); i++)
        if (strstr(get_destination(get_offer_on_pos(service->repo, i)), string) != NULL)
            array[(*length)++] = get_offer_on_pos(service->repo, i);
    order_array_by_price(array,*length);
}

void filter_by_type(OfferService *service, Offer **array, int *length, char *string)
{
    for (int i = 0; i < get_length(service->repo); i++)
        if (strstr(get_type(get_offer_on_pos(service->repo, i)), string) != NULL)
            array[(*length)++] = get_offer_on_pos(service->repo, i);
    order_array_by_price(array,*length);
}

void offers_for_given_type_after_date(OfferService* service,Offer* array[],int * length, char* type,char* date)
{
    for (int i = 0; i < get_length(service->repo); i++)
        if(strcmp(get_type(get_offer_on_pos(service->repo,i)),type)==0&&is_after_date(get_offer_on_pos(service->repo,i),date)==1)
            array[(*length)++] = get_offer_on_pos(service->repo, i);
}

void record_operations(OfferService* service,Operation* undo_op,Operation* redo_op)
{
    if(service->history_index!=service->history_len-1)
        for(int i=service->history_index+1;i<service->history_len;i++)
        {
            destroy_operation(service->op_history[i][0]);
            destroy_operation(service->op_history[i][1]);
        }
    service->history_len= service->history_index+1;
    service->op_history[service->history_len][0]=undo_op;
    service->op_history[service->history_len][1]=redo_op;
    service->history_len++;
    service->history_index=service->history_len-1;
}

int undo_redo(OfferService *service,int index)
{
    if ((service->history_index==-1&&index==0)||(service->history_index==service->history_len-1&&index==1))
        return 0;
    int good;
    if(index==1)
        service->history_index++;
    if (strcmp(get_op_type(service->op_history[service->history_index][index]),"add")==0)
        good=add(service->repo, get_op_offer(service->op_history[service->history_index][index]));
    if (strcmp(get_op_type(service->op_history[service->history_index][index]),"delete")==0)
        good=delete(service->repo, get_op_destination(service->op_history[service->history_index][index]), get_op_date(service->op_history[service->history_index][index]));
    if (strcmp(get_op_type(service->op_history[service->history_index][index]),"update")==0)
        good=update(service->repo,get_op_destination(service->op_history[service->history_index][index]), get_op_date(service->op_history[service->history_index][index]),get_op_offer(service->op_history[service->history_index][0]));
    if(index==0)
        service->history_index--;
    return good;
}
void test_add_s(OfferService* s)
{
    int was_added;
    was_added= add_offer(s,"mountain","Alps","20/04/2022","1800");
    assert(was_added==1);
    was_added= add_offer(s,"mountain","Alps","20/04/2022","1800");
    assert(was_added==0);
    was_added= add_offer(s,"mountain","Alps","39/04/2022","1800");
    assert(was_added==0);
    was_added= add_offer(s,"mountain","Alps","20/40/fass","1800");
    assert(was_added==0);
    was_added= add_offer(s,"mountain","Alps","20/04/2022","fsad");
    assert(was_added==0);
}

void test_delete_s(OfferService* s)
{
    int was_deleted;
    was_deleted= delete_offer(s,"Alps","20/50/2022");
    assert(was_deleted==0);
    was_deleted= delete_offer(s,"Alps","20/04/2022");
    assert(was_deleted==1);
}

void test_update_s(OfferService* s)
{
    int was_updated;
    add_offer(s,"mountain","Alps","20/04/2022","1800");
    was_updated= update_offer(s,"Alps","20/04/2022","city break","Rome","05/05/2022","1200");
    assert(was_updated==1);
    was_updated= update_offer(s,"Alps","20/04/2022","city bre","Rome","05/50/2022","12a0");
    assert(was_updated==0);
}

void test_filter_by_dest(OfferService* s)
{
    char string[30];
    int length=0;
    int *p=&length;
    Offer *array1[10],*array2[10];
    add(s->repo, create_offer("mountain","Alps","20/04/2022",1800));
    add(s->repo, create_offer("seaside","Mamaia","12/05/2022",400));
    add(s->repo, create_offer("city break","Paris","24/06/2022",2400));
    add(s->repo, create_offer("seaside","Barcelona","07/04/2022",1500));
    strcpy(string,"");
    filter_by_destination(s,array1,p,string);
    for(int i =0;i<length-1;i++)
        assert(get_price(array1[i])< get_price(array1[i+1]));
    assert(length== get_length(s->repo));
    length=0;
    strcpy(string,"r");
    filter_by_destination(s,array2,p,string);
    assert(length==2);
}
void test_filter_by_type(OfferService *s)
{
    char string[30];
    int length=0;
    int *p=&length;
    Offer *array1[10],*array2[10];
    strcpy(string,"");
    filter_by_type(s,array1,p,string);
    assert(length== get_length(s->repo));
    length=0;
    strcpy(string,"s");
    filter_by_type(s,array2,p,string);
    assert(length==2);

}

void test_offers_by_type_after_date(OfferService *s)
{
    char type[30],date[30];
    int length=0;
    int *p=&length;
    Offer* array[100];
    strcpy(type,"seaside");
    strcpy(date,"10/05/2022");
    offers_for_given_type_after_date(s,array,p,type,date);
    assert(length==1);
}

void test_undo_redo()
{
    OfferRepo * r=create_repo();
    OfferService * s= create_service(r);
    add_offer(s, "mountain", "Alps", "20/04/2022", "1800");
    add_offer(s, "city break", "Rome", "05/05/2022", "1200");
    add_offer(s, "seaside", "Mamaia", "12/05/2022", "400");
    undo_redo(s,0);///undo
    undo_redo(s,0);///undo
    assert(get_length(s->repo)==1);
    assert(s->history_len==3);
    assert(s->history_index==0);
    add_offer(s, "city break", "Paris", "24/06/2022", "2400");
    assert(s->history_len==2);
    assert(s->history_index==1);
    undo_redo(s,0);///undo
    undo_redo(s,1);///redo
    assert(s->history_len==2);
    assert(s->history_index==1);
    destroy_service(s);
}

void test_service()
{
    test_undo_redo();
    OfferRepo * r=create_repo();
    OfferService * s= create_service(r);
    test_add_s(s);
    test_delete_s(s);
    test_update_s(s);
    test_filter_by_dest(s);
    test_filter_by_type(s);
    test_offers_by_type_after_date(s);
    destroy_service(s);
}

