package proyecto2_edd.dataStructures.tree;

public class NodeTree {
    private Object element;
    private NodeTree son;
    private NodeTree brother;

    public NodeTree(Object element) {
        this.element = element;
        this.son = null;
        this.brother = null;
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