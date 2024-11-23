package proyecto2_edd;


public class HashMap<K, V> {
    private Array table;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    public HashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Array(capacity);
        this.size = 0;
    }

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.table = new Array(capacity);
        this.size = 0;
    }

    public Array getTable() {
        return table;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private NodeArray wrapNode(HashMapNode<K, V> node) {
        return new NodeArray(node);
    }

    private HashMapNode<K, V> unwrapNode(NodeArray nodeArray) {
        return nodeArray != null ? (HashMapNode<K, V>) nodeArray.getElement() : null;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashMapNode<K, V> newNode = new HashMapNode<>(key, value);
        NodeArray newNodeArray = wrapNode(newNode);
        if (table.getArray()[index] == null) {
            table.getArray()[index] = newNodeArray;
        } else {
            NodeArray current = (NodeArray) table.getArray()[index];
            HashMapNode<K, V> currentNode = unwrapNode(current);
            while (currentNode.getNext() != null && !currentNode.getKey().equals(key)) {
                currentNode = currentNode.getNext();
            }
            if (currentNode.getKey().equals(key)) {
                currentNode.setValue(value);
            } else {
                currentNode.setNext(newNode);
            }
        }
        size++;
        if ((float) size / capacity >= LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = hash(key);
        NodeArray current = (NodeArray) table.getArray()[index];
        while (current != null) {
            HashMapNode<K, V> currentNode = unwrapNode(current);
            if (currentNode != null && currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            current = currentNode != null ? wrapNode(currentNode.getNext()) : null;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void resize() {
        capacity *= 2;
        Array newTable = new Array(capacity);
        for (int i = 0; i < table.getArray().length; i++) {
            NodeArray node = (NodeArray) table.getArray()[i];
            while (node != null) {
                HashMapNode<K, V> currentNode = unwrapNode(node);
                int index = Math.abs(currentNode.getKey().hashCode()) % capacity;
                NodeArray nextNode = node.getNext() != null ? (NodeArray) table.getArray()[node.getNext()] : null;
                currentNode.setNext(unwrapNode((NodeArray) newTable.getArray()[index]));
                newTable.getArray()[index] = wrapNode(currentNode);
                node = nextNode;
            }
        }
        table = newTable;
    }
}