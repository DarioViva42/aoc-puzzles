package tk.vivas.adventofcode.year2024.day15;

import tk.vivas.Position;

import java.util.stream.Stream;

import static tk.vivas.adventofcode.year2024.day15.WarehouseTileType.*;

class WarehouseTile {

    private WarehouseTileType type;
    private Position position;

    private WarehouseTile northNeighbour;
    private WarehouseTile eastNeighbour;
    private WarehouseTile southNeighbour;
    private WarehouseTile westNeighbour;

    private WarehouseTile(WarehouseTileType type) {
        this.type = type;
    }

    static WarehouseTile of(WarehouseTileType type) {
        return new WarehouseTile(type);
    }

    static WarehouseTile of(int character) {
        WarehouseTileType type = switch (character) {
            case 'O' -> BOX;
            case '#' -> WALL;
            case '.' -> EMPTY;
            case '@' -> ROBOT;

            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
        return new WarehouseTile(type);
    }

    static WarehouseTile initializeTile(WarehouseTile[][] map, int x, int y) {
        WarehouseTile warehouseTile = map[y][x];

        warehouseTile.northNeighbour = map[y - 1][x];
        warehouseTile.eastNeighbour = map[y][x + 1];
        warehouseTile.southNeighbour = map[y + 1][x];
        warehouseTile.westNeighbour = map[y][x - 1];

        warehouseTile.position = new Position(x, y);

        return warehouseTile;
    }

    boolean isRobot() {
        return ROBOT == type;
    }

    boolean isBox() {
        return BOX == type;
    }

    boolean isLeftPartOfBox() {
        return LEFT_BOX == type;
    }

    WarehouseTile move(Direction direction) {
        return internalMove(direction) ? getNeighbour(direction) : this;
    }

    private boolean internalMove(Direction direction) {
        if (WALL == type) {
            return false;
        }
        if (EMPTY == type) {
            return true;
        }
        WarehouseTile neighbour = getNeighbour(direction);
        if (!neighbour.internalMove(direction)) {
            return false;
        }
        moveTo(neighbour);
        return true;
    }

    WarehouseTile largeMove(Direction direction) {
        if (!checkMove(direction)) {
            return this;
        }
        internalLargeMove(direction);
        return getNeighbour(direction);
    }

    private boolean checkMove(Direction direction) {
        if (WALL == type) {
            return false;
        }
        if (EMPTY == type) {
            return true;
        }

        WarehouseTile neighbour = getNeighbour(direction);
        return switch (type) {
            case EMPTY, BOX, WALL -> throw new IllegalStateException("Unexpected value: " + type);
            case ROBOT -> neighbour.checkMove(direction);
            case LEFT_BOX -> switch (direction) {
                case NORTH, SOUTH -> neighbour.checkMove(direction)
                        && neighbour.eastNeighbour.checkMove(direction);
                case EAST -> eastNeighbour.eastNeighbour.checkMove(direction);
                case WEST -> throw new IllegalStateException("Unexpected value: " + type);
            };
            case RIGHT_BOX -> switch (direction) {
                case NORTH, SOUTH -> neighbour.checkMove(direction)
                        && neighbour.westNeighbour.checkMove(direction);
                case EAST -> throw new IllegalStateException("Unexpected value: " + type);
                case WEST -> westNeighbour.westNeighbour.checkMove(direction);
            };
        };
    }

    private void internalLargeMove(Direction direction) {
        if (WALL == type) {
            return;
        }
        if (EMPTY == type) {
            return;
        }

        WarehouseTile neighbour = getNeighbour(direction);
        switch (type) {
            case EMPTY, BOX, WALL -> throw new IllegalStateException("Unexpected value: " + type);
            case ROBOT -> neighbour.internalLargeMove(direction);
            case LEFT_BOX -> {
                switch (direction) {
                    case NORTH, SOUTH -> {
                        neighbour.internalLargeMove(direction);
                        neighbour.eastNeighbour.internalLargeMove(direction);
                    }
                    case EAST -> eastNeighbour.eastNeighbour.internalLargeMove(direction);
                    case WEST -> throw new IllegalStateException("Unexpected value: " + type);
                }
            }
            case RIGHT_BOX -> {
                switch (direction) {
                    case NORTH, SOUTH -> {
                        neighbour.internalLargeMove(direction);
                        neighbour.westNeighbour.internalLargeMove(direction);
                    }
                    case EAST -> throw new IllegalStateException("Unexpected value: " + type);
                    case WEST -> westNeighbour.westNeighbour.internalLargeMove(direction);
                }
            }
        }

        actuallyMove(direction);
    }

    private void actuallyMove(Direction direction) {
        WarehouseTile neighbour = getNeighbour(direction);

        switch (direction) {
            case NORTH, SOUTH -> {
                switch (type) {
                    case LEFT_BOX -> eastNeighbour.moveTo(neighbour.eastNeighbour);
                    case RIGHT_BOX -> westNeighbour.moveTo(neighbour.westNeighbour);
                }
            }
            case EAST, WEST -> {
                switch (type) {
                    case LEFT_BOX -> neighbour.moveTo(neighbour.eastNeighbour);
                    case RIGHT_BOX -> neighbour.moveTo(neighbour.westNeighbour);
                }
            }
        }
        this.moveTo(neighbour);
    }

    void moveTo(WarehouseTile neighbour) {
        neighbour.type = type;
        type = EMPTY;
    }

    private WarehouseTile getNeighbour(Direction direction) {
        return switch (direction) {
            case NORTH -> northNeighbour;
            case EAST -> eastNeighbour;
            case SOUTH -> southNeighbour;
            case WEST -> westNeighbour;
        };
    }

    long gpsCoordinate() {
        return 100L * position.y() + position.x();
    }

    public Stream<WarehouseTile> enlarge() {
        return switch (type) {
            case EMPTY -> Stream.of(of(EMPTY), of(EMPTY));
            case BOX -> Stream.of(of(LEFT_BOX), of(RIGHT_BOX));
            case WALL -> Stream.of(of(WALL), of(WALL));
            case ROBOT -> Stream.of(of(ROBOT), of(EMPTY));
            case LEFT_BOX, RIGHT_BOX -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
