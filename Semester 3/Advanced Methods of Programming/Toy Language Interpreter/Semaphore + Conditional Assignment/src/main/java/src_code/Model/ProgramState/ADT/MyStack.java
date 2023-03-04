package src_code.Model.ProgramState.ADT;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> myStack;

    public MyStack(Stack<T> myStack) {
        this.myStack = myStack;
    }

    public Stack<T> getMyStack() {
        return myStack;
    }

    @Override
    public void push(T elem) {
        myStack.push(elem);
    }

    @Override
    public T pop() {
        return myStack.pop();
    }

    @Override
    public boolean isEmpty() {
        return myStack.isEmpty();
    }

    @Override
    public String toString() {
        return myStack.toString();
    }

    @Override
    public T peek() {
        return myStack.peek();
    }
}
