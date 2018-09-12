/**
 * General tree for both huffman and AVL
 */
public class GeneralTree {
    Node root;

    public GeneralTree() {

    }

    public GeneralTree(Node node) {
        setRoot(node);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot( Node root) {
        this.root = root;
    }
}
