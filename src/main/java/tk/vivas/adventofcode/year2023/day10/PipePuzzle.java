package tk.vivas.adventofcode.year2023.day10;

import java.util.ArrayList;
import java.util.List;

class PipePuzzle {

    private final PipeType[][] pipeMap;
    private final int mapSizeX;
    private final int mapSizeY;
    private PipeTile startingPipeTile;

    PipePuzzle(String input) {
        List<String> lines = input.lines().toList();
        mapSizeX = lines.getFirst().length();
        mapSizeY = lines.size();
        pipeMap = new PipeType[mapSizeY][mapSizeX];
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
        while (PipeType.START != currentPipeType) {
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
}
