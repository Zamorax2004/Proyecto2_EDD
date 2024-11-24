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
    }

    private static void processMemberAttributes(JSONArray attributes, Person familyMember) {
        for (int j = 0; j < attributes.length(); j++) {
            JSONObject attribute = attributes.getJSONObject(j);
            if (attribute.has("Father to")) {
                processChildren(attribute.getJSONArray("Father to"), familyMember);
            } else {
                familyMember.setParent("[Unknown]");
            }
            if (attribute.has("Born to") && !attribute.getString("Born to").equals("[Unknown]")) {
                if (familyMember.getParent().equals("[Unknown]")) {
                    familyMember.setParent(attribute.getString("Born to"));
                } else if (familyMember.getMother().equals("[Unknown]")) {
                    familyMember.setMother(attribute.getString("Born to"));
                }
            } else {
                if (familyMember.getParent().equals("[Unknown]")) {
                    familyMember.setParent("[Unknown]");
                }
                if (familyMember.getMother().equals("[Unknown]")) {
                    familyMember.setMother("[Unknown]");
                }
            }
            if (attribute.has("Known throughout as")) {
                familyMember.setAlias(attribute.getString("Known throughout as"));
            } else {
                familyMember.setAlias("[Unknown]");
            }
            if (attribute.has("numeral")) {
                familyMember.setNumeral(attribute.getString("numeral"));
            } else {
                familyMember.setNumeral("[Unknown]");
            }
            if (attribute.has("title")) {
                familyMember.setTitle(attribute.getString("title"));
            } else {
                familyMember.setTitle("[Unknown]");
            }
            if (attribute.has("notes")) {
                familyMember.setNotes(attribute.getString("notes"));
            } else {
                familyMember.setNotes("[Unknown]");
            }
            if (attribute.has("fate")) {
                familyMember.setFate(attribute.getString("fate"));
            } else {
                familyMember.setFate("[Unknown]");
            }
        }
    }

    private static void processChildren(JSONArray children, Person familyMember) {
        for (int k = 0; k < children.length(); k++) {
            familyMember.addChild(children.getString(k));
        }
    }
}