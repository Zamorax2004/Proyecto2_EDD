package proyecto2_edd.dataStructures.hashMap;

import proyecto2_edd.dataStructures.array.NodeArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;

public class JsonToHashMap {

    public static void main(String[] args) {
        String file = "Proyecto2_EDD/Proyecto2_EDD/resources/Baratheon.json";
        HashMap<String, FamilyMember> familyMap = new HashMap<>();
        processFile(file, familyMap);
    }

    public static void processFile(String filePath, HashMap<String, FamilyMember> familyMap) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            jsonObject.keys().forEachRemaining(house -> processHouse(jsonObject.getJSONArray(house), familyMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processHouse(JSONArray members, HashMap<String, FamilyMember> familyMap) {
        for (int i = 0; i < members.length(); i++) {
            JSONObject member = members.getJSONObject(i);
            String memberName = member.keys().next();
            FamilyMember familyMember = new FamilyMember(memberName);
            processMemberAttributes(member.getJSONArray(memberName), familyMember);
            familyMap.put(memberName, familyMember);
        }
        assignHierarchy(familyMap);
    }

    private static void processMemberAttributes(JSONArray attributes, FamilyMember familyMember) {
        for (int j = 0; j < attributes.length(); j++) {
            JSONObject attribute = attributes.getJSONObject(j);
            if (attribute.has("Father to")) {
                processChildren(attribute.getJSONArray("Father to"), familyMember);
            }
            if (attribute.has("Born to") && !attribute.getString("Born to").equals("[Unknown]")) {
                if (familyMember.getParent().equals("[Unknown]")) {
                    familyMember.setParent(attribute.getString("Born to"));
                } else {
                    familyMember.setMother(attribute.getString("Born to"));
                }
            }
            if (attribute.has("Known throughout as")) {
                familyMember.setAlias(attribute.getString("Known throughout as"));
            }
        }
    }

    private static void processChildren(JSONArray children, FamilyMember familyMember) {
        for (int k = 0; k < children.length(); k++) {
            familyMember.addChild(children.getString(k));
        }
    }

    private static void assignHierarchy(HashMap<String, FamilyMember> familyMap) {
        for (int i = 0; i < familyMap.getTable().getArray().length; i++) {
            NodeArray nodeArray = (NodeArray) familyMap.getTable().getArray()[i];
            if (nodeArray != null) {
                HashMapNode<String, FamilyMember> node = (HashMapNode<String, FamilyMember>) nodeArray.getElement();
                while (node != null) {
                    FamilyMember member = node.getValue();
                    if (!member.getParent().equals("[Unknown]")) {
                        FamilyMember parent = familyMap.get(member.getParent());
                        if (parent != null) {
                            member.setHierarchy(parent.getHierarchy() + 1);
                        }
                    }
                    node = node.getNext();
                }
            }
        }
    }
}