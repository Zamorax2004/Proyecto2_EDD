package proyecto2_edd.dataStructures.hashMap;

import java.util.ArrayList;
import java.util.List;

public class FamilyMember {
    private String name;
    private String parent;
    private String alias;
    private List<String> children;
    private int hierarchy;

    public FamilyMember(String name) {
        this.name = name;
        this.parent = "[Unknown]";
        this.alias = "";
        this.children = new ArrayList<>();
        this.hierarchy = 0;
    }

    // Getters and setters for all fields, including hierarchy
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getChildren() {
        return children;
    }

    public void addChild(String child) {
        this.children.add(child);
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }
}