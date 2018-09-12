public class AVLtree extends GeneralTree {
    static int findTime = 0;
    static int addTime = 0;
    static int removeTime = 0;
    static int comparisonTime = 0;
    static int parentChangeTime = 0;
    /*
    getters for all output counters
     */
    public int getFindTime() {
        return findTime;
    }

    public int getAddTime() {
        return addTime;
    }

    public int getRemoveTime() {
        return removeTime;
    }

    public int getComparisonTime() {
        return comparisonTime;
    }

    public int getParentChangeTime() {
        return parentChangeTime;
    }

    /*
    addMethod
     */
    public AVLtreeNode add(AVLtreeNode node,int value) {
        if(node == null){//base case
            comparisonTime++;
            node = new AVLtreeNode(value);
            addTime++;
        }else {
            comparisonTime++;
            if(value <= node.getValue()){//if insert node is less or equal than passed node's value
                comparisonTime++;
                node.left = add((AVLtreeNode) node.left,value);//recursively call left child until nothing to call then add new node
                if(getBalancedFactor((AVLtreeNode)node.getRight(),(AVLtreeNode)node.getLeft()) == 2){//condition to rebalance
                    if(value <= node.getLeft().getValue()){
                        comparisonTime++;
                        node = leftLeftRotation(node);
                    }else {
                        comparisonTime++;
                        node = leftRightRotation(node);
                    }
                }
            }else if(value > node.getValue()){//if insert node is larger than passed node's value
                comparisonTime++;
                node.right = add((AVLtreeNode) node.right,value);//recursively call right child until nothing to call then add new node
                if(getBalancedFactor((AVLtreeNode)node.getRight(),(AVLtreeNode)node.getLeft())== 2){//condition to rebalance
                    if(value > node.right.getValue()){
                        comparisonTime++;
                        node = rightRightRotation(node);
                    }else {
                        comparisonTime++;
                        node = rightLeftRotation(node);
                    }
                }
            }
        }
        node.height = 1 + Math.max(height((AVLtreeNode)node.getLeft()),height((AVLtreeNode)node.getRight()));//choose max height from left and right subtree then their parent tree is max height + 1
        return node;
    }

    /*
    return balance factor from left subtree and right subtree.
     */
    private int getBalancedFactor(AVLtreeNode leftNode, AVLtreeNode rightNode){
        return Math.abs(height(leftNode) - height(rightNode));
    }

    /*
    height method take in a node and return its height
     */
    private int height(AVLtreeNode node) {
        if (node != null)
            return node.height;
        return 0;
    }

    /*
    4 cases of node rotations
     */
    private AVLtreeNode leftLeftRotation(AVLtreeNode node){
        AVLtreeNode leftNode;
        leftNode = (AVLtreeNode) node.left;
        node.left = leftNode.right;
        leftNode.right = node;

        /*
        recalculate heights
         */
        node.height = Math.max(height((AVLtreeNode) node.left),height((AVLtreeNode)node.right));
        leftNode.height = 1 + Math.max(height((AVLtreeNode)leftNode.left),height(node));
        parentChangeTime++;
        return leftNode;
    }

    private AVLtreeNode rightRightRotation(AVLtreeNode node){
        AVLtreeNode rightNode;

        rightNode = (AVLtreeNode) node.right;
        node.right = rightNode.left;
        rightNode.left = node;

        /*
        recalculate heights
         */
        node.height = Math.max(height((AVLtreeNode)node.left), height((AVLtreeNode)(node.right))) + 1;;
        rightNode.height = Math.max(height((AVLtreeNode)(rightNode.right)), height(node)) + 1;
        parentChangeTime++;
        return rightNode;
    }

    private AVLtreeNode leftRightRotation(AVLtreeNode node){
        node.left = rightRightRotation((AVLtreeNode) node.left);
        parentChangeTime++;
        return leftLeftRotation(node);
    }

    private AVLtreeNode rightLeftRotation(AVLtreeNode node){
        node.right = leftLeftRotation((AVLtreeNode) node.right);
        parentChangeTime++;
        return rightRightRotation(node);
    }

    /*
    find method
     */
    public boolean find(int value) {
        if (getRoot() == null) {
            comparisonTime++;// empty tree
            return false;
        }
        comparisonTime++;
        AVLtreeNode node = (AVLtreeNode) getRoot();
        while (true) {
            if (node.getValue() == value) {//found the value
                comparisonTime++;
                findTime++;
                return true;
            } else if (value < node.getValue()) {// value is less than current node's value
                comparisonTime++;
                if (!node.hasLeft()) {
                    comparisonTime++;
                    return false;
                } else {
                    comparisonTime++;
                    node = (AVLtreeNode) node.getLeft();
                }
            } else {
                comparisonTime++;
                if (!node.hasRight()) {
                    comparisonTime++;
                    return false;
                } else {
                    comparisonTime++;
                    node = (AVLtreeNode) node.getRight();
                }
            }
        }
    }

    /*
    remove method
     */
    public AVLtreeNode remove(AVLtreeNode node,int value) {
        if(node == null){
            comparisonTime++;
            System.out.println("Node value "+value+" is not existed, can't remove");
            return null;
        }
        if(value < node.getValue()){// this value is less than current node's value then go to left
            comparisonTime++;
            node.left = remove((AVLtreeNode) node.left,value);
            if (height((AVLtreeNode) node.right) - height((AVLtreeNode) node.left) == 2) {//condition to rebalance
                comparisonTime++;
                AVLtreeNode rightNode =  (AVLtreeNode) node.right;
                if (height((AVLtreeNode) rightNode.left) > height((AVLtreeNode) rightNode.right)) {
                    comparisonTime++;
                    node = rightLeftRotation(node);
                }
                else {
                    comparisonTime++;
                    node = rightRightRotation(node);
                }
            }
        }else if( value > node.getValue()){// this value is larger than current node's value then go to right
            comparisonTime++;
            node.right = remove((AVLtreeNode) node.right,value);
            if (height((AVLtreeNode) node.left) - height((AVLtreeNode) node.right) == 2) {
                comparisonTime++;
                AVLtreeNode leftNode = (AVLtreeNode) node.left;
                if (height((AVLtreeNode) leftNode.right) > height((AVLtreeNode) leftNode.left)) {
                    comparisonTime++;
                    node = leftRightRotation(node);
                }
                else {
                    comparisonTime++;
                    node = leftLeftRotation(node);
                }
            }
        }else {//found the value at current node
            comparisonTime++;
            if(node.hasLeft()&&node.hasRight()){//has both child
                if(height((AVLtreeNode) node.left) > height((AVLtreeNode) node.right)){//if left tree is deeper than right
                    comparisonTime++;
                    AVLtreeNode leftMaxValueNode = maxOrMin((AVLtreeNode) node.left);//found maximum node in left subtree
                    node.setValue(leftMaxValueNode.getValue());//use this node as new node to link left and right subtree
                    node.left = remove((AVLtreeNode) node.left,leftMaxValueNode.getValue());
                }else {//if left tree is not deeper than right
                    comparisonTime++;
                    AVLtreeNode rightMinNode = maxOrMin((AVLtreeNode) node.right);//found minimum node in right subtree
                    node.setValue(rightMinNode.getValue());//use this node as new node to link left and right subtree
                    node.right = remove((AVLtreeNode) node.right,rightMinNode.getValue());
                }
            }else {//only has one side child
                AVLtreeNode tempNode = node;
                if(node.hasLeft()){//move its right or left directly under its parent
                    comparisonTime++;
                    node = (AVLtreeNode) node.left;
                }else {
                    comparisonTime++;
                    node = (AVLtreeNode)node.right;
                }
                tempNode = null;
            }
            removeTime++;
        }
        return node;
    }

    /*
    to find out and return the largest node
     */
    private AVLtreeNode maxOrMin(AVLtreeNode node){
        if(node == null){
            return null;
        }
        while(node.right != null){
            comparisonTime++;
            node = (AVLtreeNode) node.right;
        }
        return node;
    }
}
