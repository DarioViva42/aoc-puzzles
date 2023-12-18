package tk.vivas.adventofcode.year2023.day18;

import java.util.Arrays;

enum DigDirection {
    UP("U"),
    RIGHT("R"),
    DOWN("D"),
    LEFT("L");

    private final String value;

    DigDirection(String value) {
        this.value = value;
    }

    static DigDirection from(String value) {
        return Arrays.stream(values())
                .filter(direction -> direction.value.equals(value))
                .findFirst().orElseThrow();
    }
}
