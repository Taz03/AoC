import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

class Solution {
    public static void main(String... args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("input.txt"));

        String fileContent = "";
        while (input.hasNextLine()) {
            fileContent += input.nextLine() + "\n";
        }

        int[] sortedCalories = Arrays.stream(fileContent.split("\n\n"))
            .mapToInt(foods -> Arrays.stream(foods.split("\n"))
                    .mapToInt(Integer::parseInt)
                    .sum())
            .sorted()
            .toArray();

        System.out.println("part 1 " + sortedCalories[sortedCalories.length - 1]);
        System.out.println("part 2 " + (sortedCalories[sortedCalories.length - 1] + sortedCalories[sortedCalories.length - 2] + sortedCalories[sortedCalories.length - 3]));
    }
}
