package tk.vivas.adventofcode.year2024.day16;

import tk.vivas.adventofcode.Direction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReindeerMaze {

    private final Set<LabyrinthTile> forks;
    private final LabyrinthTile start;
    private final Map<ReindeerState, LabyrinthPath> paths;

    ReindeerMaze(String input) {
        LabyrinthTile[][] map = input.lines()
                .map(line -> line.chars()
                        .mapToObj(LabyrinthTile::new)
                        .toArray(LabyrinthTile[]::new))
                .toArray(LabyrinthTile[][]::new);

        int height = map.length;
        int width = map[0].length;

        List<LabyrinthTile> tiles = IntStream.range(1, height - 1)
                .mapToObj(y -> IntStream.range(1, width - 1)
                        .mapToObj(x -> LabyrinthTile.initialize(map, x, y)))
                .flatMap(Function.identity())
                .toList();

        start = tiles.stream()
                .filter(LabyrinthTile::isStart)
                .findAny().orElseThrow();

        forks = tiles.stream()
                .filter(LabyrinthTile::isFork)
                .collect(Collectors.toSet());

        paths = createPaths();
    }

    private Map<ReindeerState, LabyrinthPath> createPaths() {
        List<LabyrinthPath> paths = forks.stream()
                .flatMap(this::createPaths)
                .collect(Collectors.toList());

        List<LabyrinthPath> toInvert = paths.stream()
                .filter(path -> start == path.to())
                .toList();

        List<LabyrinthPath> inverted = toInvert.stream()
                .map(LabyrinthPath::invert)
                .toList();

        paths.addAll(inverted);
        paths.removeAll(toInvert);

        return paths.stream()
                .collect(Collectors.toMap(LabyrinthPath::createStartState, Function.identity()));
    }

    int findLowestScore() {
        Map<ReindeerState, Integer> scores = new HashMap<>();
        return walkMaze(scores, start, Direction.EAST, 0);
    }

    private int walkMaze(Map<ReindeerState, Integer> scores, LabyrinthTile currentTile, Direction direction, int score) {
        if (currentTile.isEnd()) {
            return score;
        }
        ReindeerState state = currentTile.stateWithDirection(direction);
        if (score >= scores.getOrDefault(state, Integer.MAX_VALUE)) {
            return Integer.MAX_VALUE;
        }
        scores.put(state, score);

        List<LabyrinthPath> pathList = Stream.of(state, state.turnLeft(), state.turnRight())
                .map(paths::get)
                .filter(Objects::nonNull)
                .toList();

        int minScore = Integer.MAX_VALUE;
        for (LabyrinthPath path : pathList) {
            int newScore = score + path.length();
            if (direction != path.startDirection()) {
                newScore += 1000;
            }
            int recursiveScore = walkMaze(scores, path.to(), path.endDirection(), newScore);
            minScore = Math.min(recursiveScore, minScore);
        }
        return minScore;
    }

    private Stream<LabyrinthPath> createPaths(LabyrinthTile fork) {
        return Arrays.stream(Direction.values())
                .map(direction -> createPath(fork, direction))
                .filter(Objects::nonNull);
    }

    private LabyrinthPath createPath(LabyrinthTile fork, Direction direction) {
        LabyrinthTile neighbour = fork.getNeighbour(direction);
        if (neighbour.isWall()) {
            return null;
        }
        return createPath(fork, neighbour, direction, direction, 1);
    }

    private LabyrinthPath createPath(LabyrinthTile from, LabyrinthTile current,
                                     Direction startDirection, Direction direction, int length) {
        if (isForkStartOrEnd(current)) {
            return new LabyrinthPath(startDirection, direction, from, current, length);
        }
        LabyrinthTile neighbour = current.getNeighbour(direction);

        if (!neighbour.isWall()) {
            return createPath(from, neighbour, startDirection, direction, length + 1);
        }

        Direction left = direction.left();
        LabyrinthTile leftNeighbour = current.getNeighbour(left);
        if (!leftNeighbour.isWall()) {
            return createPath(from, leftNeighbour, startDirection, left, length + 1001);
        }

        Direction right = direction.right();
        LabyrinthTile rightNeighbour = current.getNeighbour(right);
        if (!rightNeighbour.isWall()) {
            return createPath(from, rightNeighbour, startDirection, right, length + 1001);
        }

        // Dead ends not needed
        return null;
    }

    private boolean isForkStartOrEnd(LabyrinthTile tile) {
        return forks.contains(tile) || tile.isStart() || tile.isEnd();
    }
}
