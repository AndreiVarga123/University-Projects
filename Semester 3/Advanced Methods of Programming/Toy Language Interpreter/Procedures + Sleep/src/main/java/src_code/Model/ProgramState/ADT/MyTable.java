package src_code.Model.ProgramState.ADT;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyTable<T,G> implements MyITable<T,G> {
    Map<T,G> myDict;

    public MyTable(Map<T, G> myDict) {
        this.myDict = myDict;
    }

    public Map<T, G> getMyDict() {
        return myDict;
    }

    @Override
    public void add(T key, G value) {
        synchronized (this) {
            myDict.put(key, value);
        }
    }

    @Override
    public void update(T key, G value) {
        synchronized (this){
            myDict.put(key,value);
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
    public Set<Map.Entry<T, G>> getSet() {
        synchronized (this) {
            return myDict.entrySet();
        }
    }

    @Override
    public void remove(T key) {
        synchronized (this) {
            myDict.remove(key);
        }
    }

    @Override
    public Map<T, G> getContent() {
        synchronized (this) {
            return myDict;
        }
    }

    @Override
    public MyIDict<T, G> deepCopy() {
        Map<T,G> copy = new HashMap<>();
        for(Map.Entry<T,G> e:myDict.entrySet()){
            copy.put(e.getKey(),e.getValue());
        }
        return new MyDict<T,G>(copy);
    }
}
