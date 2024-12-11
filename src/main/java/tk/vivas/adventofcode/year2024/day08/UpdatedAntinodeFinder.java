package tk.vivas.adventofcode.year2024.day08;

import tk.vivas.Pair;
import tk.vivas.Position;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UpdatedAntinodeFinder extends AntinodeFinder {

    public UpdatedAntinodeFinder(List<Position> antennas, int width, int height) {
        super(antennas, width, height);
    }

    @Override
    Stream<Position> getAntinodes(Pair<Position> pair) {
        return Stream.concat(
                getAntinodes(pair, IntStream.iterate(0, i -> i - 1)),
                getAntinodes(pair, IntStream.iterate(1, i -> i + 1)));
    }

    private Stream<Position> getAntinodes(Pair<Position> pair, IntStream iteratingStream) {
        Position a = pair.a();
        Position b = pair.b();

        int differenceX = a.x() - b.x();
        int differenceY = a.y() - b.y();

        return iteratingStream
                .mapToObj(i -> new Position(a.x() + i * differenceX, a.y() + i * differenceY))
                .takeWhile(this::isInBoundary);
    }
}
