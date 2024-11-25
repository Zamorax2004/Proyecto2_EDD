package proyecto2_edd;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;


public class Grafo {
    private Graph graph;

    public Grafo() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new MultiGraph("Arbol");
    }

    public void addFamilyMember(String name) {
        if (graph.getNode(name) == null) {
            graph.addNode(name).setAttribute("ui.label", name);
        }
    }

    public void addRelationship(String parent, String child) {
        if (graph.getEdge(parent + "-" + child) == null) {
            graph.addEdge(parent + "-" + child, parent, child, true);
        }
    }

    public void displayGraph() {
        if (graph != null) {
            graph.display();
        }
    }
    
    public void buildFromTree(Tree tree) {
        buildGraph(tree.getRoot());
    }

    private void buildGraph(TreeNode node) {
        if (node == null) return;

        addFamilyMember(node.getPerson().getName());
        ListaEnlazada<TreeNode> children = node.getChildren();
        ListaEnlazada<TreeNode>.ListaIterator iterator = children.iterator();
        while (iterator.hasNext()) {
            TreeNode child = iterator.next();
            addFamilyMember(child.getPerson().getName());
            addRelationship(node.getPerson().getName(), child.getPerson().getName());
            buildGraph(child);
        }
    }
    
    public void displayDescendants(TreeNode node) {
    if (node == null) {
        System.out.println("El nodo es nulo.");
        return;
    }

    Cola<TreeNode> cola = new Cola<>(); // Usamos nuestra implementación personalizada de cola
    cola.enqueue(node);

    System.out.println("Descendientes de " + node.getPerson().getName() + ":");

    while (!cola.isEmpty()) {
        TreeNode actual = cola.dequeue();
        for (TreeNode hijo : actual.getChildren()) {
            System.out.println("- " + hijo.getPerson().getName());
            cola.enqueue(hijo); // Agregar los hijos a la cola
        }
    }
}





}