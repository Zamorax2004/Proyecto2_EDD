package proyecto2_edd.dataStructures.tree;

import java.util.StringTokenizer;

public class Tree {
    private NodeTree root;

    public Tree() {
        this.root = null;
    }

    public boolean Add(String path, String element) {
        // If root is null, so is empty
        if (root == null) {
            // Constructor use one parameter. Son and brother are null
            root = new NodeTree(element);
            return true;
        } else { // Find node according to given path
            NodeTree tmp = searchNode(path);
            if (tmp == null) {
                return false;
            } else {
                return AddSon(tmp, element);
            }
        }
    }

    private NodeTree searchNode(String thePath) {
        NodeTree tmp1 = root;
        NodeTree tmp2 = tmp1;
        // Split path by into tokens
        StringTokenizer path = new StringTokenizer(thePath, "/");
        String token;
        // Search node by existing token
        while (path.hasMoreTokens()) {
            token = path.nextToken();
            // Search for matching brothers
            while (tmp1 != null) {
                if (token.equalsIgnoreCase((String) tmp1.getElement())) {
                    // If token matches the node, it has been found
                } else {
                    tmp1 = tmp1.getBrother();
                    tmp2 = tmp1;
                }
            }
            if (tmp1 == null) {
                // No matches, return null
                return tmp1;
            } else {
                // Keep looking for sons
                tmp2 = tmp1;
                tmp1 = tmp1.getSon();
            }
        }
        return tmp2;
    }

    private boolean AddSon(NodeTree father, String element) {
        // The son of the received node is obtained
        NodeTree tmp = father.getSon();

        // If tmp (son) == null, then there is no son and it is added directly
        if (tmp == null) {
            father.setSon(new NodeTree(element, null, null));
        } else { // Already has brother
            // ask about the brother of tmp, if it is null,
            // then has no brother, so it is added directly
            NodeTree brotherTmp = tmp.getBrother();
            while (brotherTmp.getBrother() != null) {
                tmp = brotherTmp;
                brotherTmp = brotherTmp.getBrother();
            }
            brotherTmp.setBrother(new NodeTree(element, null, null));
        }
        return true;
    }

    public void printTree() {
        print(root, " ");
    }

    private void print(NodeTree node, String tab) {
        if (node != null) {
            if (node != null) {
                System.out.println(tab + node.getElement());
                print(node.getSon(), tab + " ");
                print(node.getBrother(), tab);
            }
        }
    }

    public int totalNodes() {
        // Use the nodeCounter method and sends it the root
        // This counts all the nodes in the tree
        return nodeCounter(root);
    }

    // Count the nodes in subtrees
    private int nodeCounter(NodeTree node) {
        if (node != null) {
            return 1 + nodeCounter(node.getSon()) + nodeCounter(node.getBrother());
        } else {
            return 0;
        }
    }

    public int totalLeafs() {
        // Use the leafCounter method and sends it the root
        // This counts all the leaf nodes in the tree
        return leafCounter(root);
    }

    private int leafCounter(NodeTree node) {
        if (node == null) {
            return 0;
        } else if (node.getSon() == null) {
            return 1 + leafCounter(node.getBrother());
        } else {
            return leafCounter(node.getSon()) + leafCounter(node.getBrother());
        }
    }

    public void showPreorder() {
        preorderCounter(root);
    }

    public void showInorder() {
        inorder(root);
    }

    public void showPostorder() {
        postorder(root);
    }

    private void preorderCounter(NodeTree node) {
        if (node != null) {
            System.out.println(" - " + node.getElement());
            preorderCounter(node.getSon());
            preorderCounter(node.getBrother());
        }
    }

    private void inorder(NodeTree node) {
        if (node != null) {
            for (NodeTree t = node.getSon(); t != null; t.getBrother()) {
                postorder(t);
            }
            System.out.println("-" + node.getElement());
        }
    }

    private void postorder(NodeTree node) {
        if (node != null) {
            inorder(node.getSon());
            System.out.println("-" + node.getElement());
            NodeTree t = node.getSon();
            while (t != null) {
                t = t.getBrother();
                inorder(t);
            }
        }
    }

    int numberAppearances(String s) {
        return countAppearances(root, s);
    }

    private int countAppearances(NodeTree node, String s) {
        if (node != null) {
            int count = 0;
            if (s.equals(node.getElement())) {
                count++;
            }
            return count + countAppearances(node.getSon(), s) + countAppearances(node.getBrother(), s);
        } else {
            return 0;
        }
    }

    // A ternary tree is when each node has at most 3 sons
    public boolean isTernary() {
        return ternary(root);
    }

    private boolean ternary(NodeTree node) {
        if (node == null) {
            return true;
        } else {
            NodeTree t = node.getSon();
            int h = 0;
            while (t != null) {
                t = t.getBrother();
                if (++h > 3) {
                    return false;
                }
            }
            return ternary(node.getBrother()) && ternary(node.getSon());
        }
    }
}
