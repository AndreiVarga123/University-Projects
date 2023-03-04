package src_code.Model.ProgramState.ADT;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MyTable<T,G> implements MyITable<T,G> {

    Map<T,G> myDict;
    Stack<Integer> free_locs;
    Integer free_loc;

    public MyTable(Map<T, G> myDict) {
        this.myDict = myDict;
        this.free_locs = new Stack<>();
        this.free_loc = 1;
    }

    public Map<T, G> getMyHeap() {
        synchronized (this) {
            return myDict;
        }
    }

    @Override
    public T add(G value) {
        synchronized (this) {
            T key = this.getFree();
            myDict.put(key, value);
            return key;
        }
    }

    @Override
    public void update(T key, G value) {
        synchronized (this) {
            myDict.put(key, value);
        }
    }

    @Override
    public G lookup(T key) {
        synchronized (this) {
            return myDict.get(key);
        }
    }

    @Override
    public boolean isDefined(T key) {
        synchronized (this) {
            return myDict.get(key) != null;
        }
    }

    @Override
    public String toString() {
        synchronized (this) {
            return myDict.toString();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return myDict.isEmpty();
        }

    }

    @Override
    public void remove(T key) {
        synchronized (this) {
            myDict.remove(key);
            free_locs.push((Integer) key);
        }
    }

    @Override
    public Set<Map.Entry<T, G>> getSet() {
        synchronized (this) {
            return myDict.entrySet();
        }
    }

    private T getFree(){
        synchronized (this) {
            return (T) (free_locs.isEmpty() ? this.free_loc++ : free_locs.pop());
        }
    }

    @Override
    public void setContent(Map<T, G> newMap) {
        synchronized (this) {
            myDict.clear();
            for (T i : newMap.keySet()) {
                myDict.put(i, newMap.get(i));
            }
        }
    }

    @Override
    public Map<T, G> getContent() {
        synchronized (this) {
            return myDict;
        }
    }
}
