#include "MultiMap.h"
#include "MultiMapIterator.h"
#include <exception>
#include <iostream>

using namespace std;


MultiMap::MultiMap() {
	this->dict_size=0;
    this->dict_capacity=10;
    this->dict= new Node[this->dict_capacity];
    for(int i=0;i<this->dict_size;i++)
        dict[i].next = -1;
    this->first_free=-1;
    this->_size=0;
}


void MultiMap::add(TKey c, TValue v) {
	int pos=this->hash_function(c);
    if(dict[pos].occupied&&dict_size+1>=dict_capacity)
        resize();
    else
    {
        int current=pos;
        while(dict[current].key!=c&&dict[current].next!=-1)
            current=dict[current].next;
        if(dict[current].next==-1&&dict[current].key!=c&&dict_size+1>=dict_capacity)
            resize();

    }

    pos=this->hash_function(c);
    if(!dict[pos].occupied)
    {
        dict_size++;
        dict[pos].key=c;
        dict[pos].values[0]=v;
        dict[pos].size++;
        dict[pos].occupied= true;
    }
    else
    {
        int current=pos;
        while(dict[current].key!=c&&dict[current].next!=-1)
            current=dict[current].next;
        if(dict[current].next==-1&&dict[current].key!=c)
        {
            dict_size++;
            first_free=get_first_free();
            dict[first_free].key=c;
            dict[first_free].values[0]=v;
            dict[first_free].size++;
            dict[first_free].occupied= true;
            dict[current].next=first_free;
        }
        else
        {
            dict[current].size++;
            if(dict[current].size>=dict[current].capacity)
                resize(dict[current]);
            dict[current].values[dict[current].size-1]=v;

        }
    }
    this->_size++;
}


bool MultiMap::remove(TKey c, TValue v) {
    int current= hash_function(c);
    if(!dict[current].occupied)
        return false;
    while(dict[current].key!=c&&dict[current].next!=-1)
        current=dict[current].next;
    if(dict[current].next==-1&&dict[current].key!=c)
        return false;
    for(int i=0;i<dict[current].size;i++)
        if(dict[current].values[i]==v)
        {
            for(int j=i+1;j<dict[current].size;j++)
                dict[current].values[j-1]=dict[current].values[j];
            dict[current].size--;
            if(dict[current].size==0)
            {
                int pos=hash_function(c);
                if(dict[pos].key==c)
                {
                    int elim=dict[pos].next;
                    if(elim!=-1)
                    {
                        dict[pos] = dict[elim];
                    }
                    else
                        elim=pos;

                    dict[elim].key=0;
                    dict[elim].size=0;
                    dict[elim].occupied=false;
                    dict[elim].next=-1;
                }
                else
                {
                    while(dict[dict[pos].next].key!=c)
                        pos=dict[pos].next;
                    int elim=dict[pos].next;
                    dict[pos].next=dict[elim].next;

                    dict[elim].key=0;
                    dict[elim].size=0;
                    dict[elim].occupied = false;
                    dict[elim].next = -1;
                }
                dict_size--;
            }
            this->_size--;
            return true;
        }
    return false;
}


vector<TValue> MultiMap::search(TKey c) const
{

    vector<TValue>arr;
	int pos= hash_function(c);
    while(dict[pos].key!=c&&dict[pos].next!=-1)
        pos=dict[pos].next;
    if(dict[pos].key!=c&&dict[pos].next==-1)
        return arr;
    for(int i=0;i<dict[pos].size;i++)
        arr.push_back(dict[pos].values[i]);
    return arr;
}


int MultiMap::size() const {
    return this->_size;
}


bool MultiMap::isEmpty() const {
    return this->dict_size==0;

}

MultiMapIterator MultiMap::iterator() const {
	return MultiMapIterator(*this);
}


MultiMap::~MultiMap() {
	delete[] dict;
}

int MultiMap::hash_function(TKey key) const {
    if (key < 0)
        key = this->dict_capacity+key%this->dict_capacity;
    return key%this->dict_capacity;
}

void MultiMap::resize()
{
    this->dict_capacity*=2;
    Node* els=new Node[this->dict_capacity];
    for (int i = 0; i < this->dict_capacity/2; i++)
    {
        if(dict[i].occupied)
        {
            int pos = this->hash_function(dict[i].key);
            if(!els[pos].occupied)
            {
                els[pos] = dict[i];
                els[pos].next=-1;
            }
            else
            {
                for(int j=dict_capacity-1;j>=0;j--)
                    if(!els[j].occupied)
                    {
                        first_free=j;
                        break;
                    }
                int current=pos;
                while(els[current].next!=-1)
                    current=els[current].next;
                els[first_free]=dict[i];
                els[first_free].next=-1;
                els[current].next=first_free;
            }
        }
    }
    delete[] this->dict;
    this->dict=els;
}

void MultiMap::resize(MultiMap::Node& node)
{
    node.capacity *= 2;
    auto* els = new TValue[node.capacity];
    for (int i = 0; i < node.size; i++)
        els[i] = node.values[i];

    delete[] node.values;
    node.values = els;
}



int MultiMap::get_first_free() {
    for(int i=dict_capacity-1;i>=0;i--)
        if(!dict[i].occupied)
            return i;
    return -1;
}

MultiMap &MultiMap::operator=(const MultiMap& m)
{
    if(this==&m)
        return *this;

    this->dict_size=m.dict_size;
    this->dict_capacity=m.dict_capacity;

    delete[] this->dict;
    this->dict= new Node[this->dict_capacity];
    for(int i=0;i<this->dict_size;i++)
        dict[i]=m.dict[i];

    this->first_free=-m.first_free;
    this->_size=m._size;

    return *this;
}

MultiMap::MultiMap(const MultiMap& m) {
    this->dict_size=m.dict_size;
    this->dict_capacity=m.dict_capacity;

    delete[] this->dict;
    this->dict= new Node[this->dict_capacity];
    for(int i=0;i<this->dict_size;i++)
        dict[i]=m.dict[i];

    this->first_free=-m.first_free;
    this->_size=m._size;
}

void MultiMap::empty() {
    this->dict_size=0;
    this->dict_capacity=10;
    this->first_free=-1;
    this->_size=0;
    delete[] this->dict;
    this->dict= new Node[this->dict_capacity];
    for(int i=0;i<this->dict_size;i++)
        dict[i].next = -1;
}

MultiMap::Node::Node() {
    this->next =-1;
    this->key=0;
    this->size=0;
    this->capacity=10;
    this->values=new TValue [this->capacity];
    this->occupied=false;
}

MultiMap::Node::Node(const MultiMap::Node &n) {
    this->next=n.next;
    this->key=n.key;
    this->size=n.size;
    this->capacity=n.capacity;
    this->occupied=n.occupied;

    delete[] values;
    this->values=new TValue [this->capacity];
    for(int i=0;i<this->size;i++)
        this->values[i]=n.values[i];
}

MultiMap::Node &MultiMap::Node::operator=(const MultiMap::Node &n) {
    if(this==&n)
        return *this;

    this->next=n.next;
    this->key=n.key;
    this->size=n.size;
    this->capacity=n.capacity;
    this->occupied=n.occupied;

    delete[] values;
    this->values=new TValue [this->capacity];
    for(int i=0;i<this->size;i++)
        this->values[i]=n.values[i];

    return *this;
}
