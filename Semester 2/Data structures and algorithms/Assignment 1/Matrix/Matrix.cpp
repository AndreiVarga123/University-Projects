#include "Matrix.h"
#include <exception>
using namespace std;


Matrix::Matrix(int nrLines, int nrCols)
{
    this->nr_lines=nrLines;
    this->nr_cols=nrCols;
    this->size=0;
    this->capacity=10;
    this->elems= new std::tuple<int,int,TElem>[this->capacity];
}

void Matrix::resize()
{
    this->capacity *=2;
    auto *els=new std::tuple<int,int,TElem>[this->capacity];
    for (int i = 0; i < this->size; i++)
        els[i] = this->elems[i];

    delete[] this->elems;
    this->elems = els;
}

Matrix::Matrix(const Matrix &m)
{
    this->nr_lines=m.nr_lines;
    this->nr_cols=m.nr_cols;
    this->size=m.size;
    this->capacity=m.capacity;
    this->elems = new std::tuple<int,int,TElem>[this->capacity];
    for (int i = 0; i < this->size; i++)
        this->elems[i]=m.elems[i];
}

Matrix &Matrix::operator=(const Matrix &m)
{
    if(this == &m)
        return *this;

    this->nr_lines=m.nr_lines;
    this->nr_cols=m.nr_cols;
    this->size=m.size;
    this->capacity=m.capacity;

    delete[] this->elems;
    this->elems = new std::tuple<int,int,TElem>[this->capacity];
    for (int i = 0; i < this->size; i++)
        this->elems[i] = m.elems[i];

    return *this;
}


int Matrix::nrLines() const
{
    return this->nr_lines;
}


int Matrix::nrColumns() const
{
	return this->nr_cols;
}

int Matrix::search_elem(int i, int j) const
{
    int start=0,end=this->size-1,mid;
    while(start<=end) {
        mid = (start + end) / 2;
        if (get<0>(this->elems[mid]) == i && get<1>(this->elems[mid]) == j)
            return mid;
        if ((get<0>(this->elems[mid]) == i && get<1>(this->elems[mid]) < j) || get<0>(this->elems[mid]) < i)
            start = mid + 1;
        else
            end = mid - 1;
    }
    return -1;
}

TElem Matrix::element(int i, int j) const
{
    if(i>=this->nr_lines||i<0||j>=this->nr_cols||j<0)
        throw exception();
    int index= this->search_elem(i,j);
    if(index!=-1)
        return get<2>(this->elems[index]);
    return NULL_TELEM;
}

TElem Matrix::modify(int i, int j, TElem e)
{
    if(i>=this->nr_lines||i<0||j>=this->nr_cols||j<0)
        throw exception();
    int index= this->search_elem(i,j);
    if(index!=-1)
    {
        TElem old_value = get<2>(this->elems[index]);
        get<2>(this->elems[index])=e;
        return old_value;
    }
    this->size++;
    if(this->size>this->capacity)
        this->resize();
    int c;
    for(c=this->size-2;(c>=0&&(get<0>(this->elems[c])>i||(get<0>(this->elems[c])==i&&get<1>(this->elems[c])>j)));c--)
        this->elems[c+1]=this->elems[c];
    get<0>(this->elems[c+1]) = i;
    get<1>(this->elems[c+1]) = j;
    get<2>(this->elems[c+1]) = e;

    return NULL_TELEM;
}

Matrix::~Matrix()
{
    delete[] this->elems;
}

MatrixIterator Matrix::iterator(int line) const {
    if(line<0||line>=nr_lines)
        throw exception();
    TElem arr[nr_cols];
    for(int i=0;i<nr_cols;i++)
    {
        int elem_i= search_elem(line,i);
        if(elem_i!=-1)
            arr[i]=get<2>(this->elems[elem_i]);
        else
            arr[i]=NULL_TELEM;
    }
    MatrixIterator itr(arr,nr_cols);
    return itr;
}
