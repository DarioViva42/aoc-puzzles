package tk.vivas.adventofcode.year2023.day23;

import java.util.Arrays;

enum TrailTile {
    FOREST('#', "██"),
    PATH('.', "  "),
    SLOPE_NORTH('^', "^^"),
    SLOPE_EAST('>', ">>"),
    SLOPE_SOUTH('v', "vv"),
    SLOPE_WEST('<', "<<");

    private final char inputChar;
    private final String display;

    TrailTile(char inputChar, String display) {
        this.inputChar = inputChar;
        this.display = display;
    }

    static TrailTile of(char inputChar) {
        return Arrays.stream(values())
                .filter(tile -> tile.inputChar == inputChar)
                .findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return display;
    }
}
