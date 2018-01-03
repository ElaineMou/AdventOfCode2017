import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int totalScore = 0;
        int currentGroupScore = 0;
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));

        int garbageCount = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            boolean skipNext = false;
            boolean inGarbage = false;
            for (char c : line.toCharArray()) {
                if (skipNext) {
                    skipNext = false;
                    continue;
                }
                if (inGarbage) {
                    if (c == '>') {
                        inGarbage = false;
                    } else if  (c == '!') {
                        skipNext = true;
                    } else {
                        garbageCount++;
                    }
                    continue;
                }
                switch (c) {
                    case '{':
                        currentGroupScore++;
                        continue;
                    case '}':
                        totalScore += currentGroupScore;
                        currentGroupScore--;
                        continue;
                    case '<':
                        inGarbage = true;
                        continue;
                    case '!':
                        skipNext = true;
                        continue;
                }
            }
        }

        System.out.println(totalScore);
        System.out.println(garbageCount);
    }
}
