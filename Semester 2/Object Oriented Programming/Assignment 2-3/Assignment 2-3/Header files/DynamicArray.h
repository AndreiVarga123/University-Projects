#pragma once
#define CAPACITY 10

typedef void* TElement;
typedef void (*DestroyElementFunctionType)(void*);

typedef struct
{
    TElement* elems;
    int length;
    int capacity;
    DestroyElementFunctionType destroyElemFct;
} DynamicArray;

DynamicArray* create_arr(int capacity, DestroyElementFunctionType destroyElemFct);
void destroy_arr(DynamicArray* arr);
void add_elem(DynamicArray* arr, TElement t);
void delete_elem(DynamicArray* arr, int pos);
void update_elem(DynamicArray* arr,int pos, TElement t);
int get_length_arr(DynamicArray* arr);
TElement get(DynamicArray* arr, int pos);
