package src_code.Model.ProgramState.ADT;

import src_code.Model.Value.Value;

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

    @Override
    public MyIStack<MyIDict<String, Value>> deepCopy() {
        MyIStack<MyIDict<String, Value>> aux = new MyStack<>(new Stack<>());
        MyIStack<MyIDict<String, Value>> fin = new MyStack<>(new Stack<>());

        while(!this.isEmpty()){
            aux.push((MyIDict<String, Value>) this.pop());
        }

        while(!aux.isEmpty()){
            fin.push(aux.peek().deepCopy());
            this.push((T) aux.pop());
        }
        return fin;
    }
}
