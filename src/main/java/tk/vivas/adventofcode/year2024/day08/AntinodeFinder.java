package tk.vivas.adventofcode.year2024.day08;

import tk.vivas.Pair;
import tk.vivas.Position;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

abstract class AntinodeFinder {
    private final List<Position> antennas;

    private final int width;
    private final int height;

    AntinodeFinder(List<Position> antennas, int width, int height) {
        this.antennas = antennas;

        this.width = width;
        this.height = height;
    }

    Stream<Position> findAntinodes() {
        int numberOfAntennas = antennas.size();
        return IntStream.range(0, numberOfAntennas - 1)
                .mapToObj(i -> IntStream.range(i + 1, numberOfAntennas)
                        .mapToObj(j -> createAntennaPair(i, j)))
                .flatMap(identity())
                .flatMap(this::getAntinodes);
    }

    private Pair<Position> createAntennaPair(int i, int j) {
        return new Pair<>(antennas.get(i), antennas.get(j));
    }

    boolean isInBoundary(Position position) {
        return position.x() >= 0 && position.x() < width
                && position.y() >= 0 && position.y() < height;
    }

    abstract Stream<Position> getAntinodes(Pair<Position> pair);
}
