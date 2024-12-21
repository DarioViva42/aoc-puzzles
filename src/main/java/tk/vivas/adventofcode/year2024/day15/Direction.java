package tk.vivas.adventofcode.year2024.day15;

enum Direction {
    NORTH, EAST, SOUTH, WEST;

    static Direction from(int character) {
        return switch (character) {
            case '^' -> NORTH;
            case '>' -> EAST;
            case 'v' -> SOUTH;
            case '<' -> WEST;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
}
