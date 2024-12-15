package tk.vivas.adventofcode.year2024.day12;

import tk.vivas.Position;

import java.util.ArrayList;
import java.util.List;

class GardenTile {
    private final int plantType;
    private final List<GardenTile> neighbours;
    private Position position;
    private Position groupId;

    private boolean topBorder;
    private boolean rightBorder;
    private boolean bottomBorder;
    private boolean leftBorder;

    GardenTile(int plantType) {
        this.plantType = plantType;

        neighbours = new ArrayList<>();
    }

    static GardenTile getPreparedGardenTile(GardenTile[][] map, int y, int x) {
        int height = map.length;
        int width = map[0].length;

        GardenTile gardenTile = map[y][x];
        gardenTile.placeTile(new Position(x, y));

        if (x > 0 && haveSamePlantType(map[y][x - 1], gardenTile)) {
            gardenTile.neighbours.add(map[y][x - 1]);
        } else {
            gardenTile.leftBorder = true;
        }
        if (y > 0 && haveSamePlantType(map[y - 1][x], gardenTile)) {
            gardenTile.neighbours.add(map[y - 1][x]);
        } else {
            gardenTile.topBorder = true;
        }
        if (x < width - 1 && haveSamePlantType(map[y][x + 1], gardenTile)) {
            gardenTile.neighbours.add(map[y][x + 1]);
        } else {
            gardenTile.rightBorder = true;
        }
        if (y < height - 1 && haveSamePlantType(map[y + 1][x], gardenTile)) {
            gardenTile.neighbours.add(map[y + 1][x]);
        } else {
            gardenTile.bottomBorder = true;
        }

        return gardenTile;
    }

    private static boolean haveSamePlantType(GardenTile a, GardenTile b) {
        return a.plantType == b.plantType;
    }

    void placeTile(Position position) {
        this.position = position;
        this.groupId = position;
    }

    Position getGroupId() {
        return groupId;
    }

    int getX() {
        return position.x();
    }

    int getY() {
        return position.y();
    }

    int countBorders() {
        return 4 - neighbours.size();
    }

    void combine() {
        if (position != groupId) {
            return;
        }
        combineNeighbours(groupId);
    }

    private void combine(Position groupId) {
        if (this.groupId == groupId) {
            return;
        }
        this.groupId = groupId;
        combineNeighbours(groupId);
    }

    private void combineNeighbours(Position groupId) {
        neighbours.forEach(neighbour -> neighbour.combine(groupId));
    }

    boolean hasTopBorder() {
        return topBorder;
    }

    boolean hasRightBorder() {
        return rightBorder;
    }

    boolean hasBottomBorder() {
        return bottomBorder;
    }

    boolean hasLeftBorder() {
        return leftBorder;
    }

    boolean nextTo(GardenTile gardenTile) {
        return getX() + 1 == gardenTile.getX();
    }

    boolean onTop(GardenTile gardenTile) {
        return getY() + 1 == gardenTile.getY();
    }
}
