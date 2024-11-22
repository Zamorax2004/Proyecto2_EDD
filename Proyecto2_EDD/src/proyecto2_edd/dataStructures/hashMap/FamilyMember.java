package proyecto2_edd.dataStructures.hashMap;

import proyecto2_edd.dataStructures.list.List;

public class FamilyMember {
    private String name;
    private String parent;
    private String alias;
    private List children;

    public FamilyMember(String name) {
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
        StringBuilder childrenNames = new StringBuilder();
        for (int i = 0; i < children.getSize(); i++) {
            childrenNames.append(children.get(i)).append(", ");
        }
        if (childrenNames.length() > 0) {
            childrenNames.setLength(childrenNames.length() - 2); // Remove last comma and space
        }
        return "FamilyMember{" +
                "name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                ", alias='" + alias + '\'' +
                ", children=[" + childrenNames.toString() + "]" +
                '}';
    }
}