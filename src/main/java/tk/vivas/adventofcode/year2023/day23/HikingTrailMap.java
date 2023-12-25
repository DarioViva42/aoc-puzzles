package tk.vivas.adventofcode.year2023.day23;

import java.util.*;

import static tk.vivas.adventofcode.year2023.day23.TrailTile.*;

class HikingTrailMap {

    private final TrailTile[][] map;
    private final int start;
    private final int sizeX;
    private final int sizeY;

    HikingTrailMap(String input) {
        List<String> lines = input.lines().toList();
        String firstLine = lines.getFirst();
        start = firstLine.indexOf('.');
        sizeX = firstLine.length();
        sizeY = lines.size();
        map = new TrailTile[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            String line = lines.get(y);
            for (int x = 0; x < sizeX; x++) {
                char c = line.charAt(x);
                map[x][y] = TrailTile.of(c);
            }
        }
    }

    long findLongestPath() {
        map[start][0] = SLOPE_SOUTH;
        PathSection startSection = walkPaths(start, 0);
        map[start][0] = PATH;
        return startSection.getLongestPathLength();
    }

    private PathSection walkPaths(int x, int y) {
        return switch (map[x][y]) {
            case SLOPE_NORTH -> walkPathNorth(0, x, y);
            case SLOPE_EAST -> walkPathEast(0, x, y);
            case SLOPE_SOUTH -> walkPathSouth(0, x, y);
            case SLOPE_WEST -> walkPathWest(0, x, y);
            case FOREST, PATH -> throw new IllegalStateException("expected to walk on slope");
        };
    }
    
    private PathSection walkPathNorth(int count, int x, int y) {
        y--;
        count++;
        return switch (map[x][y]) {
            case PATH -> {
                TrailTile northTile = map[x][y - 1];
                if (SLOPE_NORTH == northTile || PATH == northTile) {
                    yield walkPathNorth(count, x, y);
                }
                TrailTile eastTile = map[x + 1][y];
                if (SLOPE_EAST == eastTile || PATH == eastTile) {
                    yield walkPathEast(count, x, y);
                }
                TrailTile westTile = map[x - 1][y];
                if (SLOPE_WEST == westTile || PATH == westTile) {
                    yield walkPathWest(count, x, y);
                }
                throw new IllegalStateException("lost in forest");
            }
            case SLOPE_NORTH -> {
                y--;
                count+=2;
                Set<PathSection> nextSections = new HashSet<>();
                if (SLOPE_NORTH == map[x][y - 1]) {
                    nextSections.add(walkPaths(x, y - 1));
                }
                if (SLOPE_EAST == map[x + 1][y]) {
                    nextSections.add(walkPaths(x + 1, y));
                }
                if (SLOPE_WEST == map[x - 1][y]) {
                    nextSections.add(walkPaths(x - 1, y));
                }
                yield new PathSection(count, nextSections);
            }
            case FOREST, SLOPE_EAST, SLOPE_SOUTH, SLOPE_WEST ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }

    private PathSection walkPathEast(int count, int x, int y) {
        x++;
        count++;
        return switch (map[x][y]) {
            case PATH -> {
                TrailTile northTile = map[x][y - 1];
                if (SLOPE_NORTH == northTile || PATH == northTile) {
                    yield walkPathNorth(count, x, y);
                }
                TrailTile eastTile = map[x + 1][y];
                if (SLOPE_EAST == eastTile || PATH == eastTile) {
                    yield walkPathEast(count, x, y);
                }
                TrailTile southTile = map[x][y + 1];
                if (SLOPE_SOUTH == southTile || PATH == southTile) {
                    yield walkPathSouth(count, x, y);
                }
                throw new IllegalStateException("lost in forest");
            }
            case SLOPE_EAST -> {
                x++;
                count+=2;
                Set<PathSection> nextSections = new HashSet<>();
                if (SLOPE_NORTH == map[x][y - 1]) {
                    nextSections.add(walkPaths(x, y - 1));
                }
                if (SLOPE_EAST == map[x + 1][y]) {
                    nextSections.add(walkPaths(x + 1, y));
                }
                if (SLOPE_SOUTH == map[x][y + 1]) {
                    nextSections.add(walkPaths(x, y + 1));
                }
                yield new PathSection(count, nextSections);
            }
            case FOREST, SLOPE_NORTH, SLOPE_SOUTH, SLOPE_WEST ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }

    private PathSection walkPathSouth(int count, int x, int y) {
        y++;
        count++;
        if (y == sizeY - 1) {
            return new PathSection(count, Set.of());
        }
        return switch (map[x][y]) {
            case PATH -> {
                TrailTile eastTile = map[x + 1][y];
                if (SLOPE_EAST == eastTile || PATH == eastTile) {
                    yield walkPathEast(count, x, y);
                }
                TrailTile southTile = map[x][y + 1];
                if (SLOPE_SOUTH == southTile || PATH == southTile) {
                    yield walkPathSouth(count, x, y);
                }
                TrailTile westTile = map[x - 1][y];
                if (SLOPE_WEST == westTile || PATH == westTile) {
                    yield walkPathWest(count, x, y);
                }
                throw new IllegalStateException("lost in forest");
            }
            case SLOPE_SOUTH -> {
                y++;
                count+=2;
                Set<PathSection> nextSections = new HashSet<>();
                if (SLOPE_EAST == map[x + 1][y]) {
                    nextSections.add(walkPaths(x + 1, y));
                }
                if (SLOPE_SOUTH == map[x][y + 1]) {
                    nextSections.add(walkPaths(x, y + 1));
                }
                if (SLOPE_WEST == map[x - 1][y]) {
                    nextSections.add(walkPaths(x - 1, y));
                }
                yield new PathSection(count, nextSections);
            }
            case FOREST, SLOPE_NORTH, SLOPE_EAST, SLOPE_WEST ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }

    private PathSection walkPathWest(int count, int x, int y) {
        x--;
        count++;
        return switch (map[x][y]) {
            case PATH -> {
                TrailTile northTile = map[x][y - 1];
                if (SLOPE_NORTH == northTile || PATH == northTile) {
                    yield walkPathNorth(count, x, y);
                }
                TrailTile southTile = map[x][y + 1];
                if (SLOPE_SOUTH == southTile || PATH == southTile) {
                    yield walkPathSouth(count, x, y);
                }
                TrailTile westTile = map[x - 1][y];
                if (SLOPE_WEST == westTile || PATH == westTile) {
                    yield walkPathWest(count, x, y);
                }
                throw new IllegalStateException("lost in forest");
            }
            case SLOPE_WEST -> {
                x--;
                count+=2;
                Set<PathSection> nextSections = new HashSet<>();
                if (SLOPE_NORTH == map[x][y - 1]) {
                    nextSections.add(walkPaths(x, y - 1));
                }
                if (SLOPE_SOUTH == map[x][y + 1]) {
                    nextSections.add(walkPaths(x, y + 1));
                }
                if (SLOPE_WEST == map[x - 1][y]) {
                    nextSections.add(walkPaths(x - 1, y));
                }
                yield new PathSection(count, nextSections);
            }
            case FOREST, SLOPE_NORTH, SLOPE_EAST, SLOPE_SOUTH ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                sb.append(map[x][y].getDisplayChar());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
