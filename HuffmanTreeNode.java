/**
 * huffman tree node inherit from Node class with its own property keys,which is the character value in the huffman tree node.
 */
public class HuffmanTreeNode extends Node {
    char keys;

    public HuffmanTreeNode(char keys, int frequency) {
        super(frequency);
        this.keys = keys;
    }

    public char getKeys() {
        return keys;
    }

    public void setKeys(char keys) {
        this.keys = keys;
    }
}
