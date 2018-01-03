import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day5 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int count = 0;
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        ArrayList<Integer> jumpList = new ArrayList<Integer>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Integer num = Integer.parseInt(line);
            jumpList.add(num);
        }

        int index = 0;
        int size = jumpList.size();

        while(index >= 0 && index < size) {
            int oldJump = jumpList.get(index);
            jumpList.set(index,  jumpList.get(index) + (jumpList.get(index) >= 3 ? -1: 1));
            index += oldJump;
            count++;
        }

        System.out.println(count);
    }
}
