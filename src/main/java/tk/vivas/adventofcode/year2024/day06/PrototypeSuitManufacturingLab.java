package tk.vivas.adventofcode.year2024.day06;

import tk.vivas.Position;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static tk.vivas.adventofcode.year2024.day06.TileType.OBSTACLE;
import static tk.vivas.adventofcode.year2024.day06.TileType.VISITED;

class PrototypeSuitManufacturingLab {

    private final Guard guard;
    private final TileType[][] labMap;
    private final int height;
    private final int width;
    private final GuardState guardStartState;

    PrototypeSuitManufacturingLab(String input) {
        List<String> lines = input.lines().toList();
        height = lines.size();
        width = lines.getFirst().length();

        GuardState start = null;

        labMap = new TileType[height][width];

        for (int j = 0; j < height; j++) {
            char[] line = lines.get(j).toCharArray();
            for (int i = 0; i < width; i++) {
                char c = line[i];
                TileType tile = TileType.of(c);

                if (VISITED.equals(tile)) {
                    start = new GuardState(i, j, Direction.of(c));
                }

                labMap[j][i] = tile;
            }
        }

        guardStartState = Objects.requireNonNull(start);

        guard = createGuard(cloneMap());
    }

    long countVisitedPositions() {
        guard.patrol();

        return Arrays.stream(guard.labMap())
                .flatMap(Arrays::stream)
                .filter(VISITED::equals)
                .count();
    }

    long countPossibleObstaclePositions() {
        guard.patrol();

        return createPositionStream()
                .filter(this::hasGuardVisited)
                .filter(this::isNotStartPosition)
                .map(this::createAdaptedMap)
                .map(this::createGuard)
                .filter(Guard::isLooping)
                .count();
    }

    private Stream<Position> createPositionStream() {
        return IntStream.range(0, width)
                .mapToObj(x ->
                        IntStream.range(0, width).mapToObj(y -> new Position(x, y)))
                .flatMap(Function.identity());
    }

    private boolean hasGuardVisited(Position position) {
        return VISITED == guard.labMap()[position.y()][position.x()];
    }

    private boolean isNotStartPosition(Position position) {
        return position.x() != guardStartState.x()
                || position.y() != guardStartState.y();
    }

    private TileType[][] createAdaptedMap(Position position) {
        TileType[][] adapted = cloneMap();
        adapted[position.y()][position.x()] = OBSTACLE;
        return adapted;
    }

    private Guard createGuard(TileType[][] map) {
        return new Guard(guardStartState, map, height, width);
    }

    private TileType[][] cloneMap() {
        TileType[][] clone = new TileType[height][width];
        for (int j = 0; j < height; j++) {
            System.arraycopy(labMap[j], 0, clone[j], 0, width);
        }
        return clone;
    }
}
