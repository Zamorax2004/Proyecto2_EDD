package proyecto2_edd.dataStructures.list;

import proyecto2_edd.dataStructures.IDataStructures.IList;

public class List implements IList {

    private NodeList head;
    private int size;

    public List() {
        this.head = null;
        this.size = 0;
    }

    public NodeList getHead() {
        return head;
    }

    public void setHead(NodeList head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public NodeList insertBegin(Object element) {
        NodeList node = new NodeList(element);
        if (isEmpty()) {
            setHead(node);
        } else {
            node.setNext(getHead());
            setHead(node);
        }
        size++;
        return node;
    }

    @Override
    public NodeList insertFinal(Object element) {
        NodeList node = new NodeList(element);
        if (isEmpty()) {
            setHead(node);
        } else {
            NodeList pointer = getHead();
            while (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            pointer.setNext(node);
        }
        size++;
        return node;
    }

    @Override
    public NodeList insertInIndex(Object element, int index) {
        NodeList node = new NodeList(element);
        if (isEmpty()) {
            setHead(node);
            size++;
        } else {
            if (index < 0) {
                System.out.println("Error, invalid index");
            } else if (index > size) {
                System.out.println("Error, index out of range");
            } else if (index == 0) {
                insertBegin(element);
            } else if (index == size) {
                insertFinal(element);
            } else {
                NodeList pointer = getHead();
                int aux = 0;
                while (pointer.getNext() != null && aux < index - 1) {
                    pointer = pointer.getNext();
                    aux++;
                }
                node.setNext(pointer.getNext());
                pointer.setNext(node);
                size++;
            }
        }
        return node;
    }

    @Override
    public NodeList deleteBegin() {
        if (isEmpty()) {
            System.out.println("Error, list in empty");
            return null;
        } else {
            NodeList pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            return pointer;
        }
    }

    @Override
    public NodeList deleteFinal() {
        if (isEmpty()) {
            System.out.println("Error, list is empty");
            return null;
        } else {
            NodeList pointer = getHead();
            while (pointer.getNext().getNext() != null) {
                pointer = pointer.getNext();
            }
            NodeList temp = pointer.getNext();
            pointer.setNext(null);
            size--;
            return temp;
        }
    }

    @Override
    public NodeList deleteInIndex(int index) {
        if (isEmpty()) {
            System.out.println("Error, list is empty");
        } else {
            if (index < 0) {
                System.out.println("Error, list is empty.");
            } else if (index > size) {
                System.out.println("Error, index out of range.");
            } else if (index == 0) {
                return deleteBegin();
            } else if (index == size - 1) {
                return deleteFinal();
            } else {
                NodeList pointer = getHead();
                int aux = 0;
                while (pointer.getNext() != null && aux < index - 1) {
                    pointer = pointer.getNext();
                    aux++;
                }
                NodeList pointerAux = pointer.getNext();
                pointer.setNext(pointerAux.getNext());
                pointerAux.setNext(null);
                size--;
                return pointerAux;
            }
        }
        return null;
    }

    public void print() {
        NodeList pointer = getHead();
        while (pointer != null) {
            System.out.print("["+pointer.getElement()+"]");
            pointer = pointer.getNext();
        }
    }

    @Override
    public void deleteAll() {
        if (isEmpty()) {
            System.out.println("Error, list is empty");
        } else {
            NodeList pointer = getHead();
            pointer.setElement(null);
            pointer.setNext(null);
            setHead(pointer);
            size = 0;
        }
    }
}