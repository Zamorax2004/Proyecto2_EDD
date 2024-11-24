/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

import java.io.FileReader;
import java.util.Enumeration;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;


/**
 *
 * @author yilup
 */
public class Tree {
    private TreeNode root; // Raíz del árbol genealógico
    private HashTable<String, TreeNode> nameIndex; // Índice para búsqueda rápida por nombre
    private HashTable<String, TreeNode> moteIndex; // Índice para búsqueda rápida por mote

    // Constructor
    public Tree() {
        this.root = null;
        this.nameIndex = new HashTable<>(100); // Tamaño inicial ajustable
        this.moteIndex = new HashTable<>(100); // Tamaño inicial ajustable
    }

    // Método para cargar un árbol desde un archivo JSON
    public void loadFromJSON() {
        try {
            String jsonFilePath = FileStorage.getInstance().getFilename();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(jsonFilePath));
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {
                String lineageName = (String) key;
                JSONArray members = (JSONArray) jsonObject.get(lineageName);
                for (Object memberObj : members) {
                    JSONObject memberData = (JSONObject) memberObj;
                    String name = (String) memberData.get("name");
                    String numeral = (String) memberData.get("numeral");
                    String mote = (String) memberData.get("mote");
                    String title = (String) memberData.get("title");
                    String father = (String) memberData.get("father");
                    String mother = (String) memberData.get("mother");
                    String notes = (String) memberData.get("notes");
                    String fate = (String) memberData.get("fate");
                    Person person = new Person(name, numeral, mote, title, father, mother, notes, fate);
                    TreeNode node = new TreeNode(person);
                    if (root == null) {
                        root = node;
                    } else {
                        TreeNode parentNode = nameIndex.get(father);
                        if (parentNode != null) {
                            parentNode.addChild(node);
                            node.setParent(parentNode);
                        }
                    }
                    nameIndex.put(name, node);
                    moteIndex.put(mote, node);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar el archivo JSON: " + e.getMessage());
        }
    }

    public ListaEnlazada<String> getAllNames() {
    ListaEnlazada<String> names = new ListaEnlazada<>();
    Enumeration<TreeNode> nodes = nameIndex.elements();
    while (nodes.hasMoreElements()) {
        TreeNode node = nodes.nextElement();
        names.add(node.getPerson().getName());
    }
    return names;
}

    // Método para buscar por nombre
    public TreeNode searchByName(String name) {
        return nameIndex.get(name);
    }

    // Método para buscar por mote
    public TreeNode searchByMote(String mote) {
        return moteIndex.get(mote);
    }

    // Mostrar antepasados
    public ListaEnlazada<TreeNode> getAncestors(TreeNode node) {
        ListaEnlazada<TreeNode> ancestors = new ListaEnlazada<>();
        TreeNode current = node.getParent();
        while (current != null) {
            ancestors.add(current);
            current = current.getParent();
        }
        return ancestors;
    }

    // Representación en String para depuración
    @Override
    public String toString() {
        if (root == null) return "Árbol vacío";
        return "Raíz del árbol: " + root.toString();
    }
    
    public void loadNamesFromJSON(String jsonFilePath) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(jsonFilePath));
            JSONObject jsonObject = (JSONObject) obj;

            for (Object key : jsonObject.keySet()) {
                String houseName = (String) key;
                JSONArray members = (JSONArray) jsonObject.get(houseName);

                for (Object memberObj : members) {
                    JSONObject memberData = (JSONObject) memberObj;
                    for (Object nameObj : memberData.keySet()) {
                        String name = (String) nameObj;
                        addName(name);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading JSON: " + e.getMessage());
        }
    }
    
    private void addName(String name) {
    TreeNode newNode = new TreeNode(new Person(name));
    if (root == null) {
        root = newNode;
    } else {
        ListaEnlazada<TreeNode> queue = new ListaEnlazada<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove(0);
            if (current.getChildren().isEmpty()) {
                current.addChild(newNode);
                newNode.setParent(current);
                break;
            } else {
                for (int i = 0; i < current.getChildren().size(); i++) {
                    queue.add(current.getChildren().get(i));
                }
            }
        }
    }
    nameIndex.put(name, newNode);
}
    
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
