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

    private NodeArray[] cloneArray() {
        NodeArray[] newArray = new NodeArray[getArray().length + 1];
        System.arraycopy(getArray(), 0, newArray, 0, getArray().length);
        return newArray;
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
        NodeArray node = new NodeArray(element);
        if (getSize() >= getMaxSize()) {
            System.out.println("Error, max size reached.");
            return null;
        } else {
            int position = searchSpace();
            if (position == -1) {
                node.setNext(getHead());
                NodeArray[] newArray = cloneArray();
                newArray[newArray.length - 1] = node;
                setHead(newArray.length - 1);
                setArray(newArray);
            } else {
                node.setNext(getHead());
                getArray()[position] = node;
                setHead(position);
            }
            size++;
            return node;
        }
    }

    public NodeArray insertFinal(Object element) {
        NodeArray node = new NodeArray(element);
        if (getSize() >= getMaxSize()) {
            System.out.println("Error, max size reached.");
            return null;
        } else {
            if (isEmpty()) {
                return insertBegin(element);
            } else {
                int position = searchSpace();
                if (position == -1) {
                    NodeArray[] newArray = cloneArray();
                    Integer pointer = getHead();
                    while (newArray[pointer].getNext() != null) {
                        pointer = newArray[pointer].getNext();
                    }
                    newArray[pointer].setNext(newArray.length - 1);
                    newArray[newArray.length - 1] = node;
                    setArray(newArray);
                } else {
                    Integer pointer = getHead();
                    while (getArray()[pointer].getNext() != null) {
                        pointer = getArray()[pointer].getNext();
                    }
                    getArray()[pointer].setNext(position);
                    getArray()[position] = node;
                }
                size++;
            }
            return node;
        }
    }

    public NodeArray insertInIndex(Object element, int index) {
        if (isEmpty()) {
            return insertBegin(element);
        } else {
            index = transformIndex(index);
            if (index > size) {
                System.out.println("Error, index out of range");
                return null;
            } else if (index == size) {
                return insertFinal(element);
            } else if (index == 0) {
                return insertBegin(element);
            } else {
                NodeArray node = new NodeArray(element);
                int position = searchSpace();
                if (position == -1) {
                    System.out.println("Error, array is full");
                    return null;
                }
                NodeArray current = getArray()[getHead()];
                for (int i = 0; i < index - 1; i++) {
                    current = getArray()[current.getNext()];
                }
                node.setNext(current.getNext());
                current.setNext(position);
                getArray()[position] = node;
                size++;
                return node;
            }
        }
    }

    public NodeArray deleteBegin() {
        if (isEmpty()) {
            System.out.println("Error, list is empty.");
            return null;
        } else {
            NodeArray pointer = getArray()[getHead()];
            getArray()[getHead()] = null;
            setHead(pointer.getNext());
            pointer.setNext(null);
            size--;
            return pointer;
        }
    }

    public NodeArray deleteFinal() {
        if (isEmpty()) {
            System.out.println("Error, list is empty.");
            return null;
        } else {
            Integer pointer = getHead();
            while (getArray()[getArray()[pointer].getNext()].getNext() != null) {
                pointer = getArray()[pointer].getNext();
            }
            Integer pointer2 = getArray()[pointer].getNext();
            NodeArray nodeDeleted = getArray()[pointer2];
            getArray()[pointer2] = null;
            getArray()[pointer].setNext(null);
            size--;
            return nodeDeleted;
        }
    }

    public NodeArray deleteInIndex(int index) {
        if (isEmpty()) {
            System.out.println("Error, list is empty.");
            return null;
        } else {
            index = transformIndex(index);
            if (index < 0 || index >= size) {
                System.out.println("Error, index out of range.");
                return null;
            }
            if (index == 0) {
                return deleteBegin();
            } else if (index == size - 1) {
                return deleteFinal();
            } else {
                NodeArray current = getArray()[getHead()];
                for (int i = 0; i < index - 1; i++) {
                    current = getArray()[current.getNext()];
                }
                NodeArray nodeToDelete = getArray()[current.getNext()];
                current.setNext(nodeToDelete.getNext());
                getArray()[nodeToDelete.getNext()] = null;
                size--;
                return nodeToDelete;
            }
        }
    }

    public boolean isEmpty() {
        return getHead() == null;
    }

    public void print() {
        Integer pointer = head;
        while (pointer != null) {
            System.out.println("[" + array[pointer].getElement() + "]");
            pointer = array[pointer].getNext();
        }
    }

    public void printSecuencial() {
        for (NodeArray array1 : getArray()) {
            if (array1 != null) {
                System.out.println(array1.getElement().toString());
            }
        }
    }

    public void deleteAll() {
        head = null;
        size = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }
}