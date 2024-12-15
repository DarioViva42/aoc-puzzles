package tk.vivas.adventofcode.year2024.day12;

import tk.vivas.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GardenGroups {

    private final GardenTile[][] map;
    private final int height;
    private final int width;
    private final Map<Position, GardenGroupDetails> groupDetails;
    private final List<GardenTile> gardenTiles;

    GardenGroups(String input) {
        map = input.lines()
                .map(line -> line.chars()
                        .mapToObj(GardenTile::new)
                        .toArray(GardenTile[]::new))
                .toArray(GardenTile[][]::new);

        height = map.length;
        width = map[0].length;

        groupDetails = new HashMap<>();
        gardenTiles = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                gardenTiles.add(getPreparedGardenTile(y, x));
            }
        }
    }

    private GardenTile getPreparedGardenTile(int y, int x) {
        GardenTile gardenTile = map[y][x];
        gardenTile.placeTile(new Position(x, y));

        if (x > 0) gardenTile.addNeighbour(map[y][x - 1]);
        if (y > 0) gardenTile.addNeighbour(map[y - 1][x]);
        if (x < width - 1) gardenTile.addNeighbour(map[y][x + 1]);
        if (y < height - 1) gardenTile.addNeighbour(map[y + 1][x]);

        return gardenTile;
    }


    long totalFencingPrice() {        
        gardenTiles.forEach(GardenTile::combine);
        gardenTiles.forEach(this::evaluatePlot);
        return groupDetails.values().stream()
                .mapToLong(GardenGroupDetails::calculateCost)
                .sum();
    }

    private void evaluatePlot(GardenTile tile) {
        Position groupId = tile.getGroupId();
        GardenGroupDetails details = groupDetails.computeIfAbsent(groupId, id -> new GardenGroupDetails());
        details.addTile();
        details.removePerimeters(tile.countNeighbours());
    }
}
