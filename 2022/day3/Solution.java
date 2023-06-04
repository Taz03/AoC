package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String... args) throws IOException {
        List<String> rucksacks = Files.readAllLines(Path.of("input.txt"));

        part1(rucksacks);
        part2(rucksacks);
    }

    private static void part1(List<String> rucksacks) {
        int sum = rucksacks.stream()
            .map(rucksack -> rucksack.split("(?<=\\G.{" + (rucksack.length() / 2) + "})", 2))
            .map(Solution::getCommonCharacter)
            .mapToInt(Solution::getValue)
            .sum();

        System.out.println(sum);
    }

    private static void part2(List<String> rucksacks) {
        List<String[]> groupedRucksacks = new ArrayList<>();
        for (int i = 0; i < rucksacks.size(); i += 3) {
            groupedRucksacks.add(new String[]{rucksacks.get(i), rucksacks.get(i + 1), rucksacks.get(i + 2)});
        }

        int sum = groupedRucksacks.stream()
            .map(Solution::getCommonCharacter)
            .mapToInt(Solution::getValue)
            .sum();

        System.out.println(sum);
    }

    private static Character getCommonCharacter(String[] array) {
        for (int i = 0; i < array[0].length(); i++) {
            char curr = array[0].charAt(i);

            boolean common = false;
            for (String elem : array) {
                common = elem.contains(Character.toString(curr));
                if (!common) break;
            }

            if (common) return curr;
        }

        return 0;
    }

    private static int getValue(char ch) {
        return Character.isUpperCase(ch) ? ch - 38 : ch - 96;
    }
}
