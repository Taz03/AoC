package day10;

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
        int sumAt = 20;
        int sum = 0;
        int cycles = 0;
        int xValue = 1;

        for (String line : lines) {
            String[] parts = line.split(" ");

            int currCycles = parts[0].equals("addx") ? 2 : 1;
            if (cycles + currCycles >= sumAt) {
                sum += xValue * sumAt;
                sumAt += 40;
            }

            cycles += currCycles;
            if (currCycles == 2) xValue += Integer.parseInt(parts[1]);
        }

        System.out.println(sum);
    }

    private static void part2(List<String> lines) {
        int cycles = 0;
        int xValue = 1;
        String crt = "";

        for (String line : lines) {
            String[] parts = line.split(" ");

            int currCycles = parts[0].equals("addx") ? 2 : 1;
            for (int i = 0; i < currCycles; i++) {
                cycles++;

                if (cycles > 40) {
                    cycles = 1;
                    crt += "\n";
                }
                if (cycles >= xValue && cycles <= xValue + 2) crt += "#";
                else crt += ".";
            }

            if (currCycles == 2) xValue += Integer.parseInt(parts[1]);
        }

        System.out.println(crt);
    }
}
