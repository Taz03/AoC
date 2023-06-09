import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        int niceCount = 0;

        for (String word : Files.readAllLines(Path.of("./input.txt"))) {
            System.out.println();
            System.out.println(word);
            boolean hasPair = false;
            pairCheck: for (int i = 0; i < word.length() - 1; i++) {
                String firstPair = word.substring(i, i + 2);
                System.out.println("First pair " + firstPair);

                for (int j = i + 2; j < word.length() - 1; j++) {
                    System.out.println("Substring " + word.substring(j, j + 2));
                    if (word.substring(j, j + 2).equals(firstPair)) {
                        hasPair = true;
                        break pairCheck;
                    }
                }
            }

            boolean hasBorderLetter = false;
            for (int i = 0; i < word.length() - 2; i++) {
                System.out.println(word.charAt(i) + " " + word.charAt(i + 2));
                if (word.charAt(i) == word.charAt(i + 2)) {
                    hasBorderLetter = true;
                    break;
                }
            }

            if (hasPair && hasBorderLetter) {
                System.out.println("Is nice");
                niceCount++;
            }
        }

        System.out.println(niceCount);
    }
}
