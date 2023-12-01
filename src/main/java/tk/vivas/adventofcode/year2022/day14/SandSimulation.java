package tk.vivas.adventofcode.year2022.day14;

import java.util.Arrays;
import java.util.List;

class SandSimulation {
    private final int start;
    private final int maxY;
    private final boolean[][] cave;
    private final int startWithFloor;
    private final boolean[][] caveWithFloor;

    public SandSimulation(String input) {
        List<Path> pathList = input.lines()
                .map(Path::parse)
                .toList();

        int minX = getMinX(pathList) - 1;
        int maxX = getMaxX(pathList) + 1;
        maxY = getMaxY(pathList);

        start = 500 - minX;

        int realMinX = Math.min(minX + 1, 500 - (maxY + 2)) - 1;
        int realMaxX = Math.max(maxX - 1, 500 + (maxY + 2)) + 1;

        startWithFloor = 500 - realMinX;

        cave = new boolean[maxY + 1][maxX - minX + 1];
        caveWithFloor = new boolean[maxY + 3][realMaxX - realMinX + 1];

        initCaves(pathList, minX, realMinX);
    }

    private void initCaves(List<Path> pathList, int minX, int realMinX) {
        for (boolean[] line : cave) Arrays.fill(line, true);
        for (int i = 0; i < caveWithFloor.length - 1; i++) {
            Arrays.fill(caveWithFloor[i], true);
        }

        pathList.stream()
                .flatMap(Path::getPoints)
                .forEach(point -> {
                    cave[point.y()][point.x() - minX] = false;
                    caveWithFloor[point.y()][point.x() - realMinX] = false;
                });
    }

    public int fillFlooredCaveWithSand() {
        int sandCounter = 0;
        while (dropSandUnitInFlooredCave()) {
            sandCounter++;
        }
        return sandCounter;
    }

    public int fillCaveWithSand() {
        int sandCounter = 0;
        while (dropSandUnit()) {
            sandCounter++;
        }
        return sandCounter;
    }

    private boolean dropSandUnitInFlooredCave() {
        Sand sand = new Sand(startWithFloor, 0);

        if (!caveWithFloor[sand.getY()][sand.getX()]) {
            return false;
        }
        boolean canFlow = true;
        while (canFlow) {
            if (caveWithFloor[sand.getY() + 1][sand.getX()]) {
                sand.moveDown();
            } else if (caveWithFloor[sand.getY() + 1][sand.getX() - 1]) {
                sand.moveLeft();
            } else if (caveWithFloor[sand.getY() + 1][sand.getX() + 1]) {
                sand.moveRight();
            } else {
                caveWithFloor[sand.getY()][sand.getX()] = false;
                canFlow = false;
            }
        }
        return true;
    }

    private boolean dropSandUnit() {
        Sand sand = new Sand(start, 0);

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
