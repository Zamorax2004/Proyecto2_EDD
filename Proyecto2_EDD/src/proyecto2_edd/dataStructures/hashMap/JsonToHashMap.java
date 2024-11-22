package proyecto2_edd.dataStructures.hashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;
import proyecto2_edd.dataStructures.list.List;

public class JsonToHashMap {

    public static void main(String[] args) {
        String[] files = {"Proyecto2_EDD/Proyecto2_EDD/Baratheon.json", "Proyecto2_EDD/Proyecto2_EDD/Targaryen.json"};
        for (String file : files) {
            processFile(file);
        }
    }

    static void processFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            HashMap<String, FamilyMember> familyMap = new HashMap<>();
            jsonObject.keys().forEachRemaining(house -> {
                JSONArray members = jsonObject.getJSONArray(house);
                for (int i = 0; i < members.length(); i++) {
                    JSONObject member = members.getJSONObject(i);
                    String memberName = member.keys().next();
                    FamilyMember familyMember = new FamilyMember(memberName);
                    JSONArray attributes = member.getJSONArray(memberName);
                    for (int j = 0; j < attributes.length(); j++) {
                        JSONObject attribute = attributes.getJSONObject(j);
                        if (attribute.has("Father to")) {
                            JSONArray children = attribute.getJSONArray("Father to");
                            for (int k = 0; k < children.length(); k++) {
                                familyMember.addChild(children.getString(k));
                            }
                        }
                        if (attribute.has("Born to")) {
                            familyMember.setParent(attribute.getString("Born to"));
                        }
                        if (attribute.has("Known throughout as")) {
                            familyMember.setAlias(attribute.getString("Known throughout as"));
                        }
                    }
                    familyMap.put(memberName, familyMember);
                }
            });
            // Print the familyMap to verify the data
            System.out.println("Family Map for file: " + filePath);
            familyMap.getTable().print();
            // Print each family member
            familyMap.getTable().printSecuencial();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
