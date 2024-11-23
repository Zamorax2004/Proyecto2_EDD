package proyecto2_edd;

import java.util.StringTokenizer;

public class Tree {
    private NodeTree root;

    public Tree() {
        this.root = null;
    }

    public boolean add(String path, String element) throws TreeException {
        if (root == null) {
            if (path.equals("/")) {
                root = new NodeTree(element);
                return true;
            } else {
                throw new TreeException("Root node must be added first.");
            }
        } else {
            NodeTree parent = searchNode(path);
            if (parent == null) {
                throw new TreeException("Parent node not found for path: " + path);
            } else {
                if (findChild(parent, element) != null) {
                    throw new TreeException("Duplicate node: " + element);
                }
                return addSon(parent, element);
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