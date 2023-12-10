package tk.vivas.adventofcode.year2023.day10;

import tk.vivas.ConsoleColors;

import java.util.ArrayList;
import java.util.List;

import static tk.vivas.adventofcode.year2023.day10.Direction.*;
import static tk.vivas.adventofcode.year2023.day10.PipeType.*;

class PipePuzzle {

    private final PipeType[][] pipeMap;
    private final int mapSizeX;
    private final int mapSizeY;
    private PipeTile startingPipeTile;
    private boolean[][] partOfLoopMapCache;
    private PipeTile firstCandidateCache;
    private PipeType realStartTypeCache;
    private boolean[][] insideLoopMapCache;

    PipePuzzle(String input) {
        List<String> lines = input.lines().toList();
        mapSizeX = lines.getFirst().length();
        mapSizeY = lines.size();
        pipeMap = new PipeType[mapSizeX][mapSizeY];
        for (int y = 0; y < lines.size(); y++) {
            Character[] chars = lines.get(y).chars()
                    .mapToObj(c -> (char) c)
                    .toArray(Character[]::new);

            for (int x = 0; x < chars.length; x++) {
                Character character = chars[x];
                if (character == 'S') {
                    startingPipeTile = new PipeTile(x, y, null);
                }
                pipeMap[x][y] = PipeType.from(character);
            }
        }
    }

    long findFurthestPointInLoop() {
        PipeTile currentPipeTile = findFirstCandidate();
        PipeType currentPipeType = typeOfTile(currentPipeTile);

        long loopLength = 1;
        while (START != currentPipeType) {
            currentPipeTile = currentPipeTile.move(currentPipeType);
            currentPipeType = typeOfTile(currentPipeTile);
            loopLength++;
        }
        return loopLength / 2;
    }

    private PipeType typeOfTile(PipeTile tile) {
        return pipeMap[tile.x()][tile.y()];
    }

    private PipeTile findFirstCandidate() {
        if (firstCandidateCache != null) {
            return firstCandidateCache;
        }

        int x = startingPipeTile.x();
        int y = startingPipeTile.y();

        List<PipeTile> candidates = new ArrayList<>();

        if (y - 1 >= 0 && pipeMap[x][y - 1].connectsSouth()) {
            candidates.add(startingPipeTile.north());
        }
        if (x + 1 <= mapSizeX && pipeMap[x + 1][y].connectsWest()) {
            candidates.add(startingPipeTile.east());
        }
        if (y + 1 <= mapSizeY && pipeMap[x][y + 1].connectsNorth()) {
            candidates.add(startingPipeTile.south());
        }
        if (x - 1 >= 0 && pipeMap[x - 1][y].connectsEast()) {
            candidates.add(startingPipeTile.west());
        }

        if (candidates.size() != 2) {
            throw new IllegalStateException("expected two pipes to connect to starting pipe");
        }

        PipeTile firstCandidate = candidates.getFirst();
        firstCandidateCache = firstCandidate;
        return firstCandidate;
    }

    private PipeType findRealStartType() {
        if (realStartTypeCache != null) {
            return realStartTypeCache;
        }

        int x = startingPipeTile.x();
        int y = startingPipeTile.y();

        List<Direction> directionList = new ArrayList<>();

        if (y - 1 >= 0 && pipeMap[x][y - 1].connectsSouth()) {
            directionList.add(NORTH);
        }
        if (x + 1 <= mapSizeX && pipeMap[x + 1][y].connectsWest()) {
            directionList.add(EAST);
        }
        if (y + 1 <= mapSizeY && pipeMap[x][y + 1].connectsNorth()) {
            directionList.add(SOUTH);
        }
        if (x - 1 >= 0 && pipeMap[x - 1][y].connectsEast()) {
            directionList.add(WEST);
        }

        if (directionList.size() != 2) {
            throw new IllegalStateException("expected two pipes to connect to starting pipe");
        }

        PipeType realStartType = from(directionList.getFirst(), directionList.getLast());
        realStartTypeCache = realStartType;
        return realStartType;
    }

    long countEnclosedTiles() {
        boolean[][] partOfLoopMap = markPartOfLoop();

        pipeMap[startingPipeTile.x()][startingPipeTile.y()] = findRealStartType();

        long count = 0;
        boolean insideLoop = false;
        boolean polarizationCheck = false;
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                if (partOfLoopMap[x][y]) {
                    PipeType currentType = pipeMap[x][y];
                    if (shouldChangeContext(currentType, polarizationCheck)) {
                        insideLoop = !insideLoop;
                    } else if (currentType == EAST_SOUTH) {
                        polarizationCheck = true;
                    } else if (currentType == NORTH_EAST) {
                        polarizationCheck = false;
                    }
                } else if (insideLoop) {
                    count++;
                }
            }
        }

        pipeMap[startingPipeTile.x()][startingPipeTile.y()] = START;

        return count;
    }

    private boolean[][] markInsideOfLoop() {
        if (insideLoopMapCache != null) {
            return insideLoopMapCache;
        }

        boolean[][] partOfLoopMap = markPartOfLoop();

        pipeMap[startingPipeTile.x()][startingPipeTile.y()] = findRealStartType();

        boolean[][] insideLoopMap = new boolean[mapSizeX][mapSizeY];
        boolean insideLoop = false;
        boolean polarizationCheck = false;
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                if (partOfLoopMap[x][y]) {
                    PipeType currentType = pipeMap[x][y];
                    if (shouldChangeContext(currentType, polarizationCheck)) {
                        insideLoop = !insideLoop;
                    } else if (currentType == EAST_SOUTH) {
                        polarizationCheck = true;
                    } else if (currentType == NORTH_EAST) {
                        polarizationCheck = false;
                    }
                } else if (insideLoop) {
                    insideLoopMap[x][y] = true;
                }
            }
        }

        pipeMap[startingPipeTile.x()][startingPipeTile.y()] = START;

        insideLoopMapCache = insideLoopMap;
        return insideLoopMap;
    }

    @Override
    public String toString() {
        boolean[][] partOfLoopMap = markPartOfLoop();
        boolean[][] insideLoopMap = markInsideOfLoop();

        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                if (pipeMap[x][y] == START) {
                    sb.append(ConsoleColors.BLACK);
                    sb.append(ConsoleColors.RED_BACKGROUND);
                    sb.append(pipeMap[x][y]);
                    sb.append(ConsoleColors.RESET);
                } else if (partOfLoopMap[x][y]) {
                    sb.append(ConsoleColors.RED);
                    sb.append(pipeMap[x][y]);
                    sb.append(ConsoleColors.RESET);
                } else if (insideLoopMap[x][y]) {
                    sb.append(ConsoleColors.BLACK_BACKGROUND_BRIGHT);
                    sb.append(pipeMap[x][y]);
                    sb.append(ConsoleColors.RESET);
                } else {
                    sb.append(pipeMap[x][y]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean[][] markPartOfLoop() {
        if (partOfLoopMapCache != null) {
            return partOfLoopMapCache;
        }

        boolean[][] partOfLoopMap = new boolean[mapSizeX][mapSizeY];
        partOfLoopMap[startingPipeTile.x()][startingPipeTile.y()] = true;

        PipeTile currentPipeTile = findFirstCandidate();
        PipeType currentPipeType = typeOfTile(currentPipeTile);

        partOfLoopMap[currentPipeTile.x()][currentPipeTile.y()] = true;
        while (START != currentPipeType) {
            currentPipeTile = currentPipeTile.move(currentPipeType);
            currentPipeType = typeOfTile(currentPipeTile);
            partOfLoopMap[currentPipeTile.x()][currentPipeTile.y()] = true;
        }
        partOfLoopMapCache = partOfLoopMap;
        return partOfLoopMap;
    }

    private boolean shouldChangeContext(PipeType currentType, boolean polarizationCheck) {
        return currentType == NORTH_SOUTH
                || (polarizationCheck && currentType == NORTH_WEST)
                || (!polarizationCheck && currentType == SOUTH_WEST);
    }
}
