package tk.vivas.adventofcode.year2025.day09;

import tk.vivas.Position;

record TileSquare(Position cornerA, Position cornerB, long area) {
    TileSquare(Position a, Position b) {
        this(a, b, width(a, b) * height(a, b));
    }

    private static long height(Position a, Position b) {
        return Math.abs(a.y() - b.y()) + 1;
    }

    private static long width(Position a, Position b) {
        return Math.abs(a.x() - b.x()) + 1;
    }
}
