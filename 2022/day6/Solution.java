package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

class Solution {
    public static void main(String... args) throws IOException {
        String line = Files.readAllLines(Path.of("input.txt")).get(0);

        part1(line);
        part2(line);
    }

    private static void part1(String line) {
        System.out.println(uncommonSeries(line, 4));
    }

    private static void part2(String line) {
        System.out.println(uncommonSeries(line, 14));
    }

    private static int uncommonSeries(String line, int length) {
        for (int i = length; i < line.length(); i++) {
            long distinctCount = Arrays.stream(line.substring(i - length, i).split(""))
                .distinct()
                .count();

            if (distinctCount == length) return i;
        }

        return -1;
    }
}
