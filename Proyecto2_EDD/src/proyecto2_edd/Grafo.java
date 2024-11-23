package proyecto2_edd;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
    private Graph graph;
    private Set<String> existingNames;

    public Grafo() {
        // Initialize the GraphStream graph
        System.setProperty("org.graphstream.ui", "swing");
        graph = new MultiGraph("Family Tree");
        existingNames = new HashSet<>();
    }

    // Method to add family members and relationships to the graph
    public void addFamilyMember(String name) {
        if (name != null && !existingNames.contains(name)) {
            if (graph.getNode(name) == null) {
                graph.addNode(name).setAttribute("ui.label", name);
            }
            existingNames.add(name);
        }
    }

    public void addRelationship(String parent, String child) {
        if (parent != null && child != null && existingNames.contains(parent) && existingNames.contains(child)) {
            if (graph.getEdge(parent + "-" + child) == null) {
                graph.addEdge(parent + "-" + child, parent, child, true);
            }
        }
    }

    // Method to display the graph
    public void displayGraph() {
        if (graph != null) {
            graph.display();
        }
    }

    // Method to parse JSON and build the graph
    public void parseJsonFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Invalid file name.");
            return;
        }
        try (FileReader reader = new FileReader(fileName)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            buildGraphFromJson(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildGraphFromJson(JSONObject jsonObject) {
        if (jsonObject != null) {
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
    }
}