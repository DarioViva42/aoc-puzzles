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

    private PrintingDepartment(List<List<Tile>> map) {
        this.map = map;

        sizeX = map.getFirst().size();
        sizeY = map.size();
    }

    PrintingDepartment(String input) {
        this(parse(input));
    }

    private static List<List<Tile>> parse(String input) {
        return input.lines().map(line -> line.chars()
                        .mapToObj(c -> Tile.of((char) c))
                        .toList())
                .toList();
    }

    long accessibleRollsOfPaper() {
        return positions()
                .filter(this::isRollOfPaper)
                .filter(this::isAccessible)
                .count();
    }

    long removableRollsOfPaper() {
        long initialAmount = countPapers();

        PrintingDepartment currentState = this;
        while (true) {
            PrintingDepartment nextState = currentState.nextState();

            if (nextState.equals(currentState)) {
                break;
            }

            currentState = nextState;
        }
        return initialAmount - currentState.countPapers();
    }

    private boolean isRollOfPaper(Position position) {
        return Tile.ROLL_OF_PAPER.equals(map.get(position.y()).get(position.x()));
    }

    private boolean isAccessible(int x, int y) {
        return Stream.of(
                        tile(x - 1, y - 1), tile(x, y - 1), tile(x + 1, y - 1),
                        tile(x - 1, y),        /* center paper */ tile(x + 1, y),
                        tile(x - 1, y + 1), tile(x, y + 1), tile(x + 1, y + 1)
                )
                .filter(Tile.ROLL_OF_PAPER::equals)
                .count() < 4;
    }

    private boolean isAccessible(Position position) {
        return isAccessible(position.x(), position.y());
    }

    private PrintingDepartment nextState() {
        List<List<Tile>> nextMap = IntStream.range(0, sizeY)
                .mapToObj(y -> IntStream.range(0, sizeX)
                        .mapToObj(x -> nextState(x, y))
                        .toList())
                .toList();
        return new PrintingDepartment(nextMap);
    }

    private Tile nextState(int x, int y) {
        Tile tile = tile(x, y);
        if (tile == Tile.EMPTY) {
            return Tile.EMPTY;
        }
        return isAccessible(x, y) ? Tile.EMPTY : Tile.ROLL_OF_PAPER;
    }

    private long countPapers() {
        return positions()
                .map(this::tile)
                .filter(Tile.ROLL_OF_PAPER::equals)
                .count();
    }

    private Tile tile(int x, int y) {
        if (x >= sizeX || y >= sizeY || x < 0 || y < 0) {
            return Tile.EMPTY;
        }
        return map.get(y).get(x);
    }

    private Tile tile(Position position) {
        return tile(position.x(), position.y());
    }

    private Stream<Position> positions() {
        return IntStream.range(0, sizeY)
                .mapToObj(y -> IntStream.range(0, sizeX)
                        .mapToObj(x -> new Position(x, y)))
                .flatMap(Function.identity());
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof PrintingDepartment that)) return false;

        return map.equals(that.map);
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }
}
