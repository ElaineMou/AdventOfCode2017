import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(new FileInputStream("input.txt"));
        String line = scanner.nextLine();
        String[] nums = line.split("\\s+");
        ArrayList<String> states = new ArrayList<>();

        int[] numbers = new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            numbers[i] = Integer.parseInt(nums[i]);
        }

        int count=0;
        do {
            states.add(numbersToHexString(numbers));
            count++;
            int maxIndex = indexOfMax(numbers);
            int addToAll = numbers[maxIndex]/numbers.length;
            int remainder = numbers[maxIndex]%numbers.length;
            numbers[maxIndex] = 0;
            for(int i=0;i<numbers.length;i++) {
                numbers[i] += addToAll;
            }
            int i=0;
            for(i=maxIndex+1;remainder > 0 && i<numbers.length;i++) {
                numbers[i]++;
                remainder--;
            }
            for(i=0;remainder > 0 && i< numbers.length;i++) {
                numbers[i]++;
                remainder--;
            }


        } while(!states.contains(numbersToHexString(numbers)));


        System.out.println(states.size() - states.indexOf(numbersToHexString(numbers)));
        System.out.println(count);
    }

    public static int indexOfMax(int[] array) {
        int max = array[0];
        int maxIndex = 0;
        for(int i=0;i<array.length;i++) {
            if (array[i] > max) {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static String numbersToHexString(int[] numbers) {
        StringBuffer stringBuffer = new StringBuffer();
        for(int num : numbers) {
            stringBuffer.append(Character.forDigit(num, 16));
        }

        System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }

}
