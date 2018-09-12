/*
Huffman tree inherits from General tree
 */
public class HuffmanTree extends GeneralTree{
    /*
    constructor
     */
    public HuffmanTree(Node node) {
        super(node);
    }

    /*
    bubble sort
     */
    public static void sortArrayList(ArrayList<Node>arrayList,char[] chars){
        for (int i = 0; i < arrayList.size; i++) {
            for (int j = i + 1; j < arrayList.size; j++) {
                Node tempI;
                Node tempJ;
                if(arrayList.get(i).getValue() > arrayList.get(j).getValue()){//general case
                    tempI = arrayList.get(i);
                    tempJ= arrayList.get(j);
                    arrayList.insert(i,tempJ);
                    arrayList.remove(i + 1);
                    arrayList.insert(j,tempI);
                    arrayList.remove(j + 1);
                }else if(arrayList.get(i).getValue() == arrayList.get(j).getValue()){//case for two characters have same weight
                    int temp1 = 0;
                    int temp2 = 0;
                    for (int k = 0; k < chars.length; k++) {
                        if(((HuffmanTreeNode)arrayList.get(i)).getKeys() == chars[k]){
                            temp1 = k;
                            continue;
                        }else if(((HuffmanTreeNode)arrayList.get(j)).getKeys() == chars[k]){
                            temp2 = k;
                            continue;
                        }else if(temp1 != 0 &&temp2 != 0){
                            break;
                        }
                    }
                    if(temp1 > temp2){
                        tempI = arrayList.get(i);
                        tempJ= arrayList.get(j);
                        arrayList.insert(i,tempJ);
                        arrayList.remove(i + 1);
                        arrayList.insert(j,tempI);
                        arrayList.remove(j + 1);
                    }
                }
            }
        }
    }

    /*
    insertSort
     */
    public static void insertSort(ArrayList<Node>arrayList,Node node){
        for (int i = 0; i < arrayList.size; i++) {
            if(arrayList.get(i).getValue() > node.getValue()){
                arrayList.insert(i,node);
                return;//find the right place to insert then stop
            }
        }
        arrayList.add(node);
    }

    /*
    traverse each sub node
     */
    public static void traverse(Node node, String [] encodingTable, String code) {
        if (node == null){//base case
            return;
        }
        if (node.isLeaf()){//final result
            HuffmanTreeNode hNode = (HuffmanTreeNode) node;
            encodingTable[hNode.getKeys()] = code;
        } else {
            traverse(node.left, encodingTable, code + "0");//go left tree and its edge code update once
            traverse(node.right, encodingTable, code + "1");//go right tree and its edge code update once
        }
    }
}
