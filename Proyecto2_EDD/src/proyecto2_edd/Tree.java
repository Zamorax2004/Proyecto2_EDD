/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

import java.io.FileReader;
import org.json.JSONArray;
import org.json.JSONObject;
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
    public void loadFromJSON(String jsonFilePath) {
        try {
            // Cargar el archivo JSON y parsearlo
            JSONParser parser = new JSONParser(); // Crear un parser JSON
            Object obj = parser.parse(new FileReader(jsonFilePath));
            JSONObject jsonObject = (JSONObject) obj;

            // Iterar sobre los linajes en el archivo JSON
            for (Object key : jsonObject.keySet()) {
                String lineageName = (String) key; // Nombre de la casa
                JSONArray members = (JSONArray) jsonObject.get(lineageName);

                // Crear el árbol para este linaje
                for (Object memberObj : members) {
                    JSONObject memberData = (JSONObject) memberObj;
                    parseAndAddMember(memberData);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar el archivo JSON: " + e.getMessage());
        }
    }

    // Método para analizar y añadir un miembro al árbol
    private void parseAndAddMember(JSONObject memberData) {
        // Extraer los datos del miembro
        String name = memberData.keySet().iterator().next();
        JSONArray attributes = (JSONArray) memberData.get(name);

        String numeral = null, mote = null, title = null, father = null, mother = null, notes = null, fate = null;
        ListaEnlazada<String> children = new ListaEnlazada<>();

        for (Object attributeObj : attributes) {
            JSONObject attribute = (JSONObject) attributeObj;

            if (attribute.containsKey("Of his name")) numeral = (String) attribute.get("Of his name");
            if (attribute.containsKey("Known throughout as")) mote = (String) attribute.get("Known throughout as");
            if (attribute.containsKey("Held title")) title = (String) attribute.get("Held title");
            if (attribute.containsKey("Born to")) {
                if (father == null) father = (String) attribute.get("Born to");
                else mother = (String) attribute.get("Born to");
            }
            if (attribute.containsKey("Father to")) {
                JSONArray childrenArray = (JSONArray) attribute.get("Father to");
                for (Object child : childrenArray) {
                    children.add((String) child);
                }
            }
            if (attribute.containsKey("Notes")) notes = (String) attribute.get("Notes");
            if (attribute.containsKey("Fate")) fate = (String) attribute.get("Fate");
        }

        // Crear un nuevo objeto Person
        Person person = new Person(name, numeral, mote, title, father, mother, notes, fate);
        for (String child : children) person.addChild(child);

        // Crear un nodo TreeNode
        TreeNode newNode = new TreeNode(person);

        // Añadir a los índices
        if (name != null) nameIndex.put(name, newNode);
        if (mote != null) moteIndex.put(mote, newNode);

        // Establecer relaciones
        if (father != null && nameIndex.containsKey(father)) {
            TreeNode fatherNode = nameIndex.get(father);
            fatherNode.addChild(
                    newNode);
            newNode.setParent(fatherNode);
        } else if (root == null) {
            // Si no tiene padre y no hay raíz, establecer como raíz
            root = newNode;
        }
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
}
