import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        long requiredRibbon = Files.readAllLines(Path.of("./input.txt")).stream()
            .map(line -> line.split("x"))
            .map(split -> new int[]{ Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]) })
            .mapToInt(dimension -> Arrays.stream(dimension).sorted().limit(2).sum() * 2 + dimension[0] * dimension[1] * dimension[2])
            .sum();

        System.out.println(requiredRibbon);
    }
}
