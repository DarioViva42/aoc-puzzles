package tk.vivas.adventofcode.year2023.day21;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WalkableGarden {

    private final int sizeX;
    private final int sizeY;
    private final List<GardenTile> gardenTiles;
    private GardenTile start;

    WalkableGarden(String input) {
        List<String> lines = input.lines().toList();
        sizeX = lines.getFirst().length();
        sizeY = lines.size();
        gardenTiles = new ArrayList<>();
        GardenTile[][] gardenTileArray = new GardenTile[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            String line = lines.get(y);
            for (int x = 0; x < sizeX; x++) {
                char c = line.charAt(x);
                GardenTile gardenTile = new GardenTile(x, y, c != '#');
                gardenTileArray[x][y] = gardenTile;
                if (c == 'S') {
                    start = gardenTile;
                }
                gardenTiles.add(gardenTile);
            }
        }
        initNeighbours(gardenTileArray);
    }

    private void initNeighbours(GardenTile[][] array) {
        gardenTiles.stream()
                .filter(GardenTile::isWalkable)
                .forEach(gardenTile -> {
                    int x = gardenTile.x();
                    int y = gardenTile.y();

                    Stream.Builder<GardenTile> builder = Stream.builder();
                    if (x > 0) builder.add(array[x - 1][y]);
                    if (y > 0) builder.add(array[x][y - 1]);
                    if (x < sizeX - 1) builder.add(array[x + 1][y]);
                    if (y < sizeY - 1) builder.add(array[x][y + 1]);

                    List<GardenTile> neighbours = builder.build()
                            .filter(GardenTile::isWalkable)
                            .toList();
                    gardenTile.setNeighbours(neighbours);
                });
    }

    long walkSteps(int steps) {
        Set<GardenTile> reachedGardenTiles = new HashSet<>();
        reachedGardenTiles.add(start);
        for (int i = 0; i < steps; i++) {
            reachedGardenTiles = reachedGardenTiles.stream()
                    .map(GardenTile::getNeighbours)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toSet());
        }
        return reachedGardenTiles.size();
    }
}
