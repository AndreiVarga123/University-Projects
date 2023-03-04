#include "ListIterator.h"
#include "SortedIteratedList.h"
#include <iostream>
using namespace std;
#include <exception>

SortedIteratedList::SortedIteratedList(Relation r) {
    this->r=r;
    this->cap=10;
    this->_size=0;
    this->head=-1;
    this->firstFree=0;

    this->itr=new ListIterator(*this);
}

int SortedIteratedList::size() const {
    return this->_size;
}

bool SortedIteratedList::isEmpty() const {
    return this->_size==0;
}

ListIterator SortedIteratedList::first() const {
    this->itr->first();
    return *this->itr;
}

TComp SortedIteratedList::getElement(ListIterator poz) const {
    if(!poz.valid())
        throw exception();
	return this->arr[poz.current].data;
}

TComp SortedIteratedList::remove(ListIterator& poz) {
	if(!poz.valid())
        throw exception();
    TComp removed=arr[poz.current].data;
    itr->first();
    if(itr->current==poz.current){
        this->head=arr[poz.current].next;
        arr[poz.current].next=-2;
        poz.current= this->head;
        if(this->firstFree>poz.current)
            this->firstFree=poz.current;
    }
    else
    {
        while(arr[itr->current].next!=-1 && arr[itr->current].next!=poz.current)
            itr->next();
        arr[itr->current].next=arr[poz.current].next;
        arr[poz.current].next=-2;
        poz.current=arr[itr->current].next;
        if(this->firstFree>poz.current)
            this->firstFree=poz.current;
    }
    this->_size--;
    return removed;
}

ListIterator SortedIteratedList::search(TComp e) const{
	itr->first();
    while(itr->valid())
    {
        if (this->arr[itr->current].data==e)
            return *itr;
        itr->next();
    }
    return *itr;

}

void SortedIteratedList::add(TComp e) {
    this->_size++;
    if(this->_size>this->cap)
        this->resize();
	itr->first();
    if(this->head==-1)
    {
        arr[0].data=e;
        arr[0].next=-1;
        this->firstFree=1;
        this->head=0;
    }
    else if(!r(arr[itr->current].data,e))
    {
        arr[this->firstFree].data=e;
        arr[this->firstFree].next=this->head;
        this->head= this->firstFree;
        this->firstFree=get_first_free();
    }
    else
    {
        while(arr[this->itr->current].next!=-1 && r(arr[arr[itr->current].next].data,e))
            itr->next();
        arr[this->firstFree].data=e;
        arr[this->firstFree].next=arr[itr->current].next;
        arr[itr->current].next= this->firstFree;
        this->firstFree=get_first_free();
    }
}

SortedIteratedList::~SortedIteratedList() {
    delete itr;
	delete[] arr;
}

int SortedIteratedList::get_first_free() {
    for(int i=0;i< this->_size;i++)
        if(arr[i].next==-2)
            return i;
    return this->_size;
}

void SortedIteratedList::resize() {
    this->cap *=2;
    auto *els= new Node[this->cap];
    for(int i=0;i< this->_size;i++)
        els[i]=this->arr[i];

    delete[] this->arr;
    this->arr =els;
}

SortedIteratedList::SortedIteratedList(const SortedIteratedList &sil) {
    this->r=sil.r;
    this->cap=sil.cap;
    this->_size=sil._size;
    this->head=sil.head;
    this->firstFree=sil.firstFree;

    delete[] this->arr;
    this->arr= new Node[this->cap];
    for(int i=0;i< this->size();i++)
        this->arr[i]=sil.arr[i];

    this->itr=sil.itr;
}

SortedIteratedList &SortedIteratedList::operator=(const SortedIteratedList &sil) {
    if(this==&sil)
        return *this;

    this->r=sil.r;
    this->cap=sil.cap;
    this->_size=sil._size;
    this->head=sil.head;
    this->firstFree=sil.firstFree;

    delete[] this->arr;
    this->arr= new Node[this->cap];
    for(int i=0;i< this->size();i++)
        this->arr[i]=sil.arr[i];

    this->itr=sil.itr;

    return *this;
}

void SortedIteratedList::empty() {
    this->cap=10;
    this->_size=0;
    this->head=-1;
    this->firstFree=0;
    delete[] this->arr;
    this->arr=new Node[cap];
    for (int i=0;i<cap-1;i++)
        arr[i].next=i+1;
    arr[cap-1].next=-1;
    this->itr->first();
}
