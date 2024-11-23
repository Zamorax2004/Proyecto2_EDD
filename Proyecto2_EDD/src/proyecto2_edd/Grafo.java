package proyecto2_edd;

import org.graphstream.graph.Graph;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.graphstream.graph.implementations.MultiGraph;

public class Grafo {
    private Graph graph;
    private Set<String> existingNames;

    public Grafo() {
        // Initialize the GraphStream graph
        graph = new MultiGraph("Family Tree");
        existingNames = new HashSet<>();
    }

    // Method to add family members and relationships to the graph
    public void addFamilyMember(String name) {
        if (!existingNames.contains(name)) {
            graph.addNode(name).setAttribute("ui.label", name);
            existingNames.add(name);
        }
    }

    public void addRelationship(String parent, String child) {
        if (existingNames.contains(parent) && existingNames.contains(child)) {
            graph.addEdge(parent + "-" + child, parent, child, true);
        }
    }

    // Method to display the graph
    public void displayGraph() {
        graph.display();
    }

    // Method to parse JSON and build the graph
    public void parseJsonFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            buildGraphFromJson(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildGraphFromJson(JSONObject jsonObject) {
        jsonObject.keys().forEachRemaining(family -> {
            JSONArray members = jsonObject.getJSONArray(family);
            for (int i = 0; i < members.length(); i++) {
                JSONObject member = members.getJSONObject(i);
                String memberName = member.keys().next();
                addFamilyMember(memberName);
                JSONArray relations = member.getJSONArray(memberName);
                for (int j = 0; j < relations.length(); j++) {
                    JSONObject relation = relations.getJSONObject(j);
                    if (relation.has("Father to")) {
                        JSONArray children = relation.getJSONArray("Father to");
                        for (int k = 0; k < children.length(); k++) {
                            String childName = children.getString(k);
                            addFamilyMember(childName);
                            addRelationship(memberName, childName);
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        String filename = FileStorage.getInstance().getFilename(); // Adjust this based on your file retrieval logic
        grafo.parseJsonFile(filename);
        grafo.displayGraph();
    }
}