package src_code.Model.ProgramState.ADT;

import java.util.Iterator;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    List<T> myList;

    public MyList(List<T> myList) {
        this.myList = myList;
    }

    public List<T> getMyList() {
        synchronized (this) {
            return myList;
        }
    }

    @Override
    public void add(T elem) {
        synchronized (this) {
            myList.add(elem);
        }
    }

    @Override
    public void remove(int pos) {
        synchronized (this) {
            myList.remove(pos);
        }
    }

    @Override
    public String toString() {
        synchronized (this) {
            return myList.toString();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return myList.isEmpty();
        }
    }

    @Override
    public Iterator<T> getIter() {
        synchronized (this) {
            return myList.listIterator();
        }
    }

    @Override
    public int size() {
        return myList.size();
    }
}
