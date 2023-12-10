package tk.vivas.adventofcode.year2023.day10;

import java.util.ArrayList;
import java.util.List;

import static tk.vivas.adventofcode.year2023.day10.Direction.*;
import static tk.vivas.adventofcode.year2023.day10.PipeType.*;

class PipePuzzle {

    private final PipeType[][] pipeMap;
    private final int mapSizeX;
    private final int mapSizeY;
    private PipeTile startingPipeTile;

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

        return candidates.getFirst();
    }

    private PipeType findRealStartType() {
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

        return PipeType.from(directionList.getFirst(), directionList.getLast());
    }

    public long countEnclosedTiles() {
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
                } else {
                    if (insideLoop) {
                        count++;
                    }
                }
            }
        }

        pipeMap[startingPipeTile.x()][startingPipeTile.y()] = START;

        return count;
    }

    private boolean[][] markPartOfLoop() {
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
        return partOfLoopMap;
    }

    private boolean shouldChangeContext(PipeType currentType, boolean polarizationCheck) {
        return currentType == NORTH_SOUTH
                || (polarizationCheck && currentType == NORTH_WEST)
                || (!polarizationCheck && currentType == SOUTH_WEST);
    }
}
