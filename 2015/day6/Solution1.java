import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        boolean[][] grid = new boolean[1000][1000];

        for (String[] instruction : Files.readAllLines(Path.of("./input.txt")).stream()
                .map(line -> line.replaceAll("(turn |through )", "").split(" "))
                .toList()) {
            int startRow = Integer.parseInt(instruction[1].split(",")[0]);
            int startColumn = Integer.parseInt(instruction[1].split(",")[1]);

            int endRow = Integer.parseInt(instruction[2].split(",")[0]);
            int endColumn = Integer.parseInt(instruction[2].split(",")[1]);

            for (int i = startRow; i <= endRow; i++) {
                for (int j = startColumn; j <= endColumn; j++) {
                    switch (instruction[0]) {
                        case "on" -> grid[i][j] = true;
                        case "off" -> grid[i][j] = false;
                        case "toggle" -> grid[i][j] = !grid[i][j];
                    }
                }
            }
        }

        int onCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) onCount++;
            }
        }

        System.out.println(onCount);
    }
}
