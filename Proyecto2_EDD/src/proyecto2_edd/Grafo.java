package proyecto2_edd;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;


public class Grafo {
    private Graph graph;

    public Grafo() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new MultiGraph("Family Tree");
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

    // *********FALTA METODO PARA LEER EL JSON****************    
}