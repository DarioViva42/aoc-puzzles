package tk.vivas.adventofcode.year2022.day12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

class HillArea {
    private final List<List<HeightPoint>> heightPoints;
    private final HeightPoint start;
    private final HeightPoint end;

    public HillArea(String input) {
        heightPoints = input.lines()
                .map(String::chars)
                .map(e -> e
                        .mapToObj((c -> (char) c))
                        .map(HeightPoint::parse))
                .map(Stream::toList)
                .toList();

        start = findStart();
        end = findEnd();

        setUpNeighbours();
    }

    private HeightPoint findStart() {
        return heightPoints.stream()
                .flatMap(Collection::stream)
                .filter(HeightPoint::isStart)
                .findFirst().orElseThrow();
    }

    private HeightPoint findEnd() {
        return heightPoints.stream()
                .flatMap(Collection::stream)
                .filter(HeightPoint::isEnd)
                .findFirst().orElseThrow();
    }

    private void setUpNeighbours() {
        for (int y = 0; y < heightPoints.size(); y++) {
            for (int x = 0; x < heightPoints.get(y).size(); x++) {
                HeightPoint memoryPoint = heightPoints.get(y).get(x);
                List<HeightPoint> neighbours = new ArrayList<>();

                addTopNeighbour(y, x, memoryPoint, neighbours);
                addBottomNeighbour(y, x, memoryPoint, neighbours);
                addLeftNeighbour(y, x, memoryPoint, neighbours);
                addRightNeighbour(y, x, memoryPoint, neighbours);

                heightPoints.get(y).get(x).setNeighbors(neighbours);
            }
        }
    }

    private void addIfReachable(HeightPoint memoryPoint,
                                List<HeightPoint> neighbours,
                                HeightPoint neighbour) {
        if (neighbour.getHeight() <= memoryPoint.getHeight() + 1) {
            neighbours.add(neighbour);
        }
    }

    private void addRightNeighbour(int y, int x, HeightPoint memoryPoint, List<HeightPoint> neighbours) {
        if (x < heightPoints.get(y).size() - 1) {
            HeightPoint bottom = heightPoints.get(y).get(x + 1);
            addIfReachable(memoryPoint, neighbours, bottom);
        }
    }

    private void addLeftNeighbour(int y, int x, HeightPoint memoryPoint, List<HeightPoint> neighbours) {
        if (x > 0) {
            HeightPoint left = heightPoints.get(y).get(x - 1);
            addIfReachable(memoryPoint, neighbours, left);
        }
    }

    private void addBottomNeighbour(int y, int x, HeightPoint memoryPoint, List<HeightPoint> neighbours) {
        if (y < heightPoints.size() - 1) {
            HeightPoint bottom = heightPoints.get(y + 1).get(x);
            addIfReachable(memoryPoint, neighbours, bottom);
        }
    }

    private void addTopNeighbour(int y, int x, HeightPoint memoryPoint, List<HeightPoint> neighbours) {
        if (y > 0) {
            HeightPoint top = heightPoints.get(y - 1).get(x);
            addIfReachable(memoryPoint, neighbours, top);
        }
    }

    public int findShortestPathFromStart() {
        findShortestPath(start, 0);
        return end.getStepCount();
    }

    public int findShortestPath() {
        return heightPoints.stream()
                .flatMap(Collection::stream)
                .filter(e -> e.getHeight() == 'a')
                .mapToInt(e -> {
                    findShortestPath(e, 0);
                    return end.getStepCount();
                })
                .min().orElseThrow();
    }

    private void findShortestPath(HeightPoint memoryPoint, int stepCount) {
        if (stepCount >= memoryPoint.getStepCount()) {
            return;
        }
        memoryPoint.setStepCount(stepCount);
        if (memoryPoint.isEnd()) {
            return;
        }
        for (HeightPoint point : memoryPoint.getNeighbours()) {
            findShortestPath(point, stepCount + 1);
        }
    }
}
