package proyecto2_edd.dataStructures.hashMap;

public class JsonToHashMapTest {
    public static void main(String[] args) {
        String[] files = {"Proyecto2_EDD/Proyecto2_EDD/Baratheon.json", "Proyecto2_EDD/Proyecto2_EDD/Targaryen.json"};
        for (String file : files) {
            System.out.println("Processing file: " + file);
            JsonToHashMap.processFile(file);
        }
    }
}