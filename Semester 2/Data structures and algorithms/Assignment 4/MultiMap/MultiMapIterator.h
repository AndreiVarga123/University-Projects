#pragma once
#include "MultiMap.h"

class MultiMap;

class MultiMapIterator
{
	friend class MultiMap;

private:
	const MultiMap& col;
	//TODO - Representation

	//DO NOT CHANGE THIS PART
	MultiMapIterator(const MultiMap& c);
    int current_key;
    int current_value;

public:
	TElem getCurrent()const;
	bool valid() const;
	void next();
	void first();
};

