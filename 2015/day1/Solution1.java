import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        int floor = 0;

        for (char instruction : Files.readString(Path.of("./input.txt")).toCharArray())
            if (instruction == '(') floor += 1;
            else if (instruction == ')') floor -= 1;

        System.out.println(floor);
    }
}
