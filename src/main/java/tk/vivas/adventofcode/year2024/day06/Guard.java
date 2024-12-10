package tk.vivas.adventofcode.year2024.day06;

import java.util.HashSet;
import java.util.Set;

class Guard {
    private int posX;
    private int posY;
    private Direction direction;
    private final TileType[][] map;
    private final int height;
    private final int width;
    private final Set<GuardState> pastStates;

    Guard(GuardState startState, TileType[][] map, int height, int width) {
        this.posX = startState.x();
        this.posY = startState.y();
        this.direction = startState.direction();

        this.map = map;
        this.height = height;
        this.width = width;

        pastStates = new HashSet<>();
        pastStates.add(startState);
    }

    TileType[][] labMap() {
        return map;
    }

    private boolean move() {
        return switch (getNextTile()) {
            case EMPTY, VISITED -> {
                step();
                yield false;
            }
            case OBSTACLE -> {
                direction = direction.turnRight();
                yield false;
            }
            case null -> true;
        };
    }

    private void step() {
        switch (direction) {
            case NORTH -> posY--;
            case EAST -> posX++;
            case SOUTH -> posY++;
            case WEST -> posX--;
        }
        map[posY][posX] = TileType.VISITED;
    }

    private TileType getNextTile() {
        if (isInEndPosition()) {
            return null;
        }

        return switch (direction) {
            case NORTH -> map[posY - 1][posX];
            case EAST -> map[posY][posX + 1];
            case SOUTH -> map[posY + 1][posX];
            case WEST -> map[posY][posX - 1];
        };
    }

    private boolean isInEndPosition() {
        return switch (direction) {
            case NORTH -> posY == 0;
            case EAST -> posX == width - 1;
            case SOUTH -> posY == height - 1;
            case WEST -> posX == 0;
        };
    }

    void patrol() {
        isLooping();
    }

    boolean isLooping() {
        while (true) {
            if (move()) {
                return false;
            }

            GuardState currentState = new GuardState(posX, posY, direction);
            if (pastStates.contains(currentState)) {
                return true;
            }
            pastStates.add(currentState);
        }
    }
}
