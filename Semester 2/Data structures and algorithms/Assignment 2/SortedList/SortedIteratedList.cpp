#include "ListIterator.h"
#include "SortedIteratedList.h"
#include <iostream>
using namespace std;
#include <exception>

SortedIteratedList::SortedIteratedList(Relation r) {
    this->r=r;
    this->itr=new ListIterator(*this);
    this->_first= nullptr;
    this->_size=0;
}

int SortedIteratedList::size() const {

	return this->_size;
}

bool SortedIteratedList::isEmpty() const {
	return this->_size == 0;
}

ListIterator SortedIteratedList::first() const
{
    itr->first();
	return *itr;
}

TComp SortedIteratedList::getElement(ListIterator poz) const
{
    if(!poz.valid())
        throw exception();
	return poz.ptr->val;
}

TComp SortedIteratedList::remove(ListIterator& poz)
{
    if(!poz.valid())
        throw exception();
    TComp removed=poz.ptr->val;
    itr->first();
    if(itr->ptr==poz.ptr)
    {
        this->_first=poz.ptr->next;
        delete poz.ptr;
        poz.ptr= this->_first;
    }
    else
    {
        while (itr->ptr->next !=nullptr &&itr->ptr->next!= poz.ptr)
            itr->next();
        itr->ptr->next=poz.ptr->next;
        delete poz.ptr;
        poz.ptr=itr->ptr->next;
    }
    this->_size--;
	return removed;
}

ListIterator SortedIteratedList::search(TComp e) const
{
    itr->first();
	while(itr->valid())
    {
        if (itr->ptr->val == e)
            return *itr;
        itr->next();
    }
    return *itr;
}

void SortedIteratedList::add(TComp e)
{
	itr->first();
    Node *n=new Node;
    n->val=e;
    if(this->_first== nullptr) {
        this->_first = new Node;
        this->_first->val=e;
        this->_first->next= nullptr;
    }
    else
        if(!r(itr->ptr->val,e)) {
            n->next = itr->ptr;
            this->_first=n;
        }
        else
        {
            while (itr->ptr->next!=nullptr && r(itr->ptr->next->val, e))
                itr->next();
            n->next=itr->ptr->next;
            itr->ptr->next=n;
        }
    this->_size++;
    itr->first();
}

SortedIteratedList::~SortedIteratedList()
{
    delete itr;
}
