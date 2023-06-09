import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Solution1 {
    private static record Coordinate(int x, int y) {}

    public static void main(String[] args) throws IOException {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(new Coordinate(0, 0));
        
        for (char instruction : Files.readString(Path.of("./input.txt")).toCharArray()) {
            Coordinate lastPosition = coordinates.get(coordinates.size() - 1);

            switch (instruction) {
                case '^' -> coordinates.add(new Coordinate(lastPosition.x, lastPosition.y + 1));
                case 'v' -> coordinates.add(new Coordinate(lastPosition.x, lastPosition.y - 1));
                case '>' -> coordinates.add(new Coordinate(lastPosition.x + 1, lastPosition.y));
                case '<' -> coordinates.add(new Coordinate(lastPosition.x - 1, lastPosition.y));
            }
        }

        long distinctHouses = coordinates.stream().distinct().count();
        System.out.println(distinctHouses);
    }
}
