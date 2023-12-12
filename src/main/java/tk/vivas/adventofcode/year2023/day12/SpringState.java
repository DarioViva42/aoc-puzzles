package tk.vivas.adventofcode.year2023.day12;

import java.util.Arrays;

enum SpringState {
    OPERATIONAL('.'),
    DAMAGED('#'),
    UNKNOWN('?');

    private final char character;

    SpringState(char character) {
        this.character = character;
    }

    static SpringState from(char character) {
        return Arrays.stream(values())
                .filter(state -> character == state.character)
                .findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
