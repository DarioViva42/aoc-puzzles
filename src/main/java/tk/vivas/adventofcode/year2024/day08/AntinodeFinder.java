package tk.vivas.adventofcode.year2024.day08;

import tk.vivas.Pair;
import tk.vivas.Position;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

class AntinodeFinder {
    private final List<Position> antennas;

    AntinodeFinder(List<Position> antennas) {
        this.antennas = antennas;
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

    private Stream<Position> getAntinodes(Pair<Position> pair) {
        Position a = pair.a();
        Position b = pair.b();

        int differenceX = a.x() - b.x();
        int differenceY = a.y() - b.y();

        return Stream.<Position>builder()
                .add(new Position(a.x() + differenceX, a.y() + differenceY))
                .add(new Position(b.x() - differenceX, b.y() - differenceY))
                .build();
    }
}
