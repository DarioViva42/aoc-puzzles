package tk.vivas.adventofcode.year2025.day09;

import tk.vivas.Position;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

class MovieTheater {

    private final List<Position> tilePositions;

    MovieTheater(String input) {
        tilePositions = input.lines()
                .map(this::parsePosition)
                .toList();
    }

    private Position parsePosition(String line) {
        String[] split = line.split(",", 2);
        return new Position(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    long largestRectangle() {
        return IntStream.range(0, tilePositions.size() - 1)
                .mapToObj(i -> {
                    Position a = tilePositions.get(i);
                    return IntStream.range(i + 1, tilePositions.size())
                            .mapToObj(tilePositions::get)
                            .map(b -> new TileSquare(a, b));
                })
                .flatMap(Function.identity())
                .mapToLong(TileSquare::area)
                .max().orElseThrow();
    }
}
