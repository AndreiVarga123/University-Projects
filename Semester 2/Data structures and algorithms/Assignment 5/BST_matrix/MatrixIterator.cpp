//
// Created by varga on 5/26/2022.
//

#include "MatrixIterator.h"
#include <exception>

MatrixIterator::MatrixIterator(const Matrix &m,int l):matrix(m)
{
    if(l>matrix.nrLines()||l<0)
        throw std::exception();
    line=l;
    on_null= false;
    col=0;
    this->first();
}

void MatrixIterator::first()
{
    current=matrix.root;
    while(current!= nullptr)
        if(current->line>line)
        {
            current=current->left;
        }
        else if(current->line<line)
        {
            current=current->right;
        }
        else
        {
            col=0;
            break;
        }
}

void MatrixIterator::next()
{
    if(current== nullptr)
    {
        on_null= true;
        col++;
        return;
    }
    if(on_null)
    {
        if(current->col==col-1)
            on_null= false;
        col++;
    }
    else {
        while (current != nullptr)
            if (current->line > line) {
                current = current->left;
            } else if (current->line < line) {
                current = current->right;
            } else {
                if (col != current->col - 1) {
                    on_null = true;
                    col++;
                }
                break;
            }
    }

}

TElem MatrixIterator::get_current()
{
    if(on_null)
        return NULL_TELEM;
    return current->val;
}

bool MatrixIterator::valid() {
    return col<matrix.nrColumns();

}
