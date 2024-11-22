package proyecto2_edd.dataStructures.hashMap;

import proyecto2_edd.dataStructures.array.Array;
import proyecto2_edd.dataStructures.array.NodeArray;

public class FamilyMember {
    private String name;
    private String parent;
    private String mother;
    private String alias;
    private int hierarchy;
    private Array children;

    public FamilyMember(String name) {
        this.name = name;
        this.parent = "[Unknown]";
        this.mother = "[Unknown]";
        this.alias = "";
        this.hierarchy = 0;
        this.children = new Array(10); // Inicializamos con un tamaño máximo de 10, puede ajustarse según sea necesario
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

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Array getChildren() {
        return children;
    }

    public void addChild(String child) {
        this.children.insertFinal(child);
    }
}