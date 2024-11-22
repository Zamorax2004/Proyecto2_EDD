package proyecto2_edd.test;

import proyecto2_edd.dataStructures.hashMap.HashMap;
import proyecto2_edd.dataStructures.hashMap.HashMapNode;
import proyecto2_edd.dataStructures.tree.Tree;
import proyecto2_edd.dataStructures.array.NodeArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");

        System.out.println("key1: " + hashMap.get("key1"));
        System.out.println("key2: " + hashMap.get("key2"));
        System.out.println("key3: " + hashMap.get("key3"));

        hashMap.remove("key2");
        System.out.println("key2 after removal: " + hashMap.get("key2"));
    }

/*
/    public static void main(String[] args) {
/        String[] files = {"Proyecto2_EDD/Proyecto2_EDD/Baratheon.json", "Proyecto2_EDD/Proyecto2_EDD/Targaryen.json"};
/        for (String file : files) {
/            processFile(file);
/        }
/    }
*/

    static void processFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            HashMap<String, Object> hashMap = new HashMap<>();
            jsonObject.keys().forEachRemaining(key -> {
                Object value = jsonObject.get(key);
                hashMap.put(key, value);
            });
            printHashMap(hashMap);
            convertToTree(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printHashMap(HashMap<String, Object> hashMap) {
        for (int i = 0; i < hashMap.getTable().getArray().length; i++) {
            NodeArray nodeArray = (NodeArray) hashMap.getTable().getArray()[i];
            if (nodeArray != null) {
                HashMapNode<String, Object> node = (HashMapNode<String, Object>) nodeArray.getElement();
                while (node != null) {
                    System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue());
                    node = node.getNext();
                }
            }
        }
    }

    static void convertToTree(HashMap<String, Object> hashMap) {
        Tree tree = new Tree();
        for (int i = 0; i < hashMap.getTable().getArray().length; i++) {
            NodeArray nodeArray = (NodeArray) hashMap.getTable().getArray()[i];
            if (nodeArray != null) {
                HashMapNode<String, Object> node = (HashMapNode<String, Object>) nodeArray.getElement();
                while (node != null) {
                    tree.add("/", node.getKey());
                    addChildren(tree, "/" + node.getKey(), node.getValue());
                    node = node.getNext();
                }
            }
        }
        tree.printTree();
    }

    static void addChildren(Tree tree, String path, Object value) {
        if (value instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) value;
            jsonObject.keys().forEachRemaining(key -> {
                Object childValue = jsonObject.get(key);
                tree.add(path, key);
                addChildren(tree, path + "/" + key, childValue);
            });
        } else if (value instanceof org.json.JSONArray) {
            org.json.JSONArray jsonArray = (org.json.JSONArray) value;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object childValue = jsonArray.get(i);
                tree.add(path, "[" + i + "]");
                addChildren(tree, path + "/[" + i + "]", childValue);
            }
        } else {
            tree.add(path, value.toString());
        }
    }
}