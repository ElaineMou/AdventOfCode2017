import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int max = Integer.MIN_VALUE;
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        HashMap<String, Integer> registers = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("\\s+");

            registers.put(words[0], 0);
        }

        scanner = new Scanner(new FileInputStream("input.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split("\\s+");

            String reg = words[0];
            boolean increase = words[1].equals("inc");
            int delta = Integer.parseInt(words[2]) * (increase ? 1 : -1);
            boolean execute = test(words[4],words[5],Integer.parseInt(words[6]), registers);
            boolean execute2 = test2(words[4], words[5], Integer.parseInt(words[6]), registers);
            if (execute != execute2) {
                System.out.println(reg + " is now " + registers.get(reg) + " because " + registers.get(words[4]) + " " + words[5] + " " + words[6]);
            }
            if (execute) {
                registers.put(reg, registers.get(reg) + delta);
                int val = registers.get(reg);
                if (val > max) {
                    max = val;
                }
            }
        }
        for(int value : registers.values()) {
            if (value > max) {
                max = value;
            }
        }
        System.out.println(max);
    }

    public static boolean test(String register, String comparator, int value, HashMap<String,Integer> registers) {
        int regVal = registers.get(register);

        if (comparator.equals(">")) {
            return regVal > value;
        } else if (comparator.equals("<")) {
            return regVal < value;
        } else if (comparator.equals(">=")) {
            return regVal >= value;
        } else if (comparator.equals("<=")) {
            return regVal <= value;
        } else if (comparator.equals("==")) {
            return regVal == value;
        } else if (comparator.equals("!=")) {
            return regVal != value;
        }
        return false;
    }

    public static boolean test2(String register, String comparator, int value, HashMap<String,Integer> registers) {
        int regVal = registers.get(register);

        if (comparator.contains(">") && (regVal > value)) {
            return true;
        } else if (comparator.contains("<") && regVal < value) {
            return true;
        } else if (comparator.contains("!")) {
            return regVal != value;
        } else if (comparator.contains("=") && regVal == value) {
            return true;
        }

        return false;
    }
}
