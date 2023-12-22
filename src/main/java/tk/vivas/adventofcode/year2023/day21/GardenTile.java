package tk.vivas.adventofcode.year2023.day21;

import java.util.List;

final class GardenTile {
    private final int x;
    private final int y;
    private final boolean isWalkable;
    private List<GardenTile> neighbours;

    GardenTile(int x, int y, boolean isWalkable) {
        this.x = x;
        this.y = y;
        this.isWalkable = isWalkable;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public List<GardenTile> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<GardenTile> neighbours) {
        this.neighbours = neighbours;
    }
}
