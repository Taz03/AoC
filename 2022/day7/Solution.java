package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public static void main(String... args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("input.txt"));

        Map<String, List<String>> directories = new HashMap<>();
        List<String> wd = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split("\\s");

            if (line.startsWith("$ cd")) {
                if (parts[2].equals("..")) wd.remove(wd.size() - 1);
                else wd.add(parts[2]);
            }

            if (line.startsWith("$ ls")) {
                List<String> contents = new ArrayList<>();
                String path = String.join(" ", wd);

                for (int j = i + 1; j < lines.size(); j++) {
                    String[] output = lines.get(j).split(" ");
                    
                    if (output[0].equals("$")) break;
                    else if (output[0].equals("dir")) contents.add(path + " " + output[1]);
                    else contents.add(output[0]);
                }

                directories.put(path, contents);
            }
        }

        part1(directories);
        part2(directories);
    }

    private static void part1(Map<String, List<String>> directories) {
        int sum = directories.keySet().stream()
            .mapToInt(path -> dirSize(directories, path))
            .filter(size -> size <= 100_000)
            .sum();

        System.out.println(sum);
    }

    private static void part2(Map<String, List<String>> directories) {
        int totalSpace = 70_000_000;
        int spaceRequired = 30_000_000;
        int emptySpace = totalSpace - dirSize(directories, "/");
        int spaceToClear = spaceRequired - emptySpace;

        int sizeOfDirToRemove = directories.keySet().stream()
            .mapToInt(path -> dirSize(directories, path))
            .filter(size -> size >= spaceToClear)
            .min().getAsInt();

        System.out.println(sizeOfDirToRemove);
    }
    
    private static int dirSize(Map<String, List<String>> directories, String path) {
        int size = 0;
        for (String content : directories.get(path)) {
            if (directories.containsKey(content)) size += dirSize(directories, content);
            else size += Integer.parseInt(content);
        }

        return size;
    }
}
