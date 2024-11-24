/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 *
 * @author yilup
 */
public class TreeNode {
    private Person person; // Datos de la persona representada en este nodo
    private TreeNode parent; // Nodo padre
    private ListaEnlazada<TreeNode> children; // Hijos del nodo

    // Constructor
    public TreeNode(Person person) {
        this.person = person;
        this.children = new ListaEnlazada<>();
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

    // Representación
    @Override
    public String toString() {
        return person.toString();
    }
}
