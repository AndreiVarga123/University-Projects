package src_code.Model.ProgramState.ADT;

import java.util.Map;
import java.util.Set;

public interface MyITable<T,G>{
    T add(G value);
    void update(T key, G value);
    G lookup(T key);
    boolean isDefined(T key);
    String toString();
    boolean isEmpty();
    void remove(T key);
    Set<Map.Entry<T,G>> getSet();
    void setContent(Map<T, G> newMap);
    Map<T, G> getContent();
}
