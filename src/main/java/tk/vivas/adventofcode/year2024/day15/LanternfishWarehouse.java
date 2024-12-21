package tk.vivas.adventofcode.year2024.day15;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

class LanternfishWarehouse {

    private final List<WarehouseTile> tiles;
    private WarehouseTile robot;
    private final List<Direction> directionList;

    LanternfishWarehouse(String input) {
        String[] split = input.split("\n\n");

        directionList = split[1]
                .replace("\n", "")
                .chars()
                .mapToObj(Direction::from)
                .toList();

        WarehouseTile[][] map = split[0].lines()
                .map(line -> line.chars()
                        .mapToObj(WarehouseTile::new)
                        .toArray(WarehouseTile[]::new))
                .toArray(WarehouseTile[][]::new);
        int height = map.length;
        int width = map[0].length;

        tiles = IntStream.range(1, height - 1)
                .mapToObj(y -> IntStream.range(1, width - 1)
                        .mapToObj(x -> WarehouseTile.initializeTile(map, x, y)))
                .flatMap(Function.identity())
                .toList();

        robot = tiles.stream()
                .filter(WarehouseTile::isRobot)
                .findFirst().orElseThrow();
    }

    long coordinatesSum() {
        directionList.forEach(direction -> robot = robot.move(direction));

        return tiles.stream()
                .filter(WarehouseTile::isBox)
                .mapToLong(WarehouseTile::gpsCoordinate)
                .sum();
    }
}
