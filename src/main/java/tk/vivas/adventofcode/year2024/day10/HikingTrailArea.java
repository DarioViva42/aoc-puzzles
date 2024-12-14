package tk.vivas.adventofcode.year2024.day10;

import tk.vivas.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class HikingTrailArea {

    private final int height;
    private final int width;
    private final TrailTile[][] map;
    private final List<TrailTile> trailTiles;

    HikingTrailArea(String input) {
        map = input.lines()
                .map(line -> line.chars()
                        .mapToObj(TrailTile::new)
                        .toArray(TrailTile[]::new))
                .toArray(TrailTile[][]::new);

        height = map.length;
        width = map[0].length;

        trailTiles = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                trailTiles.add(getPreparedTrailTile(y, x));
            }
        }
    }

    private TrailTile getPreparedTrailTile(int y, int x) {
        TrailTile trailTile = map[y][x];
        trailTile.setPosition(new Position(x, y));

        if (x > 0) trailTile.addNeighbour(map[y][x - 1]);
        if (y > 0) trailTile.addNeighbour(map[y - 1][x]);
        if (x < width - 1) trailTile.addNeighbour(map[y][x + 1]);
        if (y < height - 1) trailTile.addNeighbour(map[y + 1][x]);

        return trailTile;
    }

    int trailHeadsScore() {
        return trailTiles.stream()
                .filter(TrailTile::isTrailHead)
                .map(TrailTile::getReachableSummits)
                .mapToInt(Set::size)
                .sum();
    }

    int trailHeadsRating() {
        return trailTiles.stream()
                .filter(TrailTile::isTrailHead)
                .mapToInt(TrailTile::getRating)
                .sum();
    }
}
