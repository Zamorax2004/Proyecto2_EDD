package proyecto2_edd.dataStructures.tree;

import java.util.StringTokenizer;

public class Tree {
    private NodeTree root;

    public Tree() {
        this.root = null;
    }

    public boolean add(String path, String element) {
        if (root == null) {
            root = new NodeTree(element);
            System.out.println("Added root: " + element);
            return true;
        } else {
            NodeTree parent = searchNode(path);
            if (parent == null) {
                System.out.println("Parent not found for path: " + path);
                return false;
            } else {
                boolean added = addSon(parent, element);
                if (added) {
                    System.out.println("Added " + element + " to parent " + parent.getElement());
                }
                return added;
            }
        }
    }

    public NodeTree searchNode(String path) {
        if (path.equals("/")) {
            return root;
        }
        NodeTree currentNode = root;
        StringTokenizer tokenizer = new StringTokenizer(path, "/");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            currentNode = findChild(currentNode, token);
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    private NodeTree findChild(NodeTree node, String element) {
        NodeTree current = node.getSon();
        while (current != null) {
            if (element.equals(current.getElement())) {
                return current;
            }
            current = current.getBrother();
        }
        return null;
    }

    private boolean addSon(NodeTree parent, String element) {
        NodeTree newSon = new NodeTree(element);
        if (parent.getSon() == null) {
            parent.setSon(newSon);
        } else {
            NodeTree current = parent.getSon();
            while (current.getBrother() != null) {
                current = current.getBrother();
            }
            current.setBrother(newSon);
        }
        return true;
    }

    public void printTree() {
        print(root, " ");
    }

    private void print(NodeTree node, String indent) {
        if (node != null) {
            System.out.println(indent + node.getElement());
            print(node.getSon(), indent + " ");
            print(node.getBrother(), indent);
        }
    }
}