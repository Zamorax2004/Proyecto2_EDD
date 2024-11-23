package proyecto2_edd;

public class List {

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

    public boolean isEmpty() {
        return head == null;
    }

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

    public NodeList insertInIndex(Object element, int index) {
        if (index < 0 || index > size) {
            System.out.println("Error, index out of range");
            return null;
        }
        if (index == 0) {
            return insertBegin(element);
        } else if (index == size) {
            return insertFinal(element);
        } else {
            NodeList node = new NodeList(element);
            NodeList pointer = getHead();
            for (int i = 0; i < index - 1; i++) {
                pointer = pointer.getNext();
            }
            node.setNext(pointer.getNext());
            pointer.setNext(node);
            size++;
            return node;
        }
    }

    public NodeList deleteBegin() {
        if (isEmpty()) {
            System.out.println("Error, list is empty");
            return null;
        } else {
            NodeList pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            size--;
            return pointer;
        }
    }

    public NodeList deleteFinal() {
        if (isEmpty()) {
            System.out.println("Error, list is empty");
            return null;
        } else {
            NodeList pointer = getHead();
            if (pointer.getNext() == null) {
                setHead(null);
                size--;
                return pointer;
            }
            while (pointer.getNext().getNext() != null) {
                pointer = pointer.getNext();
            }
            NodeList temp = pointer.getNext();
            pointer.setNext(null);
            size--;
            return temp;
        }
    }

    public NodeList deleteInIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Error, index out of range");
            return null;
        }
        if (index == 0) {
            return deleteBegin();
        } else if (index == size - 1) {
            return deleteFinal();
        } else {
            NodeList pointer = getHead();
            for (int i = 0; i < index - 1; i++) {
                pointer = pointer.getNext();
            }
            NodeList temp = pointer.getNext();
            pointer.setNext(temp.getNext());
            temp.setNext(null);
            size--;
            return temp;
        }
    }

    public void print() {
        NodeList pointer = getHead();
        while (pointer != null) {
            System.out.print("[" + pointer.getElement() + "]");
            pointer = pointer.getNext();
        }
        System.out.println();
    }

    public void deleteAll() {
        head = null;
        size = 0;
    }

    public Object get (int index) {
        if (index < 0 || index >= size) {
            System.out.println("Index out of range");
            return null;
        }
        NodeList pointer = getHead();
        for (int i = 0; i < index; i++) {
            pointer = pointer.getNext();
        }
        return pointer.getElement();
    }
}