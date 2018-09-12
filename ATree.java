import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ATree {
    public static void main(String[] args) throws FileNotFoundException{
        ATree atree = new ATree();
        String source = args[0];
        atree.inputFile(source);
    }

    /*
    input text file line by line
    */
    private void inputFile(String source) throws FileNotFoundException {
        Scanner scanner;
        String inputMessage = null;
        try{
            scanner = new Scanner(new FileInputStream(source));
            AVLtree avLtree = new AVLtree();
            AVLtreeNode node = (AVLtreeNode) avLtree.getRoot();
            while(scanner.hasNextLine()){
                inputMessage = scanner.nextLine();
                char instruction = inputMessage.charAt(0);
                String nodeValue = inputMessage.substring(1);
                int value =Integer.parseInt(nodeValue);

                /*
                do the instruction depend on what instruction.
                 */
                if(instruction == 'a') {
                    node = avLtree.add(node,value);
                    avLtree.setRoot(node);
                }else if(instruction == 'r') {
                    node = avLtree.remove(node,value);
                    avLtree.setRoot(node);
                }else if(instruction == 'f'){
                    boolean foundNode = avLtree.find(value);
                    if(foundNode){
                        System.out.println("Found node value "+value);
                    }else {
                        System.out.println("Not Found the node");
                    }
                }
            }
            /*
            output the results
             */
            System.out.println();
            System.out.println("The total number of comparisons is "+avLtree.getComparisonTime());
            System.out.println("The total number of times a node’s parent changes is "+avLtree.getParentChangeTime());
            System.out.println("The total number of find operations is "+avLtree.getFindTime());
            System.out.println("The total number of add operations is "+avLtree.getAddTime());
            System.out.println("The total number of remove operations is "+avLtree.getRemoveTime());
        }catch (FileNotFoundException e){
            System.out.println("This file was not found");
            System.exit(0);
        }
    }
}
