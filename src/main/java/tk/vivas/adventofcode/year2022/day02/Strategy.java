package tk.vivas.adventofcode.year2022.day02;

enum Strategy {
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
