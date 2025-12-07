package tk.vivas.adventofcode.year2025.day07;

class Tile {
    final TileType tileType;
    boolean wasVisited = false;

    Tile(char character) {
        tileType = switch (character) {
            case '.', 'S' -> TileType.EMPTY_SPACE;
            case '^' -> TileType.SPLITTER;
            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
    }

    void visit() {
        wasVisited = true;
    }

    boolean isSplitter() {
        return TileType.SPLITTER.equals(tileType);
    }

    boolean wasVisited() {
        return wasVisited;
    }
}
