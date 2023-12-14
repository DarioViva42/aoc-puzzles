package tk.vivas.adventofcode.year2023.day14;

import java.util.Arrays;

enum CoverType {
    ROUNDED_ROCK('O'),
    CUBE_SHAPED_ROCK('#'),
    EMPTY_SPACE('.');

    private final char input;

    CoverType(char input) {
        this.input = input;
    }

    static CoverType from(char character) {
        return Arrays.stream(CoverType.values())
                .filter(type -> type.input == character)
                .findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return String.valueOf(input);
    }
}
