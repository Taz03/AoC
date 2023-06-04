package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Solution {
    public static void main(String... args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("input.txt"));

        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        long count = lines.stream()
            .map(line -> line.split(","))
            .map(Solution::toRange)
            .filter(ranges -> Solution.contains(ranges[0], ranges[1]))
            .count();

        System.out.println(count);
    }

    private static void part2(List<String> lines) {
        long count = lines.stream()
            .map(line -> line.split(","))
            .map(Solution::toRange)
            .filter(ranges -> Solution.ovrelaping(ranges[0], ranges[1]))
            .count();

        System.out.println(count);
    }

    private static Range[] toRange(String[] ranges) {
        String[] range1 = ranges[0].split("-");
        String[] range2 = ranges[1].split("-");

        return new Range[]{new Range(Integer.parseInt(range1[0]), Integer.parseInt(range1[1])), new Range(Integer.parseInt(range2[0]), Integer.parseInt(range2[1]))};
    }

    private static boolean contains(Range r1, Range r2) {
        return ((r1.start <= r2.start && r1.end >= r2.end) || (r2.start <= r1.start && r2.end >= r1.end));
    }

    private static boolean ovrelaping(Range r1, Range r2) {
        return ((r1.start <= r2.end && r1.end >= r2.start) || Solution.contains(r1, r2));

    }

    private static record Range(int start, int end) {}
}
