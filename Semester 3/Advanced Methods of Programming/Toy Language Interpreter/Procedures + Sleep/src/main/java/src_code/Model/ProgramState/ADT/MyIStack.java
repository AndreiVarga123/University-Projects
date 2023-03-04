package src_code.Model.ProgramState.ADT;

import src_code.Model.Value.Value;

public interface MyIStack<T> {
    void push(T elem);
    T pop();
    T peek();
    boolean isEmpty();
    String toString();
    MyIStack<MyIDict<String, Value>> deepCopy();
}
