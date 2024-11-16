package proyecto2_edd.dataStructures.list;

public class NodeList {
    private Object element;
    private NodeList next;

    public NodeList (Object element) {
        this.element = element;
        this.next = null;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public void setNext(NodeList next) {
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public NodeList getNext() {
        return next;
    }
}

