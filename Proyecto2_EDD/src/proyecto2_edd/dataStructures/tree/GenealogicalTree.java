package proyecto2_edd.dataStructures.tree;

import proyecto2_edd.Tree;
import proyecto2_edd.HashMap;
import proyecto2_edd.HashMapNode;
import proyecto2_edd.NodeArray;

import java.util.Comparator;

public class GenealogicalTree {

    private Tree tree;

    public GenealogicalTree() {
        this.tree = new Tree();
    }

    public void buildTree(HashMap<String, Person> familyMap) {
    List<Person> members = new ArrayList<>();
    for (int i = 0; i < familyMap.getTable().getArray().length; i++) {
        NodeArray nodeArray = (NodeArray) familyMap.getTable().getArray()[i];
        if (nodeArray != null) {
            HashMapNode<String, FamilyMember> node = (HashMapNode<String, FamilyMember>) nodeArray.getElement();
            while (node != null) {
                members.add(node.getValue());
                node = node.getNext();
            }
        }
    }

    // Add root nodes first
    for (FamilyMember member : members) {
        if (member.getParent().equals("[Unknown]") && member.getMother().equals("[Unknown]")) {
            try {
                tree.add("/", member.getName());
            } catch (TreeException e) {
                System.err.println("Error adding root member: " + member.getName() + " - " + e.getMessage());
            }
        }
    }

    // Add all other members
    for (FamilyMember member : members) {
        if (!member.getParent().equals("[Unknown]")) {
            try {
                tree.add("/" + member.getParent(), member.getName());
            } catch (TreeException e) {
                System.err.println("Error processing member: " + member.getName() + " - " + e.getMessage());
            }
        }
        if (!member.getMother().equals("[Unknown]")) {
            try {
                tree.add("/" + member.getMother(), member.getName());
            } catch (TreeException e) {
                System.err.println("Error processing member: " + member.getName() + " - " + e.getMessage());
            }
        }
    }
}

    public void printTree() {
        tree.printTree();
    }
}