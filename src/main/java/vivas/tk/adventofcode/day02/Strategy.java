package vivas.tk.adventofcode.day02;

public enum Strategy {
    WIN,
    LOOSE,
    DRAW;

    public static Strategy ofCharacter(char c) {
        if (c == 'X') {
            return LOOSE;
        }
        if (c == 'Y') {
            return DRAW;
        }
        return WIN;
    }
}
