#pragma once
#include <tuple>
#include "MatrixIterator.h"

//DO NOT CHANGE THIS PART
typedef int TElem;
#define NULL_TELEM 0

class Matrix
{

private:
	std::tuple<int,int,TElem> *elems;
    int nr_lines,nr_cols;
    int size,capacity;

    void resize();
    int search_elem(int i,int j) const;
public:
	//constructor
	Matrix(int nrLines, int nrCols);

    Matrix(const Matrix& m);
    ~Matrix();

    Matrix& operator=(const Matrix& m);
	//returns the number of lines
	int nrLines() const;

	//returns the number of columns
	int nrColumns() const;

	//returns the element from line i and column j (indexing starts from 0)
	//throws exception if (i,j) is not a valid position in the Matrix
	TElem element(int i, int j) const;

	//modifies the value from line i and column j
	//returns the previous value from the position
	//throws exception if (i,j) is not a valid position in the Matrix
	TElem modify(int i, int j, TElem e);

    MatrixIterator iterator(int line) const;

};
