/*
general node class for both huffman and AVL
 */
public class Node {
    int value;
    Node left;
    Node right;
    Node parent;

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isLeaf(){
        if(this.left == null && this.right == null) {
            return true;
        }else {
            return false;
        }
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left){
        removeLeft();
        this.left = left;
        this.parent = this;
    }

    public void setRight(Node right){
        removeRight();
        this.right = right;
        this.parent = this;
    }

    public void removeLeft() {//remove its relation with left child vice versa
        if (left != null) {
            left.parent = null;
            left = null;
        }
    }

    public void removeRight() {//remove its relation with right child vice versa
        if (right != null) {
            right.parent = null;
            right = null;
        }
    }
}
