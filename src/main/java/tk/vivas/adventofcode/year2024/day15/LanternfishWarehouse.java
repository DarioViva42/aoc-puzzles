package tk.vivas.adventofcode.year2024.day15;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

class LanternfishWarehouse {

    private final List<Direction> directionList;
    private final List<WarehouseTile> tiles;
    private final List<WarehouseTile> largeTiles;

    private WarehouseTile largeRobot;
    private WarehouseTile robot;

    LanternfishWarehouse(String input) {
        String[] split = input.split("\n\n");

        directionList = createDirectionList(split[1]);
        WarehouseTile[][] map = createMap(split[0]);
        WarehouseTile[][] largeMap = createLargeMap(map);

        tiles = createTiles(map);
        robot = findRobot(tiles);

        largeTiles = createTiles(largeMap);
        largeRobot = findRobot(largeTiles);
    }

    private static List<Direction> createDirectionList(String input) {
        return input
                .replace("\n", "")
                .chars()
                .mapToObj(Direction::from)
                .toList();
    }

    private static WarehouseTile[][] createMap(String input) {
        return input.lines()
                .map(line -> line.chars()
                        .mapToObj(WarehouseTile::of)
                        .toArray(WarehouseTile[]::new))
                .toArray(WarehouseTile[][]::new);
    }

    private WarehouseTile[][] createLargeMap(WarehouseTile[][] map) {
        return Arrays.stream(map)
                .map(line -> Arrays.stream(line)
                        .flatMap(WarehouseTile::enlarge)
                        .toArray(WarehouseTile[]::new))
                .toArray(WarehouseTile[][]::new);
    }

    private WarehouseTile findRobot(List<WarehouseTile> tiles) {
        return tiles.stream()
                .filter(WarehouseTile::isRobot)
                .findFirst().orElseThrow();
    }

    private List<WarehouseTile> createTiles(WarehouseTile[][] map) {
        int height = map.length;
        int width = map[0].length;

        return IntStream.range(1, height - 1)
                .mapToObj(y -> IntStream.range(1, width - 1)
                        .mapToObj(x -> WarehouseTile.initializeTile(map, x, y)))
                .flatMap(Function.identity())
                .toList();
    }

    long coordinatesSum() {
        directionList.forEach(direction -> robot = robot.move(direction));

        return tiles.stream()
                .filter(WarehouseTile::isBox)
                .mapToLong(WarehouseTile::gpsCoordinate)
                .sum();
    }

    long coordinatesSumInLargeWarehouse() {
        directionList.forEach(direction -> largeRobot = largeRobot.largeMove(direction));

        return largeTiles.stream()
                .filter(WarehouseTile::isLeftPartOfBox)
                .mapToLong(WarehouseTile::gpsCoordinate)
                .sum();
    }
}
