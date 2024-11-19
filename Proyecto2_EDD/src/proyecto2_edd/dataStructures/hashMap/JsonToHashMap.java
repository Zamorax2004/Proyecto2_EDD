package proyecto2_edd.dataStructures.hashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileReader;
import java.io.IOException;
import proyecto2_edd.dataStructures.array.NodeArray;
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

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class FamilyMember {
        private String name;
        private String parent;
        private String alias;
        private List children;

        public FamilyMember(String name, String parent, String alias) {
            this.name = name;
            this.children = new List();
        }

        public String getName() {
            return name;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public List getChildren() {
            return children;
        }

        public void addChild(String child) {
            children.insertFinal(child);
        }

        @Override
        public String toString() {
            return "FamiliMember{" +
                    "name=" + name + '\'' +
                    ", parent=" + parent + '\'' +
                    ", alias=" + alias + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
}
