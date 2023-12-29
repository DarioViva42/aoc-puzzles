package tk.vivas.adventofcode.year2023.day23;

import tk.vivas.ConsoleColors;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.Predicate.not;
import static tk.vivas.adventofcode.year2023.day23.TrailTile.*;

class HikingTrailMap {

    private final TrailTile[][] map;
    private final String[][] idMap;
    private final int start;
    private final int end;
    private final int sizeX;
    private final int sizeY;
    private final PathSection directionalStartSection;
    private final Map<String, PathSection> identifiedSections;
    private List<String> allPaths;

    HikingTrailMap(String input) {
        List<String> lines = input.lines().toList();
        String firstLine = lines.getFirst();
        start = firstLine.indexOf('.');
        end = lines.getLast().indexOf('.');
        sizeX = firstLine.length();
        sizeY = lines.size();
        map = createTiledMap(lines);

        directionalStartSection = new MapToGraphConverter(map).toGraph();
        identifiedSections = createBidirectionalSections();
        cleanEdges();

        idMap = mapIds(identifiedSections.values());
    }

    private void cleanEdges() {
        Map<Point, PathSection> firstPointMap = identifiedSections.values().stream()
                .collect(Collectors.toMap(path -> path.getPoints().get(1), Function.identity()));
        List<PathSection> leftSections = new MapLeftTraverser(map)
                .sections().stream()
                .map(PathSection::getPoints)
                .map(points -> points.get(1))
                .map(firstPointMap::get)
                .toList();
        for (int i = 0; i < leftSections.size(); i++) {
            leftSections.get(i).setEdgeNumber(i);
        }
        List<PathSection> rightSections = new MapRightTraverser(map)
                .sections().stream()
                .map(PathSection::getPoints)
                .map(points -> points.get(1))
                .map(firstPointMap::get)
                .toList();
        for (int i = 0; i < rightSections.size(); i++) {
            rightSections.get(i).setEdgeNumber(i);
        }
        identifiedSections.values().stream()
                .filter(path -> path.getEdgeNumber() != 0)
                .forEach(PathSection::cleanEdges);
        PathSection last = leftSections.getLast();
        identifiedSections.values().stream()
                .filter(path -> path.getNextSections().contains(last))
                .forEach(path -> {
                    path.clearNextSections();
                    path.addSection(last);
                });
    }

    private TrailTile[][] createTiledMap(List<String> lines) {
        final TrailTile[][] map;
        map = new TrailTile[sizeX][sizeY];
        for (int y = 0; y < sizeY; y++) {
            String line = lines.get(y);
            for (int x = 0; x < sizeX; x++) {
                char c = line.charAt(x);
                map[x][y] = TrailTile.of(c);
            }
        }
        map[start][0] = SLOPE_SOUTH;
        map[end][sizeY - 1] = SLOPE_SOUTH;
        return map;
    }

    private Map<String, PathSection> createBidirectionalSections() {
        PathSection startSection = directionalStartSection.deepCopy();
        List<PathSection> allSections = startSection.allSections();
        fixSections(allSections);
        makeBidirectional(allSections);
        return identifySections(allSections);
    }

    int findLongestPath() {
        return directionalStartSection.getLongestPathLength();
    }

    int findLongestPathIgnoringSlopes() {
        allPaths = identifiedSections.get("A1")
                .getAllPaths(sizeY, new HashSet<>());
        return allPaths.stream()
                .mapToInt(this::routeToLength)
                .max().orElse(0);
    }

    private int routeToLength(String routeString) {
        return IntStream.iterate(0, i -> i < routeString.length(), i -> i + 2)
                .mapToObj(i -> routeString.substring(i, i + 2))
                .map(identifiedSections::get)
                .mapToInt(PathSection::length)
                .sum();
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

    private String[][] mapIds(Collection<PathSection> allSections) {
        String[][] idMap = new String[sizeX][sizeY];
        allSections.forEach(section -> {
            String id = section.getId();
            List<Point> points = section.getPoints();
            points.subList(1, points.size() - 1)
                    .forEach(point -> idMap[point.x()][point.y()] = id);
        });
        return idMap;
    }

    private Map<String, PathSection> identifySections(List<PathSection> allSections) {
        return IntStream.range(0, allSections.size())
                .mapToObj(i -> {
                    PathSection pathSection = allSections.get(i);
                    String id = (char) (i % 26 + 'A') + String.valueOf(((i / 26) + 1));
                    pathSection.setId(id);
                    return pathSection;
                })
                .collect(Collectors.toMap(PathSection::getId, Function.identity()));
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

    private String getShortestRouteString() {
        if (allPaths == null) {
            return "";
        }
        return allPaths.stream()
                .min(Comparator.comparing(this::routeToLength))
                .orElseThrow();
    }

    private String getLongestRouteString() {
        if (allPaths == null) {
            return "";
        }
        return allPaths.stream()
                .max(Comparator.comparing(this::routeToLength))
                .orElseThrow();
    }

    private String getMaxJunctionsString() {
        if (allPaths == null) {
            return "";
        }

        int maxJunctions = allPaths.stream().mapToInt(String::length).max().orElseThrow();
        return allPaths.stream()
                .filter(e -> e.length() == maxJunctions)
                .min(Comparator.comparing(this::routeToLength))
                .orElseThrow();
    }

    public void printAllSections() {
        identifiedSections.values().stream()
                .sorted(Comparator.comparing(PathSection::getId))
                .forEach(System.out::println);
        System.out.println();
        identifiedSections.values().stream()
                .sorted(Comparator.comparing(PathSection::length))
                .forEach(System.out::println);
    }

    void printAllPaths() {
        if (allPaths == null) {
            System.out.println("Not yet searched");
        }

        System.out.println("Paths with most amount of junctions:");
        int maxJunctions = allPaths.stream().mapToInt(String::length).max().orElseThrow();
        allPaths.stream()
                .filter(e -> e.length() == maxJunctions)
                .collect(Collectors.toMap(this::toPrettyRouteString, this::routeToLength))
                .entrySet().stream()
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .map(e -> "%s=%s".formatted(e.getValue(), e.getKey()))
                .forEach(System.out::println);

        System.out.println();

        System.out.println("Paths with least amount of junctions:");
        int minJunctions = allPaths.stream().mapToInt(String::length).min().orElseThrow();
        allPaths.stream()
                .filter(e -> e.length() == minJunctions)
                .collect(Collectors.toMap(this::toPrettyRouteString, this::routeToLength))
                .entrySet().stream()
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .map(e -> "%s=%s".formatted(e.getValue(), e.getKey()))
                .forEach(System.out::println);

        System.out.println();

        System.out.println("longest paths:");
        int maxLength = allPaths.stream().mapToInt(this::routeToLength).max().orElseThrow();
        allPaths.stream()
                .collect(Collectors.toMap(this::toPrettyRouteString, this::routeToLength))
                .entrySet().stream()
                .filter(e -> e.getValue().equals(maxLength))
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .map(e -> "%s=%s".formatted(e.getValue(), e.getKey()))
                .forEach(System.out::println);

        System.out.println();

        System.out.println("shortest paths:");
        int minLength = allPaths.stream().mapToInt(this::routeToLength).min().orElseThrow();
        allPaths.stream()
                .collect(Collectors.toMap(this::toPrettyRouteString, this::routeToLength))
                .entrySet().stream()
                .filter(e -> e.getValue().equals(minLength))
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .map(e -> "%s=%s".formatted(e.getValue(), e.getKey()))
                .forEach(System.out::println);

        System.out.println();

        System.out.println("Paths of all possible lengths sorted by amount of junctions");
        allPaths.stream()
                .collect(Collectors.toMap(this::routeToLength, this::toPrettyRouteString, (a, b) -> a))
                .entrySet().stream()
                .sorted(Entry.<Integer, String>comparingByValue(
                                Comparator.comparing(String::length, Comparator.reverseOrder()))
                        .thenComparing(comparingByKey(Comparator.reverseOrder())))
                .forEach(System.out::println);

        System.out.println();

        System.out.println("Paths of all possible lengths sorted by length");
        allPaths.stream()
                .collect(Collectors.toMap(this::routeToLength, this::toPrettyRouteString, (a, b) -> a))
                .entrySet().stream()
                .sorted(comparingByKey(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    private String toPrettyRouteString(String routString) {
        return IntStream.iterate(0, i -> i < routString.length(), i -> i + 2)
                .mapToObj(i -> routString.substring(i, i + 2))
                .collect(Collectors.joining(">"));
    }

    private Set<Point> toPoints(String routeString) {
        return IntStream.iterate(0, i -> i < routeString.length(), i -> i + 2)
                .mapToObj(i -> routeString.substring(i, i + 2))
                .map(identifiedSections::get)
                .map(PathSection::getPoints)
                .flatMap(Collection::stream)
                .filter(point -> point.y() < sizeY)
                .filter(point -> point.y() >= 0)
                .collect(Collectors.toUnmodifiableSet());
    }

    private boolean[][] pointsToMask(Set<Point> points) {
        boolean[][] routeMask = new boolean[sizeX][sizeY];
        points.forEach(point -> routeMask[point.x()][point.y()] = true);
        return routeMask;
    }

    public void printShortest() {
        print(getShortestRouteString(), ConsoleColors.RED_BACKGROUND);
    }

    public void printLongest() {
        print(getLongestRouteString(), ConsoleColors.GREEN_BACKGROUND);
    }

    public void printMaxJunctions() {
        print(getMaxJunctionsString(), ConsoleColors.BLUE_BACKGROUND);
    }

    public void print(String idSequence, String color) {
        boolean[][] routeMask = pointsToMask(toPoints(idSequence));
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
                if (routeMask[x][y]) {
                    sb.append(color);
                }
                String id = idMap[x][y];
                if (trailTile == PATH && id != null) {
                    sb.append(id);
                } else {
                    sb.append(trailTile);
                }
                sb.append(ConsoleColors.RESET);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
