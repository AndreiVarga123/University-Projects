#include "Header files/DynamicArray.h"
#include <stdlib.h>
#include <assert.h>

DynamicArray* create_arr(int capacity, DestroyElementFunctionType destroyElemFct)
{
    DynamicArray* da = (DynamicArray*)malloc(sizeof(DynamicArray));
    if (da == NULL)
        return NULL;

    da->capacity = capacity;
    da->length = 0;

    da->elems = (TElement*)malloc(capacity * sizeof(TElement));
    if (da->elems == NULL)
        return NULL;

    da->destroyElemFct = destroyElemFct;

    return da;
}

void destroy_arr(DynamicArray* arr)
{
    if (arr == NULL)
        return;

    for (int i = 0; i < arr->length; i++)
        arr->destroyElemFct(arr->elems[i]);

    free(arr->elems);
    arr->elems = NULL;

    free(arr);
    arr = NULL;
}

int resize_arr(DynamicArray* arr)
{
    if (arr == NULL)
        return -1;

    arr->capacity *= 2;

    TElement* aux = (TElement*)malloc(arr->capacity * sizeof(TElement));
    if (aux == NULL)
        return -1;
    for (int i = 0; i < arr->length; i++)
        aux[i] = arr->elems[i];
    free(arr->elems);
    arr->elems = aux;

    return 0;
}

void add_elem(DynamicArray* arr, TElement t)
{
    if (arr == NULL)
        return;
    if (arr->elems == NULL)
        return;

    if (arr->length == arr->capacity)
        resize_arr(arr);
    arr->elems[arr->length++] = t;
}

void delete_elem(DynamicArray* arr, int pos)
{
    if (arr == NULL || pos < 0 || pos >= arr->length)
        return;

    arr->destroyElemFct(arr->elems[pos]);

    if (pos != arr->length - 1)
        arr->elems[pos] = arr->elems[arr->length - 1];
    arr->length--;

}

void update_elem(DynamicArray* arr,int pos, TElement t)
{
    if (arr == NULL || pos < 0 || pos >= arr->length)
        return;
    arr->elems[pos]=t;
}

int get_length_arr(DynamicArray* arr)
{
    if (arr == NULL)
        return -1;

    return arr->length;
}

TElement get(DynamicArray* arr, int pos)
{
    if (arr == NULL || pos < 0)
        return NULL;
    return arr->elems[pos];
}
