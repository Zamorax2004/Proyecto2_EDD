package proyecto2_edd;

import proyecto2_edd.dataStructures.tree.Tree;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonToGeneralTreeTest {

    public static void main(String[] args) {
        testReadFile();
        testBuildTreeFromJSONObject();
        testBuildTreeFromJSONArray();
        testGetUniqueKey();
    }

    private static void testReadFile() {
        String content = JsonToGeneralTree.readFile("Proyecto2_EDD/Proyecto2_EDD/Baratheon.json");
        if (content != null && !content.isEmpty()) {
            System.out.println("testReadFile passed");
        } else {
            System.out.println("testReadFile failed");
        }
    }

    private static void testBuildTreeFromJSONObject() {
        String jsonString = "{\"House Baratheon\": {\"Orys Baratheon\": {\"Of his name\": \"First\"}}}";
        JSONObject jsonObject = new JSONObject(new JSONTokener(jsonString));
        Tree tree = new Tree();
        JsonToGeneralTree.buildTreeFromJSONObject(jsonObject, tree, "House Baratheon");
        if (tree.searchNode("House Baratheon/Orys Baratheon") != null) {
            System.out.println("testBuildTreeFromJSONObject passed");
        } else {
            System.out.println("testBuildTreeFromJSONObject failed");
            tree.printTree(); // Print the tree to see its structure
        }
    }

    private static void testBuildTreeFromJSONArray() {
        String jsonString = "[{\"Orys Baratheon\": {\"Of his name\": \"First\"}}]";
        JSONArray jsonArray = new JSONArray(new JSONTokener(jsonString));
        Tree tree = new Tree();
        JsonToGeneralTree.buildTreeFromJSONArray(jsonArray, tree, "House Baratheon");
        if (tree.searchNode("House Baratheon/0/Orys Baratheon") != null) {
            System.out.println("testBuildTreeFromJSONArray passed");
        } else {
            System.out.println("testBuildTreeFromJSONArray failed");
            tree.printTree(); // Print the tree to see its structure
        }
    }

    private static void testGetUniqueKey() {
        Tree tree = new Tree();
        tree.add("", "House Baratheon");
        String uniqueKey = JsonToGeneralTree.getUniqueKey(tree, "House Baratheon", "Orys Baratheon");
        if (uniqueKey.equals("Orys Baratheon")) {
            System.out.println("testGetUniqueKey passed");
        } else {
            System.out.println("testGetUniqueKey failed");
        }
    }
}