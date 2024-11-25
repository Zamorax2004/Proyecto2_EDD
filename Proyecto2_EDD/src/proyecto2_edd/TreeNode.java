
package proyecto2_edd;


public class TreeNode {
    private Person person; // Datos de la persona representada en este nodo
    private TreeNode parent; // Nodo padre
    private ListaEnlazada<TreeNode> children; // Hijos del nodo
    private TreeNode next;

    // Constructor
    public TreeNode(Person person) {
        this.person = person;
        this.children = new ListaEnlazada<>();
        this.next = null;
    }

    // Métodos para gestionar relaciones
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void addChild(TreeNode child) {
        if (children == null) {
            children = new ListaEnlazada<>();
        }
        children.add(child);
    }

    // Getters
    public Person getPerson() {
        return person;
    }

    public TreeNode getParent() {
        return parent;
    }

    public ListaEnlazada<TreeNode> getChildren() {
        return children;
    }

    public TreeNode getNext() {
        return next;
    }

    public void setNext(TreeNode next) {
        this.next = next;
    }

    public String toString() {
        return person.toString();
    }
}
