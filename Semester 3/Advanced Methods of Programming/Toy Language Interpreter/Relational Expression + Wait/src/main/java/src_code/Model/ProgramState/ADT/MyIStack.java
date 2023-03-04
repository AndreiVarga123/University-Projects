package src_code.Model.ProgramState.ADT;

public interface MyIStack<T> {
    void push(T elem);
    T pop();
    T peek();
    boolean isEmpty();
    String toString();
}
