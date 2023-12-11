package tk.vivas.adventofcode.year2023.day10;

import java.util.Arrays;

enum PipeType {
    NORTH_EAST('L', " └"),
    NORTH_SOUTH('|', " │"),
    NORTH_WEST('J', "─┘"),
    EAST_SOUTH('F', " ┌"),
    EAST_WEST('-', "──"),
    SOUTH_WEST('7', "─┐"),
    START('S', " S"),
    EMPTY('.', "  ");

    private final char inputChar;
    private final String displayString;

    PipeType(char inputChar, String displayString) {
        this.inputChar = inputChar;
        this.displayString = displayString;
    }

    static PipeType from(char input) {
        return Arrays.stream(values())
                .filter(pipeType -> pipeType.inputChar == input)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unexpected value: " + input));
    }

    static PipeType from(Direction first, Direction last) {
        return switch (first) {
            case NORTH -> switch (last) {
                case EAST -> NORTH_EAST;
                case SOUTH -> NORTH_SOUTH;
                case WEST -> NORTH_WEST;
                case NORTH -> throw createTwoEqualDirectionsException();
            };
            case EAST -> switch (last) {
                case NORTH -> NORTH_EAST;
                case SOUTH -> EAST_SOUTH;
                case WEST -> EAST_WEST;
                case EAST -> throw createTwoEqualDirectionsException();
            };
            case SOUTH -> switch (last) {
                case NORTH -> NORTH_SOUTH;
                case EAST -> EAST_SOUTH;
                case WEST -> SOUTH_WEST;
                case SOUTH -> throw createTwoEqualDirectionsException();
            };
            case WEST -> switch (last) {
                case NORTH -> NORTH_WEST;
                case EAST -> EAST_WEST;
                case SOUTH -> SOUTH_WEST;
                case WEST -> throw createTwoEqualDirectionsException();
            };
        };
    }

    private static IllegalStateException createTwoEqualDirectionsException() {
        return new IllegalStateException("can not create type with two equal directions");
    }

    boolean connectsNorth() {
        return equals(NORTH_EAST) || equals(NORTH_SOUTH) || equals(NORTH_WEST);
    }

    boolean connectsEast() {
        return equals(NORTH_EAST) || equals(EAST_SOUTH) || equals(EAST_WEST);
    }

    boolean connectsSouth() {
        return equals(NORTH_SOUTH) || equals(EAST_SOUTH) || equals(SOUTH_WEST);
    }

    boolean connectsWest() {
        return equals(NORTH_WEST) || equals(EAST_WEST) || equals(SOUTH_WEST);
    }

    @Override
    public String toString() {
        return displayString;
    }
}
