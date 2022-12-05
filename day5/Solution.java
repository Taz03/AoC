package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public static void main(String... args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("input.txt"));

        List<String> cargos = lines.subList(0, lines.indexOf(""));

        List<Instruction> instructions = lines.subList(lines.indexOf("") + 1, lines.size()).stream()
             .map(line -> line.replaceAll("[A-z]", "").trim().split("\\s+"))
             .map(parts -> new Instruction(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]) - 1))
             .toList();

        part1(makeStacks(cargos), instructions);
        part2(makeStacks(cargos), instructions);
    }

    private static void part1(List<Stack<String>> stacks, List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            for (int i = 0; i < instruction.n; i++) {
                stacks.get(instruction.to).push(stacks.get(instruction.from).pop());
            }
        }

        printTop(stacks);
    }
    
    private static void part2(List<Stack<String>> stacks, List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            Stack<String> tempStack = new Stack<>();

            for (int i = 0; i < instruction.n; i++) {
                tempStack.push(stacks.get(instruction.from).pop());
            }

            while (!tempStack.empty()) {
                stacks.get(instruction.to).push(tempStack.pop());
            }
        }
        
        printTop(stacks);
    }

    private static List<Stack<String>> makeStacks(List<String> cargos) {
        List<Stack<String>> stacks = new ArrayList<>();
        for (int i = 0; i < cargos.get(cargos.size() - 1).replaceAll("\\s", "").length(); i++) stacks.add(new Stack<String>());

        for (int i = cargos.size() - 2; i >= 0; i--) {
            String cargoLine = cargos.get(i);

            for (int j = 0, index = 0; j < cargoLine.length(); j += 4, index++) {
                String cargo = cargoLine.substring(j, j + 3).trim();
                if (!cargo.isEmpty()) stacks.get(index).push(cargo);
            }
        }

        return stacks;
    }

    private static void printTop(List<Stack<String>> stacks) {
        for (Stack<String> stack : stacks) System.out.print(stack.peek().replaceAll("[\\[\\]]", ""));
        System.out.println();
    }

    private static record Instruction(int n, int from, int to) { }
}
