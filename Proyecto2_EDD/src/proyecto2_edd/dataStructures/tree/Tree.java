package proyecto2_edd.dataStructures.tree;

import java.util.StringTokenizer;

public class Tree {
    private NodeTree root;

    public Tree() {
        this.root = null;
    }

    public boolean Add(String path, String element) {
        if (root == null) {
            root = new NodeTree(element);
            return true;
        } else {
            NodeTree tmp = searchNode(path);
            if (tmp == null) {
                return false;
            } else {
                return AddSon(tmp, element);
            }
        }
    }

    public NodeTree searchNode(String thePath) {
        NodeTree tmp1 = root;
        StringTokenizer path = new StringTokenizer(thePath, "/");
        String token;
        while (path.hasMoreTokens()) {
            token = path.nextToken();
            while (tmp1 != null) {
                if (token.equalsIgnoreCase((String) tmp1.getElement())) {
                    break;
                } else {
                    tmp1 = tmp1.getBrother();
                }
            }
            if (tmp1 == null) {
                return null;
            } else {
                tmp1 = tmp1.getSon();
            }
        }
        return tmp1;
    }

    private boolean AddSon(NodeTree father, String element) {
        NodeTree tmp = father.getSon();
        if (tmp == null) {
            father.setSon(new NodeTree(element, null, null));
        } else {
            NodeTree brotherTmp = tmp.getBrother();
            if (brotherTmp == null) {
                tmp.setBrother(new NodeTree(element, null, null));
            } else {
                while (brotherTmp.getBrother() != null) {
                    tmp = brotherTmp;
                    brotherTmp = brotherTmp.getBrother();
                }
                brotherTmp.setBrother(new NodeTree(element, null, null));
            }
        }
        return true;
    }

    public void printTree() {
        print(root, " ");
    }

    private void print(NodeTree node, String tab) {
        if (node != null) {
            System.out.println(tab + node.getElement());
            print(node.getSon(), tab + " ");
            print(node.getBrother(), tab);
        }
    }
}