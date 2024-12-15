package tk.vivas.adventofcode.year2024.day12;

import tk.vivas.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class GardenGroups {

    private final GardenTile[][] map;
    private final int height;
    private final int width;
    private final Map<Position, List<GardenTile>> gardenGroups;

    GardenGroups(String input) {
        map = input.lines()
                .map(line -> line.chars()
                        .mapToObj(GardenTile::new)
                        .toArray(GardenTile[]::new))
                .toArray(GardenTile[][]::new);

        height = map.length;
        width = map[0].length;

        List<GardenTile> gardenTiles = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                gardenTiles.add(getPreparedGardenTile(y, x));
            }
        }
        gardenTiles.forEach(GardenTile::combine);
        gardenGroups = gardenTiles.stream()
                .collect(Collectors.groupingBy(GardenTile::getGroupId));
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
        return gardenGroups.keySet().stream()
                .map(this::createGardenGroupDetails)
                .mapToLong(GardenGroupDetails::calculateCost)
                .sum();
    }

    private GardenGroupDetails createGardenGroupDetails(Position groupId) {
        List<GardenTile> tiles = gardenGroups.get(groupId);
        int area = tiles.size();
        int perimeter = tiles.stream()
                .mapToInt(GardenTile::countBorders)
                .sum();
        return new GardenGroupDetails(area, perimeter);
    }
}
