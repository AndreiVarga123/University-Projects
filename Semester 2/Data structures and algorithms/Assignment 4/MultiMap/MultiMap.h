#pragma once
#include<vector>
#include<utility>
//DO NOT INCLUDE MultiMapIterator

using namespace std;

//DO NOT CHANGE THIS PART
typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
#define NULL_TVALUE -111111
#define NULL_TELEM pair<int,int>(-111111, -111111)
class MultiMapIterator;

class MultiMap
{
	friend class MultiMapIterator;

private:
	//TODO - Representation
    struct Node{
        TKey key;
        int size,capacity;
        TValue *values;
        int next;
        bool occupied;
        Node();
        Node(const Node& n);
        Node& operator=(const Node& n);
    };
    Node* dict;
    int dict_size,dict_capacity;
    int first_free;
    int _size;

    int hash_function(TKey key)const;

    void resize();

    static void resize(Node& node);
    MultiMap(const MultiMap& m);
    MultiMap& operator=(const MultiMap& m);

    int get_first_free();

public:
	//constructor
	MultiMap();

	//adds a key value pair to the multimap
	void add(TKey c, TValue v);

	//removes a key value pair from the multimap
	//returns true if the pair was removed (if it was in the multimap) and false otherwise
	bool remove(TKey c, TValue v);

	//returns the vector of values associated to a key. If the key is not in the MultiMap, the vector is empty
	vector<TValue> search(TKey c) const;

	//returns the number of pairs from the multimap
	int size() const;

	//checks whether the multimap is empty
	bool isEmpty() const;

	//returns an iterator for the multimap
	MultiMapIterator iterator() const;

    void empty();

	//descturctor
	~MultiMap();


};

