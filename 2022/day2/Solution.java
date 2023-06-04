package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

class Solution {
    private static final int lostScore = 0;
    private static final int drawScore = 3;
    private static final int wonScore = 6;

    public static void main(String... args) throws IOException {
        String[] lines = Files.readAllLines(Path.of("input.txt")).toArray(new String[0]);
        
        int score1 = Arrays.stream(lines)
            .map(line -> line.split(" "))
            .map(splitted -> new Move(splitted[0], splitted[1]))
            .mapToInt(Solution::determineResult)
            .sum();

        System.out.println("part 1 " + score1);

        int score2 = Arrays.stream(lines)
            .map(line -> line.split(" "))
            .map(splitted -> new Move(splitted[0], splitted[1]))
            .mapToInt(move -> determineMove(move) + determineScore(move))
            .sum();

        System.out.println("part 2 " + score2);
    }

    private static int determineResult(Move move) {
        int score = 0;

        if (move.playerMove.equals("X")) {
            if (move.opponentMove.equals("A")) score = drawScore;
            if (move.opponentMove.equals("B")) score = lostScore; 
            if (move.opponentMove.equals("C")) score = wonScore;

            score += 1;
        } else if (move.playerMove.equals("Y")) {
            if (move.opponentMove.equals("A")) score = wonScore;
            if (move.opponentMove.equals("B")) score = drawScore;
            if (move.opponentMove.equals("C")) score = lostScore;

            score += 2;
        } else if (move.playerMove.equals("Z")) {
            if (move.opponentMove.equals("A")) score = lostScore;
            if (move.opponentMove.equals("B")) score = wonScore;
            if (move.opponentMove.equals("C")) score = drawScore;

            score += 3;
        }

        return score;
    }

    private static int determineMove(Move move) {
        if (move.opponentMove.equals("A")) {
            if (move.playerMove.equals("X")) return 3;
            if (move.playerMove.equals("Y")) return 1;
            if (move.playerMove.equals("Z")) return 2;
        } else if (move.opponentMove.equals("B")) {
            if (move.playerMove.equals("X")) return 1;
            if (move.playerMove.equals("Y")) return 2;
            if (move.playerMove.equals("Z")) return 3;
        } else {
            if (move.playerMove.equals("X")) return 2;
            if (move.playerMove.equals("Y")) return 3;
            if (move.playerMove.equals("Z")) return 1;
        }

        return 0;
    }

    private static int determineScore(Move move) {
        if (move.playerMove.equals("X")) return lostScore;
        if (move.playerMove.equals("Y")) return drawScore;
        else return wonScore;
    }

    private static record Move(String opponentMove, String playerMove) {}
}
