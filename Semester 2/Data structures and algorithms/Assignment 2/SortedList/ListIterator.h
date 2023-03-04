#pragma once
#include "SortedIteratedList.h"

//DO NOT CHANGE THIS PART
class ListIterator{
	friend class SortedIteratedList;
    friend class Node;
private:
    Node *ptr;
	const SortedIteratedList& list;
	explicit ListIterator(const SortedIteratedList& list);
public:
	void first();
	void next();
	bool valid() const;
    TComp getCurrent() const;
    void jumpBackward(int k);
};
