package tk.vivas.adventofcode.year2024.day06;

enum Direction {
    NORTH, EAST, SOUTH, WEST;

    Direction turnRight() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
        };
    }

    static Direction of(char c) {
        return switch (c) {
            case '^' -> Direction.NORTH;
            case '>' -> Direction.EAST;
            default -> throw new IllegalStateException("Illegal guard character" + c);
        };
    }
}
