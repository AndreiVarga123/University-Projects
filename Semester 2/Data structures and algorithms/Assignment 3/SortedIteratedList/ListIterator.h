#pragma once
#include "SortedIteratedList.h"

//DO NOT CHANGE THIS PART
class ListIterator{
	friend class SortedIteratedList;
private:
    int current;
	const SortedIteratedList& list;
	ListIterator(const SortedIteratedList& list);

	//TODO - Representation
public:
	void first();
	void next();
	bool valid() const;
    TComp getCurrent() const;
};


