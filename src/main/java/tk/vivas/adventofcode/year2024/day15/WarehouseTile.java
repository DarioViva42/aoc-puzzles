package tk.vivas.adventofcode.year2024.day15;

import tk.vivas.Position;

class WarehouseTile {

    private WarehouseTileType type;
    private Position position;

    private WarehouseTile northNeighbour;
    private WarehouseTile eastNeighbour;
    private WarehouseTile southNeighbour;
    private WarehouseTile westNeighbour;

    WarehouseTile(int character) {
        type = switch (character) {
            case 'O' -> WarehouseTileType.BOX;
            case '#' -> WarehouseTileType.WALL;
            case '.' -> WarehouseTileType.EMPTY;
            case '@' -> WarehouseTileType.ROBOT;

            default -> throw new IllegalStateException("Unexpected value: " + character);
        };
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
        return WarehouseTileType.ROBOT == type;
    }

    boolean isBox() {
        return WarehouseTileType.BOX == type;
    }

    WarehouseTile move(Direction direction) {
        return internalMove(direction) ? getNeighbour(direction) : this;
    }

    private boolean internalMove(Direction direction) {
        WarehouseTile neighbour = getNeighbour(direction);
        if (WarehouseTileType.WALL == neighbour.type) {
            return false;
        }
        if (WarehouseTileType.EMPTY == neighbour.type) {
            neighbour.type = this.type;
            this.type = WarehouseTileType.EMPTY;
            return true;
        }
        if (!neighbour.internalMove(direction)) {
            return false;
        }
        WarehouseTileType temp = neighbour.type;
        neighbour.type = this.type;
        this.type = temp;
        return true;
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
}
