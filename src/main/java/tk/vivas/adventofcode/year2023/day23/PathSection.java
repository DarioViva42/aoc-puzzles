package tk.vivas.adventofcode.year2023.day23;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

class PathSection {
    private final List<Point> points;
    private final int length;
    private final List<PathSection> nextPaths;
    private String id;
    private int edgeNumber;

    PathSection(List<Point> points, int length, List<PathSection> nextPaths) {
        this.points = points;
        this.length = length;
        this.nextPaths = nextPaths;

        edgeNumber = -1;
    }

    void setId(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    public int length() {
        return length;
    }

    List<Point> getPoints() {
        return points;
    }

    List<PathSection> getNextSections() {
        return nextPaths;
    }

    void setEdgeNumber(int edgeNumber) {
        this.edgeNumber = edgeNumber;
    }

    int getEdgeNumber() {
        return edgeNumber;
    }

    public void cleanEdges() {
            nextPaths.stream()
                    .filter(path -> path.edgeNumber != -1)
                    .min(Comparator.comparing(PathSection::getEdgeNumber))
                    .ifPresent(nextPaths::remove);
    }

    int getLongestPathLength() {
        int longestContinuation = nextPaths.stream()
                .mapToInt(PathSection::getLongestPathLength)
                .max().orElse(0);
        return length + longestContinuation;
    }

    List<String> getAllPaths(int endY, Set<PathSection> visitedSections) {
        return nextPaths.stream()
                .filter(not(visitedSections::contains))
                .map(pathSection -> {
                    if (endY == pathSection.points.getLast().y()) {
                        return List.of(pathSection.id);
                    }
                    Set<PathSection> visitedSectionsCopied = new HashSet<>(visitedSections);
                    visitedSectionsCopied.addAll(nextPaths);
                    return pathSection.getAllPaths(endY, visitedSectionsCopied);
                })
                .flatMap(Collection::stream)
                .map(idChain -> this.id + idChain)
                .toList();
    }

    List<PathSection> allSections() {
        return allSections(new HashSet<>()).stream()
                .sorted(Comparator.comparing(e -> e.points.getFirst().y()))
                .toList();
    }

    private Set<PathSection> allSections(HashSet<PathSection> visitedSections) {
        visitedSections.add(this);
        for (PathSection nextPath : nextPaths) {
            nextPath.allSections(visitedSections);
        }
        return visitedSections;
    }

    public void clearNextSections() {
        nextPaths.clear();
    }

    void addSection(PathSection section) {
        nextPaths.add(section);
    }

    PathSection deepCopy() {
        List<PathSection> copiedPaths = nextPaths.stream()
                .map(PathSection::deepCopy)
                .collect(Collectors.toList());
        return new PathSection(points, length, copiedPaths);
    }

    boolean contains(PathSection section) {
        return nextPaths.contains(section);
    }

    public boolean isTouching(PathSection other) {
        return Objects.equals(this.points.getFirst(), other.points.getFirst())
                || Objects.equals(this.points.getLast(), other.points.getLast())
                || Objects.equals(this.points.getFirst(), other.points.getLast())
                || Objects.equals(this.points.getLast(), other.points.getFirst());
    }

    @Override
    public String toString() {
        String nextPathString = nextPaths.stream()
                .map(path -> path.id)
                .collect(Collectors.joining(", "));
        return "%s) %s -%2d-> %s : %s".formatted(id, points.getFirst(), length, points.getLast(), nextPathString);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathSection that = (PathSection) o;
        return Objects.equals(points.get(1), that.points.get(1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(points.get(1));
    }
}
