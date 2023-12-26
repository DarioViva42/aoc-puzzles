package tk.vivas.adventofcode.year2023.day23;

import tk.vivas.ConsoleColors;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;
import static tk.vivas.adventofcode.year2023.day23.TrailTile.*;

class HikingTrailMap {

    private final TrailTile[][] map;
    private final String[][] idMap;
    private final int start;
    private final int end;
    private final int sizeX;
    private final int sizeY;
    private PathSection startSectionCached;

    HikingTrailMap(String input) {
        List<String> lines = input.lines().toList();
        String firstLine = lines.getFirst();
        start = firstLine.indexOf('.');
        end = lines.getLast().indexOf('.');
        sizeX = firstLine.length();
        sizeY = lines.size();
        map = new TrailTile[sizeX][sizeY];
        idMap = new String[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            String line = lines.get(y);
            for (int x = 0; x < sizeX; x++) {
                char c = line.charAt(x);
                map[x][y] = TrailTile.of(c);
            }
        }
    }

    int findLongestPath() {
        return createPathSections().getLongestPathLength();
    }

    public int findLongestPathIgnoringSlopes() {
        PathSection realStartSection = createPathSections().deepCopy();
        List<PathSection> allSections = realStartSection.allSections();
        identifySections(allSections);
        mapIds(allSections);
        fixSections(allSections);
        makeBidirectional(allSections);

        return realStartSection.getLongestPathLength(sizeY, new HashSet<>());
    }

    private void fixSections(List<PathSection> allSections) {
        allSections.forEach(section -> {
            List<PathSection> nextSections = section.getNextSections();
            for (int i = 0; i < nextSections.size(); i++) {
                PathSection nextSection = nextSections.get(i);
                int index = allSections.indexOf(nextSection);
                if (index != -1) {
                    PathSection realSection = allSections.get(index);
                    nextSections.set(i, realSection);
                }
            }
        });
    }

    private void mapIds(List<PathSection> allSections) {
        allSections.forEach(section -> {
            String id = section.getId();
            section.getPoints()
                    .forEach(point -> idMap[point.x()][point.y()] = id);
        });
    }

    private void identifySections(List<PathSection> allSections) {
        IntStream.range(0, allSections.size())
                .forEach(i -> {
                    PathSection pathSection = allSections.get(i);
                    String id = (char) (i % 26 + 'A') + String.valueOf(((i / 26) + 1));
                    pathSection.setId(id);
                });
    }

    private void makeBidirectional(List<PathSection> allSections) {
        for (PathSection section : allSections) {
            allSections.stream()
                    .filter(not(section::equals))
                    .filter(not(s -> s.contains(section)))
                    .filter(section::isTouching)
                    .forEach(s -> s.addSection(section));
        }
    }

    private PathSection createPathSections() {
        if (startSectionCached != null) {
            return startSectionCached;
        }
        map[start][0] = SLOPE_SOUTH;
        map[end][sizeY - 1] = SLOPE_SOUTH;
        startSectionCached = walkPaths(start, 0);
        return startSectionCached;
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
                count+=2;
                List<PathSection> nextSections = new ArrayList<>();
                if (SLOPE_NORTH == map[x][y - 1]) {
                    nextSections.add(walkPaths(x, y - 1));
                }
                if (SLOPE_EAST == map[x + 1][y]) {
                    nextSections.add(walkPaths(x + 1, y));
                }
                if (SLOPE_WEST == map[x - 1][y]) {
                    nextSections.add(walkPaths(x - 1, y));
                }
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
                count+=2;
                List<PathSection> nextSections = new ArrayList<>();
                if (SLOPE_NORTH == map[x][y - 1]) {
                    nextSections.add(walkPaths(x, y - 1));
                }
                if (SLOPE_EAST == map[x + 1][y]) {
                    nextSections.add(walkPaths(x + 1, y));
                }
                if (SLOPE_SOUTH == map[x][y + 1]) {
                    nextSections.add(walkPaths(x, y + 1));
                }
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
                count+=2;
                List<PathSection> nextSections = new ArrayList<>();
                if (SLOPE_EAST == map[x + 1][y]) {
                    nextSections.add(walkPaths(x + 1, y));
                }
                if (SLOPE_SOUTH == map[x][y + 1]) {
                    nextSections.add(walkPaths(x, y + 1));
                }
                if (SLOPE_WEST == map[x - 1][y]) {
                    nextSections.add(walkPaths(x - 1, y));
                }
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
                count+=2;
                List<PathSection> nextSections = new ArrayList<>();
                if (SLOPE_NORTH == map[x][y - 1]) {
                    nextSections.add(walkPaths(x, y - 1));
                }
                if (SLOPE_SOUTH == map[x][y + 1]) {
                    nextSections.add(walkPaths(x, y + 1));
                }
                if (SLOPE_WEST == map[x - 1][y]) {
                    nextSections.add(walkPaths(x - 1, y));
                }
                points.add(new Point(x, y));
                yield new PathSection(points, count, nextSections);
            }
            case FOREST, SLOPE_NORTH, SLOPE_EAST, SLOPE_SOUTH ->
                    throw new IllegalStateException("walking to forbidden tile");
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int x = 0; x < sizeX; x++) {
            if (x % 2 == 0) {
                sb.append(ConsoleColors.BLACK_BACKGROUND_BRIGHT);
            }
            sb.append("%02x".formatted(x));
            sb.append(ConsoleColors.RESET);
        }
        sb.append("\n");
        for (int y = 0; y < sizeY; y++) {
            sb.append("%02x".formatted(y));
            for (int x = 0; x < sizeX; x++) {
                TrailTile trailTile = map[x][y];
                String id = idMap[x][y];
                if (trailTile == PATH && id != null) {
                    sb.append(id);
                } else {
                    sb.append(trailTile);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
