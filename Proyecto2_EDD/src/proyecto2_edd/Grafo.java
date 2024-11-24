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
        buildGraphRecursively(tree.getRoot());
    }

    private void buildGraphRecursively(TreeNode node) {
        if (node == null) return;

        addFamilyMember(node.getPerson().getName());
        ListaEnlazada<TreeNode> children = node.getChildren();
        ListaEnlazada<TreeNode>.ListaIterator iterator = children.iterator();
        while (iterator.hasNext()) {
            TreeNode child = iterator.next();
            addFamilyMember(child.getPerson().getName());
            addRelationship(node.getPerson().getName(), child.getPerson().getName());
            buildGraphRecursively(child);
        }
    }

    //funcion que obtenga arbol con nombres en los nodos y los transforme en un grafo    
}