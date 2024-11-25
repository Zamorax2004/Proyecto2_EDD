
package proyecto2_edd;

import java.io.FileReader;
import java.util.Enumeration;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;



public class Tree {
    private TreeNode root; 
    private HashTable<String, TreeNode> nameIndex; 
    private HashTable<String, TreeNode> moteIndex; 

    // Constructor
    public Tree() {
        this.root = null;
        this.nameIndex = new HashTable<>(100); 
        this.moteIndex = new HashTable<>(100); 
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
    
    public TreeNode getSubtree(String name) {
    TreeNode node = searchByName(name);

    if (node == null) {
        System.err.println("El miembro con el nombre '" + name + "' no fue encontrado.");
        return null;
    }

    return node; 
   }
    
   
    public ListaEnlazada<TreeNode> searchByTitle(String title) {
    ListaEnlazada<TreeNode> results = new ListaEnlazada<>();


    ListaEnlazada<NodoHash<String, TreeNode>> nodos = nameIndex.values();
    
   
    Nodo<NodoHash<String, TreeNode>> current = nodos.getHead(); 
    while (current != null) {
        NodoHash<String, TreeNode> nodoHash = current.getData();
        TreeNode node = nodoHash.getValue();
        if (node.getPerson().getTitle() != null && node.getPerson().getTitle().equals(title)) {
            results.add(node);
        }
        current = current.getNext(); 
    }

    return results;
    
   }
   
   public ListaEnlazada<TreeNode> getMembersByGeneration(int generation) {
    ListaEnlazada<TreeNode> miembros = new ListaEnlazada<>();
    if (root == null) {
        System.out.println("El árbol está vacío.");
        return miembros;
    }

    Cola<TreeNode> cola = new Cola<>();
    Cola<Integer> niveles = new Cola<>(); 

    cola.enqueue(root);
    niveles.enqueue(0); 

    while (!cola.isEmpty()) {
        TreeNode actual = cola.dequeue();
        int nivelActual = niveles.dequeue();

        if (nivelActual == generation) {
            miembros.add(actual);
        }

        ListaEnlazada<TreeNode> children = actual.getChildren();
        Nodo<TreeNode> childNode = children.getHead();
        while (childNode != null) {
            TreeNode hijo = childNode.getData();
            cola.enqueue(hijo);
            niveles.enqueue(nivelActual + 1);
            childNode = childNode.getNext();
        }
    }

    return miembros;
}
}
   
