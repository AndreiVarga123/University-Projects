//
// Created by varga on 5/26/2022.
//

#include "Matrix.h"
typedef int TElem;
#define NULL_TELEM 0

class Matrix;

struct Node;

class MatrixIterator{
    friend class Matrix;

private:
    Node* current;
    const Matrix& matrix;
    int line;
    int col;
    bool on_null;

public:
    MatrixIterator(const Matrix& m,int l);
    void first();
    void next();
    bool valid();
    TElem get_current();


};
