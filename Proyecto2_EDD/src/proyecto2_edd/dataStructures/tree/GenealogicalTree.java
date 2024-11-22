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
        for (int i = 0; i < familyMap.getTable().getArray().length; i++) {
            NodeArray nodeArray = (NodeArray) familyMap.getTable().getArray()[i];
            if (nodeArray != null) {
                processNodeArray(nodeArray);
            }
        }
    }

    private void processNodeArray(NodeArray nodeArray) {
        HashMapNode<String, FamilyMember> node = (HashMapNode<String, FamilyMember>) nodeArray.getElement();
        while (node != null) {
            processFamilyMember(node.getValue());
            node = node.getNext();
        }
    }

    private void processFamilyMember(FamilyMember member) {
        if (member.getParent().equals("[Unknown]")) {
            tree.add("/", member.getName());
        } else {
            tree.add("/" + member.getParent(), member.getName());
        }
        addChildren(member);
    }

    private void addChildren(FamilyMember member) {
        for (int i = 0; i < member.getChildren().getSize(); i++) {
            String childName = (String) member.getChildren().get(i);
            tree.add("/" + member.getName(), childName);
        }
    }

    public void printTree() {
        tree.printTree();
    }
}