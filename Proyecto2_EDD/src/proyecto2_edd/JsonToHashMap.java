package proyecto2_edd;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;

public class JsonToHashMap {
    
    public static void main(String[] args) {
        String file = FileStorage.getInstance().getFilename();
        HashMap<String, Person> familyMap = new HashMap<>();
        processFile(file, familyMap);
    }

    public static void processFile(String filePath, HashMap<String, Person> familyMap) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);
            jsonObject.keys().forEachRemaining(house -> processHouse(jsonObject.getJSONArray(house), familyMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processHouse(JSONArray members, HashMap<String, Person> familyMap) {
        for (int i = 0; i < members.length(); i++) {
            JSONObject member = members.getJSONObject(i);
            String memberName = member.keys().next();
            Person familyMember = new Person(memberName);
            processMemberAttributes(member.getJSONArray(memberName), familyMember);
            familyMap.put(memberName, familyMember);
        }
        assignHierarchy(familyMap);
    }

    private static void processMemberAttributes(JSONArray attributes, Person familyMember) {
        for (int j = 0; j < attributes.length(); j++) {
            JSONObject attribute = attributes.getJSONObject(j);
            if (attribute.has("Father to")) {
                processChildren(attribute.getJSONArray("Father to"), familyMember);
            }
            if (attribute.has("Born to") && !attribute.getString("Born to").equals("[Unknown]")) {
                if (familyMember.getParent().equals("[Unknown]")) {
                    familyMember.setParent(attribute.getString("Born to"));
                } else if (familyMember.getMother().equals("[Unknown]")) {
                    familyMember.setMother(attribute.getString("Born to"));
                }
            }
            if (attribute.has("Known throughout as")) {
                familyMember.setAlias(attribute.getString("Known throughout as"));
            }
        }
    }

    private static void processChildren(JSONArray children, Person familyMember) {
        for (int k = 0; k < children.length(); k++) {
            familyMember.addChild(children.getString(k));
        }
    }

    private static void assignHierarchy(HashMap<String, Person> familyMap) {
        for (int i = 0; i < familyMap.getTable().getArray().length; i++) {
            NodeArray nodeArray = (NodeArray) familyMap.getTable().getArray()[i];
            if (nodeArray != null) {
                HashMapNode<String, Person> node = (HashMapNode<String, Person>) nodeArray.getElement();
                while (node != null) {
                    Person member = node.getValue();
                    if (!member.getParent().equals("[Unknown]")) {
                        Person parent = familyMap.get(member.getParent());
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