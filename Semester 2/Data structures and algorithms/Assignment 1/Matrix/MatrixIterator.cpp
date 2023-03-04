//
// Created by varga on 4/1/2022.
//

#include "MatrixIterator.h"

MatrixIterator::MatrixIterator(TElem *data,int size)
{
    this->ptr=data;
    this->_start=data;
    this->_end=*(&data + size - 1);
    this->col=0;
}

TElem *MatrixIterator::next()
{
    this->ptr=*(&this->ptr+1);
    return this->ptr;
}

TElem *MatrixIterator::prev()
{
    this->ptr=*(&this->ptr+1);
    return this->ptr;
}

TElem *MatrixIterator::start() {
    return this->_start;
}

TElem *MatrixIterator::end() {
    return this->_end;
}

MatrixIterator::~MatrixIterator()=default;
