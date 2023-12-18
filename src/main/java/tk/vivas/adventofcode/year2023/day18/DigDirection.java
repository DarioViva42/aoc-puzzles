package tk.vivas.adventofcode.year2023.day18;

import java.util.Arrays;

enum DigDirection {
    UP("U", '3'),
    RIGHT("R", '0'),
    DOWN("D", '1'),
    LEFT("L", '2');

    private final String value;
    private final char directionChar;

    DigDirection(String value, char directionChar) {
        this.value = value;
        this.directionChar = directionChar;
    }

    static DigDirection from(String value) {
        return Arrays.stream(values())
                .filter(direction -> direction.value.equals(value))
                .findFirst().orElseThrow();
    }

    static DigDirection fromColorChar(char directionChar) {
        return Arrays.stream(values())
                .filter(direction -> direction.directionChar == directionChar)
                .findFirst().orElseThrow();
    }
}
