package tk.vivas.adventofcode.year2024.day08;

import tk.vivas.Pair;
import tk.vivas.Position;

import java.util.List;
import java.util.stream.Stream;

class SimpleAntinodeFinder extends AntinodeFinder {

    SimpleAntinodeFinder(List<Position> antennas, int width, int height) {
        super(antennas, width, height);
    }

    @Override
    Stream<Position> getAntinodes(Pair<Position> pair) {
        Position a = pair.a();
        Position b = pair.b();

        int differenceX = a.x() - b.x();
        int differenceY = a.y() - b.y();

        return Stream.<Position>builder()
                .add(new Position(a.x() + differenceX, a.y() + differenceY))
                .add(new Position(b.x() - differenceX, b.y() - differenceY))
                .build()
                .filter(this::isInBoundary);
    }
}
