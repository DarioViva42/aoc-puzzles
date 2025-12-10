package tk.vivas.adventofcode.year2025.day09;

import java.util.stream.IntStream;
import java.util.stream.Stream;

record TileSquare(ModifiablePosition cornerA, ModifiablePosition cornerB, long area) {
    TileSquare(ModifiablePosition a, ModifiablePosition b) {
        this(a, b, width(a, b) * height(a, b));
    }

    private static long height(ModifiablePosition a, ModifiablePosition b) {
        return Math.abs(a.getY() - b.getY()) + 1;
    }

    private static long width(ModifiablePosition a, ModifiablePosition b) {
        return Math.abs(a.getX() - b.getX()) + 1;
    }

    public boolean check(byte[][] map) {
        int minX = Math.min(cornerA.getX(), cornerB.getX());
        int maxX = Math.max(cornerA.getX(), cornerB.getX());

        int minY = Math.min(cornerA.getY(), cornerB.getY());
        int maxY = Math.max(cornerA.getY(), cornerB.getY());

        IntStream t = IntStream.rangeClosed(minX, maxX).map(x -> map[minY][x]);
        IntStream b = IntStream.rangeClosed(minX, maxX).map(x -> map[maxY][x]);
        IntStream l = IntStream.rangeClosed(minY, maxY).map(y -> map[y][minX]);
        IntStream r = IntStream.rangeClosed(minY, maxY).map(y -> map[y][maxX]);

        return Stream.of(t, b, l, r)
                .flatMap(IntStream::boxed)
                .noneMatch(tile -> tile == 0);
    }
}
