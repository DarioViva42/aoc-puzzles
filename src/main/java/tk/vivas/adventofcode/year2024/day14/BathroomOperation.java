package tk.vivas.adventofcode.year2024.day14;

import tk.vivas.Position;

import java.util.List;

class BathroomOperation {

    private final List<Robot> robots;
    private final int sizeX;
    private final int sizeY;

    BathroomOperation(String input, int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        robots = input.lines()
                .map(Robot::new)
                .toList();
    }

    long safetyFactor() {
        List<Position> reachedPositions = getPositions(100L);

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

    long cycleLength() {
        List<Position> initialState = getPositions(0L);

        long time = 0L;
        while (true) {
            long currentMoment = ++time;
            List<Position> reachedPositions = getPositions(currentMoment);

            if (reachedPositions.equals(initialState)) {
                return currentMoment;
            }
        }
    }

    List<Position> getPositions(long passedTime) {
        return robots.stream()
                .map(robot -> robot.afterElapsedTime(passedTime, sizeX, sizeY))
                .toList();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
