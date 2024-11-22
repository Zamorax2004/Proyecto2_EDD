package proyecto2_edd.dataStructures.tree;

import proyecto2_edd.dataStructures.hashMap.JsonToHashMap;
import proyecto2_edd.dataStructures.hashMap.FamilyMember;
import proyecto2_edd.dataStructures.hashMap.HashMap;

public class GenealogicalTreeTest {
    public static void main(String[] args) {
        // Load JSON
        String[] files = {"Proyecto2_EDD/Proyecto2_EDD/resources/Baratheon.json", "Proyecto2_EDD/Proyecto2_EDD/resources/Targaryen.json"};

        // Create HashMap
        HashMap<String, FamilyMember> familyMap = new HashMap<>();

        // Process JSON files
        for (String file : files) {
            JsonToHashMap.processFile(file, familyMap);
        }
    }
}
