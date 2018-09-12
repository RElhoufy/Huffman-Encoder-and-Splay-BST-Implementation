import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Huffman {

    /*
    entry
     */
    public static void main(String[] args) throws FileNotFoundException{
        Huffman huffman = new Huffman();
        String source = args[0];//import read-in file to build encoding table
        String s = huffman.inputEncodingFile(source);// transfer it to plain String text
        HuffmanTree tree = huffman.buildHuffmanTree(s);// use this text to build the tree
        String [] table = huffman.encodingTable(tree);// use the tree to make the encoding table
        huffman.encoding(table);//allow user to type in any texts to generate their encoded messages
    }

    /*
    input text file for encoding
     */
    private String inputEncodingFile(String source) throws FileNotFoundException{
        Scanner scanner;
        String inputMessage = null;
        try{
            scanner = new Scanner(new FileInputStream(source));
            while(scanner.hasNextLine()){
                inputMessage = scanner.nextLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("This file was not found");
            System.exit(0);
        }
        return inputMessage;
    }

    /*
    this method based on input text to build a huffman tree and then turn it.
    it uses a self-defined arraylist with two different sorting method.
     */
    private HuffmanTree buildHuffmanTree(String s){
        ArrayList<Character> characterArrayList = new ArrayList<Character>();
        HuffmanTree huffmanTree = null;
        int [] frequency = new int[128];//fixed size for all possible characters in ASCII code
        for (int i = 0; i < s.length(); i++) {
            if(frequency[s.charAt(i)] == 0) {
                characterArrayList.add(s.charAt(i));
            }
            frequency[s.charAt(i)]++;// use characters' ASCII code number as its index
        }

        /*
        build all of huffman tree nodes by its key(char) and weight(appearance frequency)
         */
        ArrayList<Node> arrayList = new ArrayList<Node>();
        HuffmanTreeNode node;
        for (int i = 0; i < characterArrayList.size; i++) {
            node = new HuffmanTreeNode(characterArrayList.get(i),frequency[characterArrayList.get(i)]);
            arrayList.add(node);
        }

        /*
        define a char array to keep track with character coming order
         */
        char [] arrayTemp = new char[arrayList.size];
        for (int i = 0; i < arrayTemp.length; i++) {
            arrayTemp[i] = ((HuffmanTreeNode)arrayList.get(i)).getKeys();
        }

        HuffmanTree.sortArrayList(arrayList,arrayTemp);//sort nodes by its weight(frequency) and come-in order.(use bubble sort because only use it once)

        /*
        make a shallow copy of real array list as temporary list to help build the tree
        (easy to modify nodes coming in or coming out without affecting the real node in the array list)
         */
        ArrayList<Node> newNodeArrayList = new ArrayList<Node>();
        shallowCopy(arrayList,newNodeArrayList);

        int size = arrayList.size();//counter to track
        while(size > 1) {
            Node nodeTemp1 = newNodeArrayList.get(0);
            Node nodeTemp2 = newNodeArrayList.get(1);
            newNodeArrayList.remove(0);
            newNodeArrayList.remove(0);
            Node internalNode = new Node(nodeTemp1.getValue() + nodeTemp2.getValue());
            internalNode.setLeft(nodeTemp1);
            internalNode.setRight(nodeTemp2);
            HuffmanTree.insertSort(newNodeArrayList, internalNode);//use insert sort because it can do the insert and sort at same time
            size--;
        }

        /*
        make the whole tree
         */
        Node root = newNodeArrayList.pop();//last node in the arrayList is the root
        huffmanTree = new HuffmanTree(root);
        return huffmanTree;
    }

    private void shallowCopy(ArrayList<Node> nodeArrayList, ArrayList<Node> newNodeArrayList){
        for (int i = 0; i < nodeArrayList.size; i++) {
            newNodeArrayList.add(nodeArrayList.get(i));
        }
    }

    /*
    build the encoding table
     */
    private String [] encodingTable(HuffmanTree huffmanTree){
        String[] encodingTable = new String[128];//most likely to have 128 ASCII characters
        Node root = huffmanTree.root;
        HuffmanTree.traverse(root,encodingTable, "");//recursively get each character's paths with its corresponded code
        return encodingTable;
    }

    /*
    use this encoded table to encode texts
     */
    private void encoding(String[] strings){
        System.out.println("Please input texts you wanna encode");
        Scanner scanner = new Scanner(System.in);
        String inputMessage = scanner.nextLine();
        String encodedMessage = "";
        for (int i = 0; i < inputMessage.length(); i++) {
            char c = inputMessage.charAt(i);
            String encodedChar = strings[c];
            encodedMessage += encodedChar;
        }
        System.out.println("This is encoded message :");
        System.out.println(encodedMessage);
        System.out.println("It's length is "+encodedMessage.length());
    }
}
