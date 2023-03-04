package src_code.Model.ProgramState.ADT;

import java.util.Iterator;

public interface MyIList<T> {
    void add(T elem);
    void remove(int pos);
    String toString();
    boolean isEmpty();
    Iterator<T> getIter();
    int size();
}
