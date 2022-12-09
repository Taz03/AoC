package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public static void main(String... args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("input.txt"));

        part1(lines);
        part2(lines);
    }

    private static void part1(List<String> lines) {
        int[] h = {0, 0};
        int[] t = {0, 0};
        List<int[]> tPositions = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" ");

            for (int i = 0; i < Integer.parseInt(parts[1]); i++) {
                switch (parts[0].charAt(0)) {
                    case 'R' -> h[0]++;
                    case 'L' -> h[0]--;
                    case 'U' -> h[1]++;
                    case 'D' -> h[1]--;
                }

                if (!isTouching(h, t)) {
                    moveTail(h, t);
                    tPositions.add(Arrays.copyOf(t, 2));
                }
            }
        }

        System.out.println(distinctCount(tPositions) + 1);
    }

    private static void part2(List<String> lines) {
        List<int[]> nodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) nodes.add(new int[]{0, 0});

        List<int[]> tPositions = new ArrayList<>();
        int[] h = nodes.get(0);
        int[] t = nodes.get(nodes.size() - 1);

        for (String line : lines) {
            String[] parts = line.split(" ");

            for (int i = 0; i < Integer.parseInt(parts[1]); i++) {
                switch (parts[0].charAt(0)) {
                    case 'R' -> h[0]++;
                    case 'L' -> h[0]--;
                    case 'U' -> h[1]++;
                    case 'D' -> h[1]--;
                }

                for (int j = 0; j < nodes.size() - 1; j++) {
                    if (!isTouching(nodes.get(j), nodes.get(j + 1))) {
                        moveTail(nodes.get(j), nodes.get(j + 1));
                        tPositions.add(Arrays.copyOf(t, 2));
                    }
                }
            }
        }

        System.out.println(distinctCount(tPositions));
    }

    private static boolean isTouching(int[] h, int[] t) {
        for (int i = t[1] - 1; i <= t[1] + 1; i++) {
            for (int j = t[0] - 1; j <= t[0] + 1; j++) {
                if (i == h[1] && j == h[0]) return true;
            }
        }

        return false;
    }

    private static void moveTail(int[] h, int[] t) {
        t[0] += Integer.signum(h[0] - t[0]);
        t[1] += Integer.signum(h[1] - t[1]);
    }

    private static long distinctCount(List<int[]> positions) {
        record Cordinates(int x, int y) {};

        return positions.stream()
            .map(pos -> new Cordinates(pos[0], pos[1]))
            .distinct()
            .count();
    }
}
