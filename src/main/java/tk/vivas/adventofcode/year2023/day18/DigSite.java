package tk.vivas.adventofcode.year2023.day18;

import java.util.Arrays;
import java.util.List;

import static tk.vivas.adventofcode.year2023.day18.DigTileType.EAST_SOUTH;
import static tk.vivas.adventofcode.year2023.day18.DigTileType.NORTH_EAST;
import static tk.vivas.adventofcode.year2023.day18.DigTileType.NORTH_SOUTH;
import static tk.vivas.adventofcode.year2023.day18.DigTileType.NORTH_WEST;
import static tk.vivas.adventofcode.year2023.day18.DigTileType.SOUTH_WEST;

class DigSite {

    private static final String ERROR_ILLEGAL_NEXT_DIRECTION = "only angels of 90 degrees allowed";
    private final List<DigInstruction> instructionList;
    private DigTileType[][] digMap;
    private int startY;
    private int startX;
    private int sizeX;
    private int sizeY;

    DigSite(String input) {
        instructionList = input.lines()
                .map(DigInstruction::parse)
                .toList();
    }

    int countSize() {
        initMap();
        int x = startX;
        int y = startY;
        for (int j = 0; j < instructionList.size(); j++) {
            DigInstruction instruction = instructionList.get(j);

            int amount = instruction.amount();
            for (int i = 0; i < amount; i++) {
                switch (instruction.direction()) {
                    case UP -> {
                        y--;
                        digMap[x][y] = NORTH_SOUTH;
                    }
                    case RIGHT -> {
                        x++;
                        digMap[x][y] = DigTileType.EAST_WEST;
                    }
                    case DOWN -> {
                        y++;
                        digMap[x][y] = NORTH_SOUTH;
                    }
                    case LEFT -> {
                        x--;
                        digMap[x][y] = DigTileType.EAST_WEST;
                    }
                }
            }
            DigInstruction nextInstruction = instructionList.get((j + 1) % instructionList.size());
            switch (instruction.direction()) {
                case UP -> {
                    switch (nextInstruction.direction()) {
                        case RIGHT -> digMap[x][y] = EAST_SOUTH;
                        case LEFT -> digMap[x][y] = SOUTH_WEST;
                        case UP, DOWN -> throw new IllegalStateException(ERROR_ILLEGAL_NEXT_DIRECTION);
                    }
                }
                case RIGHT -> {
                    switch (nextInstruction.direction()) {
                        case UP -> digMap[x][y] = NORTH_WEST;
                        case DOWN -> digMap[x][y] = SOUTH_WEST;
                        case LEFT, RIGHT -> throw new IllegalStateException(ERROR_ILLEGAL_NEXT_DIRECTION);
                    }
                }
                case DOWN -> {
                    switch (nextInstruction.direction()) {
                        case RIGHT -> digMap[x][y] = NORTH_EAST;
                        case LEFT -> digMap[x][y] = NORTH_WEST;
                        case UP, DOWN -> throw new IllegalStateException(ERROR_ILLEGAL_NEXT_DIRECTION);
                    }
                }
                case LEFT -> {
                    switch (nextInstruction.direction()) {
                        case UP -> digMap[x][y] = NORTH_EAST;
                        case DOWN -> digMap[x][y] = EAST_SOUTH;
                        case LEFT, RIGHT -> throw new IllegalStateException(ERROR_ILLEGAL_NEXT_DIRECTION);
                    }
                }
            }
        }
        int totalBorderTiles = instructionList.stream()
                .mapToInt(DigInstruction::amount)
                .sum();
        return totalBorderTiles + countEnclosedTiles();
    }

    int countEnclosedTiles() {
        int count = 0;
        boolean insideLoop = false;
        boolean polarizationCheck = false;
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                DigTileType currentType = digMap[x][y];
                if (currentType != DigTileType.EMPTY) {
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

        return count;
    }

    private boolean shouldChangeContext(DigTileType currentType, boolean polarizationCheck) {
        return currentType == NORTH_SOUTH
               || (polarizationCheck && currentType == NORTH_WEST)
               || (!polarizationCheck && currentType == SOUTH_WEST);
    }


    private void initMap() {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int x = 0;
        int y = 0;
        for (DigInstruction digInstruction : instructionList) {
            int amount = digInstruction.amount();
            switch (digInstruction.direction()) {
                case UP -> {
                    y -= amount;
                    minY = Math.min(minY, y);
                }
                case RIGHT -> {
                    x += amount;
                    maxX = Math.max(maxX, x);
                }
                case DOWN -> {
                    y += amount;
                    maxY = Math.max(maxY, y);
                }
                case LEFT -> {
                    x -= amount;
                    minX = Math.min(minX, x);
                }
            }
        }
        startX = Math.abs(minX);
        startY = Math.abs(minY);

        sizeX = maxX - minX + 1;
        sizeY = maxY - minY + 1;
        this.digMap = new DigTileType[sizeX][sizeY];
        for (DigTileType[] column : digMap) {
            Arrays.fill(column, DigTileType.EMPTY);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                sb.append(digMap[x][y]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
