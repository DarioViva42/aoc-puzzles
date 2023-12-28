package tk.vivas.adventofcode.year2023.day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static tk.vivas.adventofcode.year2023.day23.TrailTile.*;

class MapRightTraverser {

    private final TrailTile[][] map;
    private final int sizeY;
    private final int start;

    MapRightTraverser(TrailTile[][] map) {
        this.map = map;

        sizeY = map.length;
        start = Arrays.stream(map).map(e -> e[0]).toList().indexOf(SLOPE_SOUTH);
    }

    List<PathSection> sections() {
        List<PathSection> edgeSections = new ArrayList<>();
        PathSection pathSection = walkPaths(start, 0);
        edgeSections.add(pathSection);
        while (!pathSection.getNextSections().isEmpty()) {
            pathSection = pathSection.getNextSections().getFirst();
            edgeSections.add(pathSection);
        }
        return edgeSections;
    }

    private PathSection walkPaths(int x, int y) {
        return switch (map[x][y]) {
            case SLOPE_NORTH -> walkPathNorth(0, x, y, withStart(x, y + 1));
            case SLOPE_EAST -> walkPathEast(0, x, y, withStart(x - 1, y));
            case SLOPE_SOUTH -> walkPathSouth(0, x, y, withStart(x, y - 1));
            case SLOPE_WEST -> walkPathWest(0, x, y, withStart(x + 1, y));
            case FOREST, PATH -> throw new IllegalStateException("expected to walk on slope");
        };
    }

    private List<Point> withStart(int x, int y) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(x, y));
        return points;
    }

    private PathSection walkPathNorth(int count, int x, int y, List<Point> points) {
        points.add(new Point(x, y));
        y--;
        count++;
        return switch (map[x][y]) {
            case PATH -> {
                TrailTile northTile = map[x][y - 1];
                if (SLOPE_NORTH == northTile || PATH == northTile) {
                    yield walkPathNorth(count, x, y, points);
                }
                TrailTile eastTile = map[x + 1][y];
                if (SLOPE_EAST == eastTile || PATH == eastTile) {
                    yield walkPathEast(count, x, y, points);
                }
                TrailTile westTile = map[x - 1][y];
                if (SLOPE_WEST == westTile || PATH == westTile) {
                    yield walkPathWest(count, x, y, points);
                }
                throw new IllegalStateException("lost in forest");
            }
            case SLOPE_NORTH -> {
                points.add(new Point(x, y));
                y--;
                count += 2;
                List<PathSection> nextSections = new ArrayList<>();
                nextSections.add(SLOPE_EAST == map[x + 1][y] ? walkPaths(x + 1, y) : walkPaths(x, y - 1));
                points.add(new Point(x, y));
                yield new PathSection(points, count, nextSections);
            }
            case FOREST, SLOPE_EAST, SLOPE_SOUTH, SLOPE_WEST ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }

    private PathSection walkPathEast(int count, int x, int y, List<Point> points) {
        points.add(new Point(x, y));
        x++;
        count++;
        return switch (map[x][y]) {
            case PATH -> {
                TrailTile northTile = map[x][y - 1];
                if (SLOPE_NORTH == northTile || PATH == northTile) {
                    yield walkPathNorth(count, x, y, points);
                }
                TrailTile eastTile = map[x + 1][y];
                if (SLOPE_EAST == eastTile || PATH == eastTile) {
                    yield walkPathEast(count, x, y, points);
                }
                TrailTile southTile = map[x][y + 1];
                if (SLOPE_SOUTH == southTile || PATH == southTile) {
                    yield walkPathSouth(count, x, y, points);
                }
                throw new IllegalStateException("lost in forest");
            }
            case SLOPE_EAST -> {
                points.add(new Point(x, y));
                x++;
                count += 2;
                List<PathSection> nextSections = new ArrayList<>();
                nextSections.add(SLOPE_SOUTH == map[x][y + 1] ? walkPaths(x, y + 1) : walkPaths(x + 1, y));
                points.add(new Point(x, y));
                yield new PathSection(points, count, nextSections);
            }
            case FOREST, SLOPE_NORTH, SLOPE_SOUTH, SLOPE_WEST ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }

    private PathSection walkPathSouth(int count, int x, int y, List<Point> points) {
        points.add(new Point(x, y));
        y++;
        count++;
        if (y == sizeY - 1) {
            points.add(new Point(x, y));
            points.add(new Point(x, y + 1));
            return new PathSection(points, count, List.of());
        }
        return switch (map[x][y]) {
            case PATH -> {
                TrailTile eastTile = map[x + 1][y];
                if (SLOPE_EAST == eastTile || PATH == eastTile) {
                    yield walkPathEast(count, x, y, points);
                }
                TrailTile southTile = map[x][y + 1];
                if (SLOPE_SOUTH == southTile || PATH == southTile) {
                    yield walkPathSouth(count, x, y, points);
                }
                TrailTile westTile = map[x - 1][y];
                if (SLOPE_WEST == westTile || PATH == westTile) {
                    yield walkPathWest(count, x, y, points);
                }
                throw new IllegalStateException("lost in forest");
            }
            case SLOPE_SOUTH -> {
                points.add(new Point(x, y));
                y++;
                count += 2;
                List<PathSection> nextSections = new ArrayList<>();
                nextSections.add(SLOPE_WEST == map[x - 1][y] ? walkPaths(x - 1, y) : walkPaths(x, y + 1));
                points.add(new Point(x, y));
                yield new PathSection(points, count, nextSections);
            }
            case FOREST, SLOPE_NORTH, SLOPE_EAST, SLOPE_WEST ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }

    private PathSection walkPathWest(int count, int x, int y, List<Point> points) {
        points.add(new Point(x, y));
        x--;
        count++;
        return switch (map[x][y]) {
            case PATH -> {
                TrailTile northTile = map[x][y - 1];
                if (SLOPE_NORTH == northTile || PATH == northTile) {
                    yield walkPathNorth(count, x, y, points);
                }
                TrailTile southTile = map[x][y + 1];
                if (SLOPE_SOUTH == southTile || PATH == southTile) {
                    yield walkPathSouth(count, x, y, points);
                }
                TrailTile westTile = map[x - 1][y];
                if (SLOPE_WEST == westTile || PATH == westTile) {
                    yield walkPathWest(count, x, y, points);
                }
                throw new IllegalStateException("lost in forest");
            }
            case SLOPE_WEST -> {
                points.add(new Point(x, y));
                x--;
                count += 2;
                List<PathSection> nextSections = new ArrayList<>();
                nextSections.add(SLOPE_NORTH == map[x][y - 1] ? walkPaths(x, y - 1) : walkPaths(x - 1, y));
                points.add(new Point(x, y));
                yield new PathSection(points, count, nextSections);
            }
            case FOREST, SLOPE_NORTH, SLOPE_EAST, SLOPE_SOUTH ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }
}
