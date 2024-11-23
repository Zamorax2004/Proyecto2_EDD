package proyecto2_edd;

public class NodeArray {
    private Object element;
    private Integer next;

    public NodeArray(Object element) {
        this.element = element;
        this.next = null;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }
}
