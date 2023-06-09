import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Solution1 {
    public static void main(String[] args) throws IOException {
        int niceCount = 0;

        for (String word : Files.readAllLines(Path.of("./input.txt"))) {
            boolean isNice = !word.matches(".*(ab|cd|qp|xy).*");

            int vowelCount = 0;
            boolean doubleLetter = false;
            for (int i = 0; i < word.length() && isNice; i++) {
                if (vowelCount >= 3 && doubleLetter) break;

                if (i < word.length() - 1 && word.charAt(i) == word.charAt(i + 1)) doubleLetter = true;
                if (word.substring(i, i + 1).matches("[aeiou]")) vowelCount++;
            }

            if (vowelCount < 3 || !doubleLetter) isNice = false;

            if (isNice) niceCount++;
        }

        System.out.println(niceCount);
    }
}
