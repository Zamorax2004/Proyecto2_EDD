package proyecto2_edd.dataStructures.tree;

import proyecto2_edd.dataStructures.hashMap.FamilyMember;
import proyecto2_edd.dataStructures.hashMap.HashMap;
import proyecto2_edd.dataStructures.hashMap.HashMapNode;
import proyecto2_edd.dataStructures.array.NodeArray;

public class GenealogicalTree {

    private Tree tree;

    public GenealogicalTree() {
        this.tree = new Tree();
    }

    public void buildTree(HashMap<String, FamilyMember> familyMap) {
        // First pass: Add all members with unknown parents (root nodes)
        for (int i = 0; i < familyMap.getTable().getArray().length; i++) {
            NodeArray nodeArray = (NodeArray) familyMap.getTable().getArray()[i];
            if (nodeArray != null) {
                HashMapNode<String, FamilyMember> node = (HashMapNode<String, FamilyMember>) nodeArray.getElement();
                while (node != null) {
                    FamilyMember member = node.getValue();
                    if (member.getParent().equals("[Unknown]")) {
                        try {
                            tree.add("/", member.getName());
                        } catch (TreeException e) {
                            System.err.println("Error adding root member: " + member.getName() + " - " + e.getMessage());
                        }
                    }
                    node = node.getNext();
                }
            }
        }

        // Second pass: Add all other members
        for (int i = 0; i < familyMap.getTable().getArray().length; i++) {
            NodeArray nodeArray = (NodeArray) familyMap.getTable().getArray()[i];
            if (nodeArray != null) {
                HashMapNode<String, FamilyMember> node = (HashMapNode<String, FamilyMember>) nodeArray.getElement();
                while (node != null) {
                    FamilyMember member = node.getValue();
                    if (!member.getParent().equals("[Unknown]")) {
                        try {
                            tree.add("/" + member.getParent(), member.getName());
                        } catch (TreeException e) {
                            System.err.println("Error processing member: " + member.getName() + " - " + e.getMessage());
                        }
                    }
                    node = node.getNext();
                }
            }
        }
    }

    public void printTree() {
        tree.printTree();
    }
}