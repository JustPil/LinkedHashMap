package HMDL;

public interface HashMapInterface<K, V> {
    boolean put(K k, V v);
    V get(K k);
    boolean remove(K k);
    boolean contains(K k);
    boolean isEmpty();
    boolean isFull();
    int size();
}
