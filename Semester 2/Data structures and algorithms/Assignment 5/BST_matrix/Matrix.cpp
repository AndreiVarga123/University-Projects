#include "Matrix.h"
#include "MatrixIterator.h"
#include <exception>
using namespace std;

Matrix::Matrix(int nrLines, int nrCols) {
	   
	nr_line=nrLines;
	nr_col=nrCols;
	root= nullptr;
}


int Matrix::nrLines() const {
	return nr_line;
}


int Matrix::nrColumns() const {
	return nr_col;
}

TElem Matrix::element(int i, int j) const {
	if (i>nr_line||i<0||j>nr_col||j<0)
        throw exception();

    Node* current = root;
    while(current!= nullptr)
    {
        if (i == current->line && j == current->col)
            return current->val;
        else if (i > current->line || (i == current->line && j > current->col))
            current = current->right;
        else
            current = current->left;
    }

    return NULL_TELEM;

}

TElem Matrix::modify(int i, int j, TElem e)
{
    if (i>nr_line||i<0||j>nr_col||j<0)
        throw exception();

    Node* current = root;
    while(current!= nullptr)
    {
        if (i == current->line && j == current->col)
        {
            TElem old_val=current->val;
            current->val=e;
            return old_val;
        }
        else if (i > current->line || (i == current->line && j > current->col))
            current = current->right;
        else
            current = current->left;
    }

    current=root;

    Node *n = new Node;
    n->line = i;
    n->col = j;
    n->val = e;
    n->left = nullptr;
    n->right = nullptr;

    if(root== nullptr)
    {
        root= n;
    }
    else
    {
        while (true)
        {
            if (n->line > current->line || (n->line == current->line && n->col > current->col))
            {
                if (current->right == nullptr)
                {
                    current->right = n;
                    break;
                } else
                    current = current->right;
            } else
            {
                if (current->left == nullptr)
                {
                    current->left = n;
                    break;
                } else
                    current = current->left;
            }
        }
    }

    return NULL_TELEM;

}

MatrixIterator Matrix::iterator(int line) const
{
    return MatrixIterator(*this,line);
}
