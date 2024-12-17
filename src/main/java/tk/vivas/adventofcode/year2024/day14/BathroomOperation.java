package tk.vivas.adventofcode.year2024.day14;

import tk.vivas.Position;

import java.util.List;

class BathroomOperation {

    private final List<Robot> robots;

    BathroomOperation(String input) {
        robots = input.lines()
                .map(Robot::new)
                .toList();
    }

    long safetyFactor(int sizeX, int sizeY) {
        List<Position> reachedPositions = robots.stream()
                .map(robot -> robot.afterElapsedTime(100L, sizeX, sizeY))
                .toList();

        int xSplit = sizeX / 2;
        int ySplit = sizeY / 2;

        long topLeft = reachedPositions.stream()
                .filter(position -> position.x() < xSplit && position.y() < ySplit)
                .count();
        long topRight = reachedPositions.stream()
                .filter(position -> position.x() > xSplit && position.y() < ySplit)
                .count();
        long bottomLeft = reachedPositions.stream()
                .filter(position -> position.x() < xSplit && position.y() > ySplit)
                .count();
        long bottomRight = reachedPositions.stream()
                .filter(position -> position.x() > xSplit && position.y() > ySplit)
                .count();
        return topLeft * topRight * bottomLeft * bottomRight;
    }

    long cycleLength(int sizeX, int sizeY) {
        List<Position> initialState = robots.stream()
                .map(robot -> robot.afterElapsedTime(0L, sizeX, sizeY))
                .toList();

        long time = 0L;
        while(true) {
            long currentMoment = ++time;
            List<Position> reachedPositions = robots.stream()
                    .map(robot -> robot.afterElapsedTime(currentMoment, sizeX, sizeY))
                    .toList();

            if (reachedPositions.equals(initialState)) {
                return currentMoment;
            }
        }
    }
}
