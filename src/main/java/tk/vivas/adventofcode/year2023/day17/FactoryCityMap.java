package tk.vivas.adventofcode.year2023.day17;

import java.util.Arrays;
import java.util.List;

class FactoryCityMap {

    private final int[][] cityBlocks;
    private final int[][][][] heatLossMap;
    private final int[][][][] heatLossMapUltraCrucible;
    private final int citySizeX;
    private final int citySizeY;

    FactoryCityMap(String input) {
        List<String> lines = input.lines().toList();
        citySizeX = lines.getFirst().length();
        citySizeY = lines.size();
        cityBlocks = new int[citySizeX][citySizeY];

        for (int y = 0; y < citySizeY; y++) {
            String row = lines.get(y);
            for (int x = 0; x < citySizeX; x++) {
                cityBlocks[x][y] = row.charAt(x) - '0';
            }
        }

        heatLossMap = new int[4][3][citySizeX][citySizeY];
        for (int[][][] directions : heatLossMap) {
            for (int[][] type : directions) {
                for (int[] column : type) {
                    Arrays.fill(column, Integer.MAX_VALUE);
                }
            }
        }

        heatLossMapUltraCrucible = new int[4][7][citySizeX][citySizeY];
        for (int[][][] directions : heatLossMapUltraCrucible) {
            for (int[][] type : directions) {
                for (int[] column : type) {
                    Arrays.fill(column, Integer.MAX_VALUE);
                }
            }
        }
    }

    int findMinimalHeatLoss() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                heatLossMap[i][j][0][0] = 0;
            }
        }
        moveSouth(0, 0, 0);
        moveEast(0, 0, 0);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                int x = heatLossMap[i][j][citySizeX - 1][citySizeY - 1];
                if (x < min) {
                    min = x;
                }
            }
        }
        return min;
    }

    int findMinimalHeatLossWithUltraCrucible() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                heatLossMapUltraCrucible[i][j][0][0] = 0;
            }
        }
        moveSouthUltraCrucible(0, 0, 0);
        moveEastUltraCrucible(0, 0, 0);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                int x = heatLossMapUltraCrucible[i][j][citySizeX - 1][citySizeY - 1];
                if (x < min) {
                    min = x;
                }
            }
        }
        return min;
    }

    private void moveNorth(int x, int y, int currentLoss) {
        for (int i = 0; i < 3; i++) {
            y--;
            if (y < 0) {
                return;
            }
            currentLoss += cityBlocks[x][y];
            if (currentLoss >= heatLossMap[0][i][x][y]) {
                return;
            }
            heatLossMap[0][i][x][y] = currentLoss;
            moveWest(x, y, currentLoss);
            moveEast(x, y, currentLoss);
        }
    }

    private void moveEast(int x, int y, int currentLoss) {
        for (int i = 0; i < 3; i++) {
            x++;
            if (x >= citySizeX) {
                return;
            }
            currentLoss += cityBlocks[x][y];
            if (currentLoss >= heatLossMap[1][i][x][y]) {
                return;
            }
            heatLossMap[1][i][x][y] = currentLoss;
            if (x == citySizeX - 1 && y == citySizeY - 1) {
                return;
            }
            moveNorth(x, y, currentLoss);
            moveSouth(x, y, currentLoss);
        }
    }

    private void moveSouth(int x, int y, int currentLoss) {
        for (int i = 0; i < 3; i++) {
            y++;
            if (y >= citySizeY) {
                return;
            }
            currentLoss += cityBlocks[x][y];
            if (currentLoss >= heatLossMap[2][i][x][y]) {
                return;
            }
            heatLossMap[2][i][x][y] = currentLoss;
            if (x == citySizeX - 1 && y == citySizeY - 1) {
                return;
            }
            moveWest(x, y, currentLoss);
            moveEast(x, y, currentLoss);
        }
    }

    private void moveWest(int x, int y, int currentLoss) {
        for (int i = 0; i < 3; i++) {
            x--;
            if (x < 0) {
                return;
            }
            currentLoss += cityBlocks[x][y];
            if (currentLoss >= heatLossMap[3][i][x][y]) {
                return;
            }
            heatLossMap[3][i][x][y] = currentLoss;
            moveNorth(x, y, currentLoss);
            moveSouth(x, y, currentLoss);
        }
    }

    private void moveNorthUltraCrucible(int x, int y, int currentLoss) {
        for (int i = 0; i < 3; i++) {
            y--;
            if (y < 0) {
                return;
            }
            currentLoss += cityBlocks[x][y];
        }
        for (int i = 0; i < 7; i++) {
            y--;
            if (y < 0) {
                return;
            }
            currentLoss += cityBlocks[x][y];
            if (currentLoss >= heatLossMapUltraCrucible[0][i][x][y]) {
                return;
            }
            heatLossMapUltraCrucible[0][i][x][y] = currentLoss;
            moveWestUltraCrucible(x, y, currentLoss);
            moveEastUltraCrucible(x, y, currentLoss);
        }
    }

    private void moveEastUltraCrucible(int x, int y, int currentLoss) {
        for (int i = 0; i < 3; i++) {
            x++;
            if (x >= citySizeX) {
                return;
            }
            currentLoss += cityBlocks[x][y];
        }
        for (int i = 0; i < 7; i++) {
            x++;
            if (x >= citySizeX) {
                return;
            }
            currentLoss += cityBlocks[x][y];
            if (currentLoss >= heatLossMapUltraCrucible[1][i][x][y]) {
                return;
            }
            heatLossMapUltraCrucible[1][i][x][y] = currentLoss;
            if (x == citySizeX - 1 && y == citySizeY - 1) {
                return;
            }
            moveNorthUltraCrucible(x, y, currentLoss);
            moveSouthUltraCrucible(x, y, currentLoss);
        }
    }

    private void moveSouthUltraCrucible(int x, int y, int currentLoss) {
        for (int i = 0; i < 3; i++) {
            y++;
            if (y >= citySizeY) {
                return;
            }
            currentLoss += cityBlocks[x][y];
        }
        for (int i = 0; i < 7; i++) {
            y++;
            if (y >= citySizeY) {
                return;
            }
            currentLoss += cityBlocks[x][y];
            if (currentLoss >= heatLossMapUltraCrucible[2][i][x][y]) {
                return;
            }
            heatLossMapUltraCrucible[2][i][x][y] = currentLoss;
            if (x == citySizeX - 1 && y == citySizeY - 1) {
                return;
            }
            moveWestUltraCrucible(x, y, currentLoss);
            moveEastUltraCrucible(x, y, currentLoss);
        }
    }

    private void moveWestUltraCrucible(int x, int y, int currentLoss) {
        for (int i = 0; i < 3; i++) {
            x--;
            if (x < 0) {
                return;
            }
            currentLoss += cityBlocks[x][y];
        }
        for (int i = 0; i < 7; i++) {
            x--;
            if (x < 0) {
                return;
            }
            currentLoss += cityBlocks[x][y];
            if (currentLoss >= heatLossMapUltraCrucible[3][i][x][y]) {
                return;
            }
            heatLossMapUltraCrucible[3][i][x][y] = currentLoss;
            moveNorthUltraCrucible(x, y, currentLoss);
            moveSouthUltraCrucible(x, y, currentLoss);
        }
    }


}
