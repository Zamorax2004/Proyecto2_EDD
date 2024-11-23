package proyecto2_edd.dataStructures.array;

public class Array {

    private Integer head;
    private NodeArray[] array;
    private int size, maxSize;

    public Array(int maxSize) {
        this.maxSize = maxSize;
        this.head = null;
        this.size = 0;
        this.array = new NodeArray[maxSize];
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public NodeArray[] getArray() {
        return array;
    }

    public void setArray(NodeArray[] array) {
        this.array = array;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    private int transformIndex(int index) {
        if (index >= 0) {
            return index;
        } else {
            return maxSize + index;
        }
    }

    private void resize() {
        maxSize *= 2;
        NodeArray[] newArray = new NodeArray[maxSize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private int searchSpace() {
        for (int i = 0; i < getArray().length; i++) {
            if (getArray()[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public NodeArray insertBegin(Object element) {
        if (getSize() >= getMaxSize()) {
            resize();
        }
        NodeArray node = new NodeArray(element);
        int position = searchSpace();
        node.setNext(getHead());
        getArray()[position] = node;
        setHead(position);
        size++;
        return node;
    }

    public NodeArray insertFinal(Object element) {
        if (getSize() >= getMaxSize()) {
            resize();
        }
        NodeArray node = new NodeArray(element);
        if (isEmpty()) {
            return insertBegin(element);
        } else {
            int position = searchSpace();
            Integer pointer = getHead();
            while (getArray()[pointer].getNext() != null) {
                pointer = getArray()[pointer].getNext();
            }
            getArray()[pointer].setNext(position);
            getArray()[position] = node;
            size++;
        }
        return node;
    }

    public boolean isEmpty() {
        return getHead() == null;
    }
}