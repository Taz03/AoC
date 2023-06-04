package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.LongUnaryOperator;

class Solution {
    static class Monkey {
        Queue<Long> items;
        LongUnaryOperator worryLevelOperator;
        int divisibleBy;
        int firstMonkeyIndex;
        int secondMonkeyIndex;
        int inspected = 0;

        Monkey(Queue<Long> items, LongUnaryOperator worryLevelOperator, int divisibleBy, int firstMonkeyIndex, int secondMonkeyIndex) {
            this.items = items;
            this.worryLevelOperator = worryLevelOperator;
            this.divisibleBy = divisibleBy;
            this.firstMonkeyIndex = firstMonkeyIndex;
            this.secondMonkeyIndex = secondMonkeyIndex;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] monkeysData = Files.readString(Path.of("input.txt")).split("\\n\\n");

        part1(makeMonkeys(monkeysData));
        part2(makeMonkeys(monkeysData));
    }

    private static void part1(List<Monkey> monkeys) {
        for (Monkey monkey : monkeys) monkey.worryLevelOperator = monkey.worryLevelOperator.andThen(value -> Math.round(value / 3));

        throwStuff(monkeys, 20);
        System.out.println(topTwoProduct(monkeys));
    }
    
    private static void part2(List<Monkey> monkeys) {
        throwStuff(monkeys, 10_000);
        System.out.println(topTwoProduct(monkeys));
    }

    private static List<Monkey> makeMonkeys(String[] monkeysData) {
        List<Monkey> monkeys = new ArrayList<>();

        for (String monkeyData : monkeysData) {
            String[] monkeyDataParts = monkeyData.split("\\n");

            List<Long> itemsList = Arrays.stream(monkeyDataParts[1].replaceAll("[^\\d\\s]", "").trim().split(" "))
                .map(Long::parseLong)
                .toList();
            Queue<Long> items = new LinkedList<>();
            items.addAll(itemsList);

            String[] operation = monkeyDataParts[2].split(" ");
            LongUnaryOperator worryLevelOperator = value -> value * value;
            if (operation[operation.length - 1].matches("\\d+")) {
                int num = Integer.parseInt(operation[operation.length - 1]);

                worryLevelOperator = switch(operation[operation.length - 2].charAt(0)) {
                    case '+' -> value -> value + num;
                    case '*' -> value -> value * num;
                    default -> value -> value;
                };
            }

            int divisibleBy = Integer.parseInt(monkeyDataParts[3].trim().split(" ")[3]);
            int firstMonkeyIndex = Integer.parseInt(monkeyDataParts[4].trim().split(" ")[5]);
            int secondMonkeyIndex = Integer.parseInt(monkeyDataParts[5].trim().split(" ")[5]);
            
            monkeys.add(new Monkey(items, worryLevelOperator, divisibleBy, firstMonkeyIndex, secondMonkeyIndex));
        }

        return monkeys;
    }

    private static void throwStuff(List<Monkey> monkeys, int times) {
        int uniqueDivisor = monkeys.stream()
            .mapToInt(monkey -> monkey.divisibleBy)
            .reduce(1, (x, y) -> x *= y);
        
        for (int i = 0; i < times; i++) {
            for (Monkey monkey : monkeys) {
                int items = monkey.items.size();

                for (int j = 0; j < items; j++, monkey.inspected++) {
                    long newWorryLevel = monkey.worryLevelOperator.applyAsLong(monkey.items.poll()) % uniqueDivisor;
                    
                    int newMonkey;
                    if (newWorryLevel % monkey.divisibleBy == 0) newMonkey = monkey.firstMonkeyIndex;
                    else newMonkey = monkey.secondMonkeyIndex;
                    monkeys.get(newMonkey).items.add(newWorryLevel);
                }
            }
        }
    }

    private static long topTwoProduct(List<Monkey> monkeys) {
        return monkeys.stream()
            .map(monkey -> monkey.inspected)
            .sorted(Comparator.reverseOrder())
            .mapToLong(i -> i)
            .limit(2)
            .reduce(1, (x, y) -> x *= y);
    }
}

