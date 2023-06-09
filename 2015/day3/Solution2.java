import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    private static record Coordinate(int x, int y) {}

    public static void main(String[] args) throws IOException {
        List<Coordinate> santaCoordinates = new ArrayList<>();
        List<Coordinate> roboCoordinates = new ArrayList<>();

        Coordinate origin = new Coordinate(0, 0);
        santaCoordinates.add(origin);
        roboCoordinates.add(origin);

        boolean roboTurn = false;
        for (char instruction : Files.readString(Path.of("./input.txt")).toCharArray()) {
            if (roboTurn) roboCoordinates.add(move(roboCoordinates.get(roboCoordinates.size() - 1), instruction));
            else santaCoordinates.add(move(santaCoordinates.get(santaCoordinates.size() - 1), instruction));

            roboTurn = !roboTurn;
        }

        List<Coordinate> mergedCoordinates = new ArrayList<>();
        mergedCoordinates.addAll(santaCoordinates);
        mergedCoordinates.addAll(roboCoordinates);

        long distinctHouses = mergedCoordinates.stream().distinct().count() - 1;
        System.out.println(distinctHouses);
    }

    private static Coordinate move(Coordinate lastPosition, char instruction) {
        return switch (instruction) {
            case '^' -> new Coordinate(lastPosition.x, lastPosition.y + 1);
            case 'v' -> new Coordinate(lastPosition.x, lastPosition.y - 1);
            case '>' -> new Coordinate(lastPosition.x + 1, lastPosition.y);
            case '<' -> new Coordinate(lastPosition.x - 1, lastPosition.y);
            default -> null;
        };
    }
}
