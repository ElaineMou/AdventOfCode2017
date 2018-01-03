import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int count = 0;
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        HashMap<String,Node> nodes = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("[^a-zA-Z\\d]+");

            nodes.put(words[0], new Node(words[0], Integer.parseInt(words[1])));
        }

        scanner = new Scanner(new FileInputStream("input.txt"));
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("[^a-zA-Z\\d]+");

            Node root = nodes.get(words[0]);
            for(int i=2;i<words.length;i++) {
                Node child = nodes.get(words[i]);
                child.parent = root;
                root.children.add(child);
            }
        }

        Node root= null;
        for(Node node : nodes.values()) {
            if (node.parent == null) {
                root = node;
                System.out.println(node.name);
            }
        }
        int rootWeight = root.sumWeight();
        int fixWeight = fixingWeight(root);



        System.out.println(fixWeight);
    }

    private static class Node{
        String name;
        int weight;
        int totalWeight = 0;
        ArrayList<Node> children;
        Node parent;

        Node(String name, int weight) {
            this.name = name;
            this.weight = weight;
            children = new ArrayList<>();
        }

        int sumWeight() {
            if (totalWeight ==0) {
                for (Node child : children) {
                    totalWeight += child.sumWeight();
                }
                totalWeight += weight;
            }
            return totalWeight;
        }
    }

    public static int fixingWeight(Node root) {
        for(int i=0;i<root.children.size();i++) {
            int fixWeight = fixingWeight(root.children.get(i));
            if (fixWeight<0) {
                return fixWeight;
            }
        }

        for(int i=0;i<root.children.size()-2;i++) {
            int weight1 = root.children.get(i).sumWeight();
            int weight2 = root.children.get(i+1).sumWeight();
            int weight3 = root.children.get(i+2).sumWeight();

            if (weight1 != weight2 && weight1 != weight3) {
                return -(weight2 - weight1 + root.children.get(i).weight);
            }
            if (weight2 != weight3 && weight2 != weight1) {
                return -(weight3 - weight2 + root.children.get(i+1).weight);
            }
            if (weight3 != weight1 && weight3 != weight2) {
                return -(weight2 - weight3 + root.children.get(i+2).weight);
            }
        }
        return root.sumWeight();
    }
}
