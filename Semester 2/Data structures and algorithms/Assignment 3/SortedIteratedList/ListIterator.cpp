#include "ListIterator.h"
#include "SortedIteratedList.h"
#include <exception>

using namespace std;

ListIterator::ListIterator(const SortedIteratedList& list) : list(list){
    this->current=-1;
}

void ListIterator::first(){
    this->current= list.head;
}

void ListIterator::next(){
    if(this->current==-1)
        throw exception();
    this->current=list.arr[this->current].next;
}

bool ListIterator::valid() const{
	return this->current!=-1;
}

TComp ListIterator::getCurrent() const{
    if(this->current==-1)
        throw exception();
	return list.arr[this->current].data;
}


