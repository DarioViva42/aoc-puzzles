package tk.vivas.adventofcode.year2023.day10;

enum PipeType {
    NORTH_EAST, NORTH_SOUTH, NORTH_WEST, EAST_SOUTH, EAST_WEST, SOUTH_WEST, START, EMPTY;

    static PipeType from(char input) {
        return switch (input) {
            case 'L' -> NORTH_EAST;
            case '|' -> NORTH_SOUTH;
            case 'J' -> NORTH_WEST;
            case 'F' -> EAST_SOUTH;
            case '-' -> EAST_WEST;
            case '7' -> SOUTH_WEST;
            case 'S' -> START;
            case '.' -> EMPTY;
            default -> throw new IllegalStateException("Unexpected value: " + input);
        };
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
}
