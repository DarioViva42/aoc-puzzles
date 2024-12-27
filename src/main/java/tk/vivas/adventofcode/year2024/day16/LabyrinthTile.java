package tk.vivas.adventofcode.year2024.day16;

import tk.vivas.Position;
import tk.vivas.adventofcode.Direction;

import java.util.stream.Stream;

import static tk.vivas.adventofcode.year2024.day16.LabyrinthTileType.*;

class LabyrinthTile {

    private final LabyrinthTileType type;
    private LabyrinthTile northNeighbour;
    private LabyrinthTile eastNeighbour;
    private LabyrinthTile southNeighbour;
    private LabyrinthTile westNeighbour;
    private Position position;

    LabyrinthTile(int character) {
        type = of(character);
    }

    static LabyrinthTile initialize(LabyrinthTile[][] map, int x, int y) {
        LabyrinthTile labyrinthTile = map[y][x];

        labyrinthTile.northNeighbour = map[y - 1][x];
        labyrinthTile.eastNeighbour = map[y][x + 1];
        labyrinthTile.southNeighbour = map[y + 1][x];
        labyrinthTile.westNeighbour = map[y][x - 1];

        labyrinthTile.position = new Position(x, y);

        return labyrinthTile;
    }

    LabyrinthTile getNeighbour(Direction direction) {
        return switch (direction) {
            case NORTH -> northNeighbour;
            case EAST -> eastNeighbour;
            case SOUTH -> southNeighbour;
            case WEST -> westNeighbour;
        };
    }

    boolean isStart() {
        return START == type;
    }

    boolean isEnd() {
        return END == type;
    }

    boolean isWall() {
        return WALL == type;
    }

    boolean isFork() {
        if (isWall()) {
            return false;
        }

        return Stream.of(northNeighbour, eastNeighbour, southNeighbour, westNeighbour)
                .filter(neighbour -> PATH == neighbour.type)
                .count() >= 3L;
    }

    ReindeerState stateWithDirection(Direction direction) {
        return new ReindeerState(position, direction);
    }
}
