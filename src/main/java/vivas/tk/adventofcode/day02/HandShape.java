package vivas.tk.adventofcode.day02;

enum HandShape {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int score;

    HandShape(int score) {
        this.score = score;
    }

    public static HandShape ofCharacter(char c) {
        if (c == 'A' || c == 'X') {
            return ROCK;
        }
        if (c == 'B' || c == 'Y') {
            return PAPER;
        }
        return SCISSORS;
    }

    private static HandShape ofScore(int score) {
        if (score == 1) {
            return ROCK;
        }
        if (score == 2) {
            return PAPER;
        }
        return SCISSORS;
    }

    public int getScore() {
        return score;
    }

    public HandShape getWinningHandShape() {
        return ofScore((score + 1) % 3);
    }

    public HandShape getLoosingHandShape() {
        return ofScore((score - 1) % 3);
    }

    public boolean winsAgainst(HandShape opponent) {
        return score - opponent.score == 1 || opponent.score - score == 2;
    }
}
