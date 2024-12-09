package tk.vivas.adventofcode.year2024.day06;

enum TileType {
    EMPTY, VISITED, OBSTACLE;

    static TileType of(int i) {
        return switch (i) {
            case '.' -> EMPTY;
            case '#' -> OBSTACLE;
            default -> VISITED;
        };
    }
}
