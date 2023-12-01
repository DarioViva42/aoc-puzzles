package tk.vivas.adventofcode.year2022.day14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class Path {
    List<Point> points;

    public Path(List<Point> points) {
        this.points = points;
    }

    public int getMinX() {
        return points.stream()
                .mapToInt(Point::x)
                .min().orElseThrow();
    }

    public int getMaxX() {
        return points.stream()
                .mapToInt(Point::x)
                .max().orElseThrow();
    }

    public int getMaxY() {
        return points.stream()
                .mapToInt(Point::y)
                .max().orElseThrow();
    }

    public static Path parse(String input) {
        List<Point> parsed = Arrays.stream(input.split(" -> "))
                .map(Point::parse)
                .toList();
        return new Path(parsed);
    }

    public Stream<Point> getPoints() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(points.get(0));
        for (int i = 0; i < points.size() - 1; i++) {
            Point pointA = points.get(i);
            Point pointB = points.get(i + 1);

            Direction direction = pointA.toPoint(pointB);

            pointList.addAll(lineToPoints(pointA, pointB, direction));
        }
        return pointList.stream();
    }

    private List<Point> lineToPoints(Point pointA, Point pointB, Direction direction) {
        List<Point> pointList = new ArrayList<>();
        Point currentPoint = pointA;
        while (!currentPoint.equals(pointB)) {
            currentPoint = currentPoint.nextInDirection(direction);
            pointList.add(currentPoint);
        }
        return pointList;
    }
}
