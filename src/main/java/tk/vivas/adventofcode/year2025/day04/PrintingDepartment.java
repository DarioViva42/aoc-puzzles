package tk.vivas.adventofcode.year2025.day04;

import tk.vivas.Position;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class PrintingDepartment {

    private final List<List<Tile>> map;
    private final int sizeX;
    private final int sizeY;

    PrintingDepartment(String input) {
        map = input.lines().map(line -> line.chars()
                        .mapToObj(c -> Tile.of((char) c))
                        .toList())
                .toList();

        sizeX = map.getFirst().size();
        sizeY = map.size();
    }

    long accessibleRollsOfPaper() {
        return IntStream.range(0, sizeY)
                .mapToObj(y -> IntStream.range(0, sizeX)
                        .mapToObj(x -> new Position(x, y)))
                .flatMap(Function.identity())
                .filter(this::isRollOfPaper)
                .filter(this::isAccessible)
                .count();
    }

    private boolean isRollOfPaper(Position position) {
        return Tile.ROLL_OF_PAPER.equals(map.get(position.y()).get(position.x()));
    }

    private boolean isAccessible(Position position) {
        int x = position.x();
        int y = position.y();
        return Stream.of(
                        tile(x - 1, y - 1), tile(x, y - 1), tile(x + 1, y - 1),
                        tile(x - 1, y),        /* center paper */ tile(x + 1, y),
                        tile(x - 1, y + 1), tile(x, y + 1), tile(x + 1, y + 1)
                )
                .filter(Tile.ROLL_OF_PAPER::equals)
                .count() < 4;
    }

    private Tile tile(int x, int y) {
        if (x >= sizeX || y >= sizeY || x < 0 || y < 0) {
            return Tile.EMPTY;
        }
        return map.get(y).get(x);
    }
}
