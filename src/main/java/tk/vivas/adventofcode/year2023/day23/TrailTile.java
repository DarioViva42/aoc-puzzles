package tk.vivas.adventofcode.year2023.day23;

import java.util.Arrays;

enum TrailTile {
    FOREST('#'),
    PATH('.'),
    SLOPE_NORTH('^'),
    SLOPE_EAST('>'),
    SLOPE_SOUTH('v'),
    SLOPE_WEST('<');

    private final char displayChar;

    TrailTile(char displayChar) {
        this.displayChar = displayChar;
    }

    static TrailTile of(char displayChar) {
        return Arrays.stream(values())
                .filter(tile -> tile.displayChar == displayChar)
                .findFirst().orElseThrow();
    }

    public char getDisplayChar() {
        return displayChar;
    }
}
