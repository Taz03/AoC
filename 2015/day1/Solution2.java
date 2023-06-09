import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        int floor = 0;
        int position = 0;

        for (char instruction : Files.readString(Path.of("./input.txt")).toCharArray()) {
            position++;

            if (instruction == '(') floor += 1;
            else if (instruction == ')') floor -= 1;

            if (floor < 0) break;
        }

        System.out.println(position);
    }
}
