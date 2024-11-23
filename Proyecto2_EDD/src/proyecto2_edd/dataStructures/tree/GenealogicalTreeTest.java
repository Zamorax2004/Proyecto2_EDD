package proyecto2_edd.dataStructures.tree;

import proyecto2_edd.JsonToHashMap;
import proyecto2_edd.dataStructures.hashMap.FamilyMember;
import proyecto2_edd.HashMap;
import proyecto2_edd.utils.FilePathUtil;

public class GenealogicalTreeTest {
    public static void main(String[] args) {
        // Load a single JSON file using the utility method to get the absolute path
        String file = FilePathUtil.getJsonFilePath("Proyecto2_EDD/Proyecto2_EDD/src/proyecto2_edd/dataStructures/resources/Baratheon.json");

        // Create a HashMap to store family members
        HashMap<String, FamilyMember> familyMap = new HashMap<>();

        // Process the JSON file and populate the HashMap
        JsonToHashMap.processFile(file, familyMap);

        // Create a GenealogicalTree object
        GenealogicalTree genealogicalTree = new GenealogicalTree();

        // Build the genealogical tree from the HashMap
        genealogicalTree.buildTree(familyMap);

        // Print the genealogical tree
        genealogicalTree.printTree();
    }
}