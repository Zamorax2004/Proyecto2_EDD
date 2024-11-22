package proyecto2_edd.dataStructures.array;

import proyecto2_edd.dataStructures.IDataStructures.IList;

public class Array implements IList {

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

    public int transformIndex(int index) {
        if (index > 0) {
            return index;
        } else {
            return maxSize - 1 + index;
        }
    }

    public NodeArray[] cloneArray() {
        NodeArray[] newArray = new NodeArray[getArray().length + 1];
        System.arraycopy(getArray(), 0, newArray, 0, getArray().length);
        return newArray;
    }

    public int searchSpace() {
        for (int i = 0; i < getArray().length; i++) {
            if (getArray()[i] == null) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public NodeArray insertBegin(Object element) {
        NodeArray node = new NodeArray(element);
        if (getSize() >= getMaxSize()) {
            System.out.println("El maximo tamaño he sido alcanzado.");
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
            return node;
        }
    }

    @Override
    public NodeArray insertFinal(Object element) {
        NodeArray node = new NodeArray(element);
        if (getSize() >= getMaxSize()) {
            System.out.println("Error, max size reach.");
            return null;
        } else {
            if (isEmpty()) {
                insertBegin(element);
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

    @Override
    public NodeArray insertInIndex(Object element, int index) {
        if (isEmpty()) {
            insertBegin(element);
        } else {
            index = transformIndex(index);
            if (index > size) {
                System.out.println("Error, index out of range");
            } else if (index == size) {
                insertFinal(element);
            } else if (index == 0) {
                insertBegin(element);
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
        return null;
    }

    @Override
    public NodeArray deleteBegin() {
        if (isEmpty()) {
            System.out.println("Tha list is empty.");
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

    @Override
    public NodeArray deleteFinal() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return null;
        } else {
            Integer pointer = getHead();
            Integer nextNode = getArray()[pointer].getNext();
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

    @Override
    public boolean isEmpty() {
        return getHead() == null;
    }

    @Override
    public Object deleteInIndex(int index) {
        if (isEmpty()) {
            System.out.println("Error, list is empty");
            return null;
        } else {
            index = transformIndex(index);
            if (index < 0 || index > maxSize) {
                System.out.println("Error, index out of range");
                return null;
            }
            if (index == 0) {
                return deleteBegin();
            } else if (index == maxSize - 1) {
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

    public void print() {
        Integer pointer = getHead();
        while (pointer != null) {
            System.out.println("[" + getArray()[pointer].getElement() + "]");
            pointer = getArray()[pointer].getNext();
        }
    }

    public void printSecuencial() {
        for (NodeArray array1 : getArray()) {
            System.out.println("[" + array1.getElement() + "]");
        }
    }

    @Override
    public void deleteAll() {
        head = null;
        size = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }
}
