package tk.vivas.adventofcode.year2024.day12;

import tk.vivas.Position;

import java.util.ArrayList;
import java.util.List;

class GardenTile {
    private final int plantType;
    private final List<GardenTile> neighbours;
    private Position position;
    private Position groupId;

    GardenTile(int plantType) {
        this.plantType = plantType;

        neighbours = new ArrayList<>();
    }

    void placeTile(Position position) {
        this.position = position;
        this.groupId = position;
    }

    Position getGroupId() {
        return groupId;
    }

    void addNeighbour(GardenTile gardenTile) {
        if (gardenTile.plantType == plantType) {
            neighbours.add(gardenTile);
        }
    }

    int countNeighbours() {
        return neighbours.size();
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
}
