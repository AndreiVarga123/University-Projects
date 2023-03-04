#include "MultiMapIterator.h"
#include "MultiMap.h"


MultiMapIterator::MultiMapIterator(const MultiMap& c): col(c) {
    current_key=0;
    if(col.dict_size>0)
        while(!col.dict[current_key].occupied)
            current_key++;
    else
        current_key=col.dict_capacity;
    current_value=0;
}

TElem MultiMapIterator::getCurrent() const{
    if(!this->valid())
        throw exception();
    return make_pair(col.dict[current_key].key,col.dict[current_key].values[current_value]);
}

bool MultiMapIterator::valid() const {
    if(current_key<col.dict_capacity&&col.dict[current_key].size>current_value)
        return true;
    return false;
}

void MultiMapIterator::next() {
    if(!this->valid())
        throw exception();
    if(col.dict[current_key].size-1==current_value) {
        current_key++;
        while (!col.dict[current_key].occupied&&current_key<col.dict_capacity)
            current_key++;
        current_value = 0;
    }
    else
        current_value++;
}

void MultiMapIterator::first() {
        current_key=0;
        while(!col.dict[current_key].occupied)
            current_key++;
        current_value=0;
}

