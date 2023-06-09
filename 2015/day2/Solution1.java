import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        long requiredPaper = Files.readAllLines(Path.of("./input.txt")).stream()
            .map(line -> line.split("x"))
            .map(split -> new int[]{ Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]) })
            .map(dimension -> new int[]{ dimension[0] * dimension[1], dimension[1] * dimension[2], dimension[0] * dimension[2] })
            .mapToInt(sides -> 2 * sides[0] + 2 * sides[1] + 2 * sides[2] + Math.min(sides[0], Math.min(sides[1], sides[2])))
            .sum();

        System.out.println(requiredPaper);
    }
}
