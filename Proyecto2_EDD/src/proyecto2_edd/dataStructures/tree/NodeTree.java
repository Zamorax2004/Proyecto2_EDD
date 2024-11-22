package proyecto2_edd.dataStructures.tree;

public class NodeTree {
    private Object element;
    private NodeTree son;
    private NodeTree brother;

    // Default constructor withouth sons or brothers
    public NodeTree(Object element) {
        this.element = element;
        this.son = null;
        this.brother = null;
    }

    // Constructor with 3 parameters
    public NodeTree(Object element, NodeTree son, NodeTree brother) {
        this.element = element;
        this.son = son;
        this.brother = brother;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public NodeTree getSon() {
        return son;
    }

    public void setSon(NodeTree son) {
        this.son = son;
    }

    public NodeTree getBrother() {
        return brother;
    }

    public void setBrother(NodeTree brother) {
        this.brother = brother;
    }
}
