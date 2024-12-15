package tk.vivas.adventofcode.year2024.day12;

import tk.vivas.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GardenGroups {
    private final Map<Position, List<GardenTile>> gardenGroups;

    GardenGroups(String input) {
        GardenTile[][] map = input.lines()
                .map(line -> line.chars()
                        .mapToObj(GardenTile::new)
                        .toArray(GardenTile[]::new))
                .toArray(GardenTile[][]::new);

        int height = map.length;
        int width = map[0].length;

        List<GardenTile> gardenTiles = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                gardenTiles.add(GardenTile.getPreparedGardenTile(map, y, x));
            }
        }
        gardenTiles.forEach(GardenTile::combine);
        gardenGroups = gardenTiles.stream()
                .collect(Collectors.groupingBy(GardenTile::getGroupId));
    }

    long totalFencingPrice() {
        return gardenGroups.keySet().stream()
                .map(this::createGardenGroupDetails)
                .mapToLong(GardenGroupDetails::calculateCost)
                .sum();
    }

    long discountedFencingPrice() {
        return gardenGroups.keySet().stream()
                .map(this::createDiscountedGroupDetails)
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

    private GardenGroupDetails createDiscountedGroupDetails(Position groupId) {
        List<GardenTile> tiles = gardenGroups.get(groupId);
        int area = tiles.size();
        int perimeter = tiles.stream()
                .mapToInt(GardenTile::countBorders)
                .sum();

        int topSavings = getTopSavings(tiles);
        int rightSavings = getRightSavings(tiles);
        int bottomSavings = getBottomSavings(tiles);
        int leftSavings = getLeftSavings(tiles);

        int savings = topSavings + rightSavings + bottomSavings + leftSavings;

        return new GardenGroupDetails(area, perimeter - savings);
    }

    private int getTopSavings(List<GardenTile> tiles) {
        return tiles.stream()
                .filter(GardenTile::hasTopBorder)
                .collect(Collectors.groupingBy(GardenTile::getY))
                .values().stream()
                .mapToInt(this::getSavingsX)
                .sum();
    }

    private int getRightSavings(List<GardenTile> tiles) {
        return tiles.stream()
                .filter(GardenTile::hasRightBorder)
                .collect(Collectors.groupingBy(GardenTile::getX))
                .values().stream()
                .mapToInt(this::getSavingsY)
                .sum();
    }

    private int getBottomSavings(List<GardenTile> tiles) {
        return tiles.stream()
                .filter(GardenTile::hasBottomBorder)
                .collect(Collectors.groupingBy(GardenTile::getY))
                .values().stream()
                .mapToInt(this::getSavingsX)
                .sum();
    }

    private int getLeftSavings(List<GardenTile> tiles) {
        return tiles.stream()
                .filter(GardenTile::hasLeftBorder)
                .collect(Collectors.groupingBy(GardenTile::getX))
                .values().stream()
                .mapToInt(this::getSavingsY)
                .sum();
    }

    private int getSavingsX(List<GardenTile> tiles) {
        return (int) IntStream.range(0, tiles.size() - 1)
                .filter(i -> tiles.get(i).nextTo(tiles.get(i + 1)))
                .count();
    }

    private int getSavingsY(List<GardenTile> tiles) {
        return (int) IntStream.range(0, tiles.size() - 1)
                .filter(i -> tiles.get(i).onTop(tiles.get(i + 1)))
                .count();
    }
}
