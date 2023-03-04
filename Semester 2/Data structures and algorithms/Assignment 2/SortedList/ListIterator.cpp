#include "ListIterator.h"
#include "SortedIteratedList.h"
#include <exception>

using namespace std;

ListIterator::ListIterator(const SortedIteratedList& list) : list(list)
{

    this->ptr= nullptr;
}

void ListIterator::first(){
    this->ptr=list._first;
}

void ListIterator::next(){
    if(this->ptr== nullptr)
        throw exception();
    this->ptr= this->ptr->next;
}

bool ListIterator::valid() const{
	return this->ptr!=nullptr;
}

TComp ListIterator::getCurrent() const{
	return this->ptr->val;
}

void ListIterator::jumpBackward(int k) {
    if(k<=0)
        throw exception();
    Node *ans=list._first;
    Node *current=list._first;
    for(int i=0;i<k;i++)
    {
        if(current==this->ptr)
        {
            this->ptr= nullptr;
            throw exception();
        }
        current=current->next;
    }
    while(this->ptr!=current)
    {
        current=current->next;
        ans=ans->next;
    }
    this->ptr=ans;
}


