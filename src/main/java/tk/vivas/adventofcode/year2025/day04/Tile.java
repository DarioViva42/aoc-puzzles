package tk.vivas.adventofcode.year2025.day04;

public enum Tile {
    ROLL_OF_PAPER,
    EMPTY;

    static Tile of(char symbol) {
        return switch (symbol) {
            case '@' -> ROLL_OF_PAPER;
            case '.' -> EMPTY;
            default -> {throw new IllegalArgumentException("Illegal symbol: " + symbol);}
        };
    }
}
