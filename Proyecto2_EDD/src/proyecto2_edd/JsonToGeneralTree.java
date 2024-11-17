package proyecto2_edd;

import proyecto2_edd.dataStructures.tree.Tree;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;

public class JsonToGeneralTree {
    public static void main(String[] args) {
        String content = readFile("Proyecto2_EDD/Proyecto2_EDD/Baratheon.json");
        if (content != null) {
            Tree tree = new Tree();
            try {
                JSONObject jsonObject = new JSONObject(new JSONTokener(content));
                buildTree(jsonObject, tree, "House Baratheon");
                tree.printTree();
            } catch (Exception e) {
                System.out.println("Error parsing JSON: " + e.getMessage());
            }
        }
    }

    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filePath);
            int i;
            while ((i = fileReader.read()) != -1) {
                content.append((char) i);
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
        return content.toString();
    }

    private static void buildTree(JSONObject jsonObject, Tree tree, String rootName) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            String uniqueKey = getUniqueKey(tree, rootName, key);
            tree.Add(rootName, uniqueKey);
            if (value instanceof JSONArray) {
                buildTree((JSONArray) value, tree, rootName + "/" + uniqueKey);
            } else if (value instanceof JSONObject) {
                buildTree((JSONObject) value, tree, rootName + "/" + uniqueKey);
            }
        }
    }

    private static void buildTree(JSONArray jsonArray, Tree tree, String rootName) {
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            if (value instanceof JSONObject) {
                buildTree((JSONObject) value, tree, rootName);
            }
        }
    }

    private static String getUniqueKey(Tree tree, String rootName, String key) {
        String uniqueKey = key;
        int counter = 1;
        while (tree.searchNode(rootName + "/" + uniqueKey) != null) {
            uniqueKey = key + "_" + counter;
            counter++;
        }
        return uniqueKey;
    }
}