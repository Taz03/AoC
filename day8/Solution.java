package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public static void main(String... args) throws IOException {
        List<int[]> trees = Files.readAllLines(Path.of("input.txt")).stream()
            .map(line -> line.split(""))
            .map(splitted -> Arrays.stream(splitted).mapToInt(Integer::parseInt).toArray())
            .toList();

        part1(trees);
        part2(trees);
    }

    private static void part1(List<int[]> trees) {
        int visibleTrees = (trees.get(0).length * 2) + (trees.size() * 2) - 4;

        for (int i = 1; i < trees.size() - 1; i++)  {
            int[] row = trees.get(i);

            for (int j = 1; j < row.length - 1; j++) {
                visibleTrees += visible(trees, i, j) ? 1 : 0;
            }
        }

        System.out.println(visibleTrees);
    }

    private static void part2(List<int[]> trees) {
        List<Integer> senericScores = new ArrayList<>();

        for (int i = 1; i < trees.size() - 1; i++)  {
            int[] row = trees.get(i);

            for (int j = 1; j < row.length - 1; j++) {
                senericScores.add(senericScore(trees, i, j));
            }
        }

        int max = senericScores.stream()
            .map(String::valueOf)
            .mapToInt(Integer::parseInt)
            .max().getAsInt();

        System.out.println(max);
    }
    
    private static boolean visible(List<int[]> trees, int x, int y) {
        int treeLength = trees.get(x)[y];

        boolean top = true;
        for (int i = 0; i < x; i++) {
            top = trees.get(i)[y] < treeLength;
            if (!top) break;
        }

        boolean bottom = true;
        for (int i = x + 1; i < trees.size(); i++) {
            bottom = trees.get(i)[y] < treeLength;
            if (!bottom) break;
        }

        boolean left = true;
        for (int i = 0; i < y; i++) {
            left = trees.get(x)[i] < treeLength;
            if (!left) break;
        }

        boolean right = true;
        for (int i = y + 1; i < trees.get(x).length; i++) {
            right = trees.get(x)[i] < treeLength;
            if (!right) break;
        }

        return (top || bottom) || (left || right);
    }

    private static int senericScore(List<int[]> trees, int x, int y) {
        int treeLength = trees.get(x)[y];

        int top = 1;
        for (int i = x - 1; i > 0; i--, top++) {
            if (!(trees.get(i)[y] < treeLength)) break;
        }

        int bottom = 1;
        for (int i = x + 1; i < trees.size() - 1; i++, bottom++) {
            if (!(trees.get(i)[y] < treeLength)) break;
        }

        int left = 1;
        for (int i = y - 1; i > 0; i--, left++) {
            if (!(trees.get(x)[i] < treeLength)) break;
        }

        int right = 1;
        for (int i = y + 1; i < trees.get(x).length - 1; i++, right++) {
            if (!(trees.get(x)[i] < treeLength)) break;
        }

        return top * bottom * left * right;
    }
}
