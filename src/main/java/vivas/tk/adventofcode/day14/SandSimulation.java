package vivas.tk.adventofcode.day14;

import java.util.Arrays;
import java.util.List;

class SandSimulation {
    private final int startX;
    private final int maxY;
    private final boolean[][] cave;

    public SandSimulation(String input) {
        List<Path> pathList = input.lines()
                .map(Path::parse)
                .toList();

        int minX = getMinX(pathList) - 1;
        int maxX = getMaxX(pathList) + 1;
        maxY = getMaxY(pathList);

        startX = 500 - minX;

        cave = new boolean[maxY + 1][maxX - minX + 1];
        for (boolean[] line : cave) Arrays.fill(line, true);

        pathList.stream()
                .flatMap(Path::getPoints)
                .forEach(point -> cave[point.y()][point.x() - minX] = false);
    }

    public int fillWithSand() {
        int sandCounter = 0;
        while (dropSandUnit()) {
            sandCounter++;
        }
        return sandCounter;
    }

    private boolean dropSandUnit() {
        Sand sand = new Sand(startX, 0);

        boolean canFlow = true;
        while (canFlow) {
            if (sand.getY() == maxY) {
                return false;
            } else if (cave[sand.getY() + 1][sand.getX()]) {
                sand.moveDown();
            } else if (cave[sand.getY() + 1][sand.getX() - 1]) {
                sand.moveLeft();
            } else if (cave[sand.getY() + 1][sand.getX() + 1]) {
                sand.moveRight();
            } else {
                cave[sand.getY()][sand.getX()] = false;
                canFlow = false;
            }
        }
        return true;
    }

    private int getMinX(List<Path> pathList) {
        return pathList.stream()
                .mapToInt(Path::getMinX)
                .min().orElseThrow();
    }

    private int getMaxX(List<Path> pathList) {
        return pathList.stream()
                .mapToInt(Path::getMaxX)
                .max().orElseThrow();
    }

    private int getMaxY(List<Path> pathList) {
        return pathList.stream()
                .mapToInt(Path::getMaxY)
                .max().orElseThrow();
    }
}
