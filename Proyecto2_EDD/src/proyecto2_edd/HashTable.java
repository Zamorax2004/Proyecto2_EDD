/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 *
 * @author yilup
 */
public class HashTable<K,V> {
    private static final int DEFAULT_CAPACITY = 100;
    private ListaEnlazada<NodoHash<K, V>>[] buckets;
    private int size;

    public HashTable(int capacity) {
        this.buckets = new ListaEnlazada[capacity];
        this.size = 0;

        // Inicializar las listas enlazadas
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ListaEnlazada<>();
        }
    }

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    private int getBucketIndex(K key) {
        int hash = key.hashCode();
        return Math.abs(hash) % buckets.length;
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula.");
        int index = getBucketIndex(key);
        ListaEnlazada<NodoHash<K, V>> bucket = buckets[index];

        // Buscar si la clave ya existe
        ListaEnlazada<NodoHash<K, V>>.ListaIterator iterator = bucket.iterator();
        while (iterator.hasNext()) {
            NodoHash<K, V> nodo = iterator.next();
            if (nodo.getKey().equals(key)) {
                nodo.setValue(value);
                return;
            }
        }

        bucket.add(new NodoHash<>(key, value));
        size++;
    }

    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula.");
        int index = getBucketIndex(key);
        ListaEnlazada<NodoHash<K, V>> bucket = buckets[index];

        ListaEnlazada<NodoHash<K, V>>.ListaIterator iterator = bucket.iterator();
        while (iterator.hasNext()) {
            NodoHash<K, V> nodo = iterator.next();
            if (nodo.getKey().equals(key)) {
                return nodo.getValue();
            }
        }
        return null;
    }

    public void remove(K key) {
        if (key == null) throw new IllegalArgumentException("La clave no puede ser nula.");
        int index = getBucketIndex(key);
        ListaEnlazada<NodoHash<K, V>> bucket = buckets[index];

        ListaEnlazada<NodoHash<K, V>>.ListaIterator iterator = bucket.iterator();
        while (iterator.hasNext()) {
            NodoHash<K, V> nodo = iterator.next();
            if (nodo.getKey().equals(key)) {
                bucket.remove(nodo);
                size--;
                return;
            }
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public ListaEnlazada<V> values() {
        ListaEnlazada<V> values = new ListaEnlazada<>();
        for (ListaEnlazada<NodoHash<K, V>> bucket : buckets) {
            ListaEnlazada<NodoHash<K, V>>.ListaIterator iterator = bucket.iterator();
            while (iterator.hasNext()) {
                values.add(iterator.next().getValue());
            }
        }
        return values;
    }
}
