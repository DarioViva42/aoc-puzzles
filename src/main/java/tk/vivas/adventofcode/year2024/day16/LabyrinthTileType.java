package tk.vivas.adventofcode.year2024.day16;

enum LabyrinthTileType {
    START, END, PATH, WALL;

    static LabyrinthTileType of(int character) {
        return switch (character) {
            case 'S' -> START;
            case 'E' -> END;
            case '#' -> WALL;
            case '.' -> PATH;

            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }
}
