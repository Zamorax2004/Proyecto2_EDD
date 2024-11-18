package proyecto2_edd;

import proyecto2_edd.dataStructures.tree.Tree;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;

public class JsonToGeneralTree {

    public static void main(String[] args) {
        String[] files = {"Proyecto2_EDD/Proyecto2_EDD/Baratheon.json", "Proyecto2_EDD/Proyecto2_EDD/Targaryen.json"};
        for (String file : files) {
            processFile(file);
        }
    }

    static void processFile(String filePath) {
        String content = readFile(filePath);
        if (content != null) {
            Tree tree = new Tree();
            try {
                JSONObject jsonObject = new JSONObject(new JSONTokener(content));
                String rootName = jsonObject.keys().next();
                tree.add("", rootName);
                Object rootElement = jsonObject.get(rootName);
                buildTree(rootElement, tree, rootName);
                System.out.println("Tree for " + filePath + ":");
                tree.printTree();
            } catch (Exception e) {
                System.out.println("Error parsing JSON from " + filePath + ": " + e.getMessage());
            }
        }
    }

    static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            int i;
            while ((i = fileReader.read()) != -1) {
                content.append((char) i);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
        return content.toString();
    }

    static void buildTree(Object element, Tree tree, String rootName) {
        if (element instanceof JSONObject) {
            buildTreeFromJSONObject((JSONObject) element, tree, rootName);
        } else if (element instanceof JSONArray) {
            buildTreeFromJSONArray((JSONArray) element, tree, rootName);
        }
    }

    static void buildTreeFromJSONObject(JSONObject jsonObject, Tree tree, String rootName) {
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            String uniqueKey = getUniqueKey(tree, rootName, key);
            tree.add(rootName, uniqueKey);
            buildTree(value, tree, rootName + "/" + uniqueKey);
        }
    }

    static void buildTreeFromJSONArray(JSONArray jsonArray, Tree tree, String rootName) {
        for (int i = 0; i < jsonArray.length(); i++) {
            Object value = jsonArray.get(i);
            String uniqueKey = rootName + "/" + i;
            buildTree(value, tree, uniqueKey);
        }
    }

    static String getUniqueKey(Tree tree, String rootName, String key) {
        String uniqueKey = key;
        int counter = 1;
        while (tree.searchNode(rootName + "/" + uniqueKey) != null) {
            uniqueKey = key + "_" + counter;
            counter++;
        }
        return uniqueKey;
    }
}