package tk.vivas.adventofcode.year2023.day16;

import tk.vivas.ConsoleColors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FacilityContraption {

    private final ContraptionTile[][] tiles;
    private final boolean[][] visitedTiles;
    private final int contraptionSizeY;
    private final int contraptionSizeX;

    FacilityContraption(String input) {
        tiles = input.lines()
                .map(e -> e.chars()
                        .mapToObj(c -> (char) c)
                        .map(ContraptionTile::from)
                        .toArray(ContraptionTile[]::new))
                .toArray(ContraptionTile[][]::new);
        contraptionSizeX = tiles[0].length;
        contraptionSizeY = tiles.length;

        visitedTiles = new boolean[contraptionSizeY][contraptionSizeX];
    }

    int traceLight() {
        moveEast(-1, 0);
        return countVisitedTiles();
    }

    int traceMaxLight() {
        List<Integer> countList = new ArrayList<>();
        for (int x = 0; x < contraptionSizeX; x++) {
            moveSouth(x, -1);
            countList.add(countVisitedTiles());
            clearVisits();

            moveNorth(x, contraptionSizeY);
            countList.add(countVisitedTiles());
            clearVisits();
        }

        for (int y = 0; y < contraptionSizeY; y++) {
            moveEast(-1, y);
            countList.add(countVisitedTiles());
            clearVisits();

            moveWest(contraptionSizeX, y);
            countList.add(countVisitedTiles());
            clearVisits();
        }
        return countList.stream().mapToInt(i -> i)
                .max().orElseThrow();
    }

    private int countVisitedTiles() {
        return Arrays.stream(visitedTiles)
                .mapToInt(this::countTrue)
                .sum();
    }

    private int countTrue(boolean[] array) {
        int count = 0;
        for (boolean b : array) if (b) count++;
        return count;
    }

    private void clearVisits() {
        for (boolean[] row : visitedTiles) {
            Arrays.fill(row, false);
        }
    }

    private void moveNorth(int x, int y) {
        y--;
        if (y < 0) {
            return;
        }
        ContraptionTile currentTile = tiles[y][x];
        boolean alreadyVisited = visitedTiles[y][x];
        visitedTiles[y][x] = true;
        switch (currentTile) {
            case EMPTY_SPACE, VERTICAL_SPLITTER -> moveNorth(x, y);
            case MIRROR_NE_SW -> moveWest(x, y);
            case MIRROR_NW_SE -> moveEast(x, y);
            case HORIZONTAL_SPLITTER -> {
                if (!alreadyVisited) {
                    moveEast(x, y);
                    moveWest(x, y);
                }
            }
        }
    }

    private void moveEast(int x, int y) {
        x++;
        if (x >= contraptionSizeX) {
            return;
        }
        ContraptionTile currentTile = tiles[y][x];
        boolean alreadyVisited = visitedTiles[y][x];
        visitedTiles[y][x] = true;
        switch (currentTile) {
            case EMPTY_SPACE, HORIZONTAL_SPLITTER -> moveEast(x, y);
            case MIRROR_NE_SW -> moveSouth(x, y);
            case MIRROR_NW_SE -> moveNorth(x, y);
            case VERTICAL_SPLITTER -> {
                if (!alreadyVisited) {
                    moveNorth(x, y);
                    moveSouth(x, y);
                }
            }
        }
    }

    private void moveSouth(int x, int y) {
        y++;
        if (y >= contraptionSizeY) {
            return;
        }
        ContraptionTile currentTile = tiles[y][x];
        boolean alreadyVisited = visitedTiles[y][x];
        visitedTiles[y][x] = true;
        switch (currentTile) {
            case EMPTY_SPACE, VERTICAL_SPLITTER -> moveSouth(x, y);
            case MIRROR_NE_SW -> moveEast(x, y);
            case MIRROR_NW_SE -> moveWest(x, y);
            case HORIZONTAL_SPLITTER -> {
                if (!alreadyVisited) {
                    moveEast(x, y);
                    moveWest(x, y);
                }
            }
        }
    }

    private void moveWest(int x, int y) {
        x--;
        if (x < 0) {
            return;
        }
        ContraptionTile currentTile = tiles[y][x];
        boolean alreadyVisited = visitedTiles[y][x];
        visitedTiles[y][x] = true;
        switch (currentTile) {
            case EMPTY_SPACE, HORIZONTAL_SPLITTER -> moveWest(x, y);
            case MIRROR_NE_SW -> moveNorth(x, y);
            case MIRROR_NW_SE -> moveSouth(x, y);
            case VERTICAL_SPLITTER -> {
                if (!alreadyVisited) {
                    moveNorth(x, y);
                    moveSouth(x, y);
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < contraptionSizeY; y++) {
            for (int x = 0; x < contraptionSizeX; x++) {
                if (visitedTiles[y][x]) {
                    sb.append(ConsoleColors.YELLOW);
                    sb.append(tiles[y][x]);
                    sb.append(ConsoleColors.RESET);
                } else {
                    sb.append(tiles[y][x]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
