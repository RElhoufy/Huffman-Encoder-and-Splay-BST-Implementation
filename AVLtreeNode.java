public class AVLtreeNode extends Node {
    int height = 0;

    /*
    constructor
     */
    public AVLtreeNode(int value) {
        super(value);
    }

    public boolean hasLeft() {
        if(this.getLeft() != null) {
            return true;
        } else{
            return false;
        }
    }

    public boolean hasRight(){
        if(this.getRight() != null) {
            return true;
        } else{
            return false;
        }
    }
}
