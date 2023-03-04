#pragma once
#include <tuple>
using namespace std;

typedef int TElem;
#define NULL_TELEM 0

class MatrixIterator
{
private:
    TElem *ptr;
    int col;
    TElem *_start;
    TElem *_end;
public:
    MatrixIterator(TElem *data,int size);
    TElem *start();
    TElem *end();
    TElem  *next();
    TElem  *prev();
    ~MatrixIterator();
};
