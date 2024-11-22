package proyecto2_edd.dataStructures.tree;

import proyecto2_edd.dataStructures.hashMap.JsonToHashMap;
import proyecto2_edd.dataStructures.hashMap.FamilyMember;
import proyecto2_edd.dataStructures.hashMap.HashMap;
import proyecto2_edd.utils.FilePathUtil;

public class GenealogicalTreeTest {
    public static void main(String[] args) {
        // Load JSON files using the utility method to get absolute paths
        String[] files = {
                FilePathUtil.getJsonFilePath("Proyecto2_EDD/Proyecto2_EDD/src/proyecto2_edd/dataStructures/resources/Baratheon.json"),
                FilePathUtil.getJsonFilePath("Proyecto2_EDD/Proyecto2_EDD/src/proyecto2_edd/dataStructures/resources/Targaryen.json")
        };
                // Create a HashMap to store family members
        HashMap<String, FamilyMember> familyMap = new HashMap<>();

        // Process each JSON files and populate the HashMap
        for (String file : files) {
            JsonToHashMap.processFile(file, familyMap);
        }

        // Create a GenalogicalTree object
        GenealogicalTree genealogicalTree = new GenealogicalTree();

        // Build the genealogical tree from the HashMap
        genealogicalTree.buildTree(familyMap);

        // Print the genealogical tree
        genealogicalTree.printTree();
    }
}
