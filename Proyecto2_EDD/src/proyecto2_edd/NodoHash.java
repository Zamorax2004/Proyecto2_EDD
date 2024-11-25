
package proyecto2_edd;


public class NodoHash<K,V> {
    private K key;
    private V value;
    private NodoHash<K,V> next;

    public NodoHash(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    
     public NodoHash<K, V> getNext() {
        return next;
    }

    public void setNext(NodoHash<K, V> next) {
        this.next = next;
    }

    
    public String toString() {
        return "{" + key + ": " + value + "}";
    }
}
