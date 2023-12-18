package tk.vivas.adventofcode.year2023.day18;

import java.util.ArrayList;
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
    private List<Long> xStrechList;
    private List<Long> yStrechList;

    DigSite(String input) {
        instructionList = input.lines()
                .map(DigInstruction::parse)
                .toList();
    }

    long countSize() {
        initMap(instructionList);
        drawToMap(instructionList);
        return countBorderTiles(instructionList) + countEnclosedTiles();
    }

    public long countLargeSize() {
        List<DigInstruction> realInstructionList = instructionList.stream()
                .map(DigInstruction::fromColor)
                .toList();
        List<DigInstruction> mappedInstructionList = initLargeMap(realInstructionList);
        drawToMap(mappedInstructionList);
        return countBorderTiles(realInstructionList) + countLargeEnclosedTiles();
    }

    private void drawToMap(List<DigInstruction> instructionList) {
        int x = startX;
        int y = startY;
        for (int j = 0; j < instructionList.size(); j++) {
            DigInstruction instruction = instructionList.get(j);

            long amount = instruction.amount();
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
            handleCorner(instruction, nextInstruction, x, y);
        }
    }

    private long countBorderTiles(List<DigInstruction> instructionList) {
        return instructionList.stream()
                .mapToLong(DigInstruction::amount)
                .sum();
    }

    private void handleCorner(DigInstruction instruction, DigInstruction nextInstruction, int x, int y) {
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

    long countLargeEnclosedTiles() {
        long count = 0;
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
                    count += xStrechList.get(x) * yStrechList.get(y);
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


    private void initMap(List<DigInstruction> instructionList) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int x = 0;
        int y = 0;
        for (DigInstruction digInstruction : instructionList) {
            int amount = (int) digInstruction.amount();
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

    private List<DigInstruction> initLargeMap(List<DigInstruction> instructionList) {
        List<Long> xList = new ArrayList<>();
        List<Long> yList = new ArrayList<>();
        xList.add(0L);
        yList.add(0L);
        long x = 0;
        long y = 0;
        for (DigInstruction digInstruction : instructionList) {
            long amount = digInstruction.amount();
            switch (digInstruction.direction()) {
                case UP -> {
                    y -= amount;
                    if (!yList.contains(y)) {
                        yList.add(y);
                    }
                }
                case RIGHT -> {
                    x += amount;
                    if (!xList.contains(x)) {
                        xList.add(x);
                    }
                }
                case DOWN -> {
                    y += amount;
                    if (!yList.contains(y)) {
                        yList.add(y);
                    }
                }
                case LEFT -> {
                    x -= amount;
                    if (!xList.contains(x)) {
                        xList.add(x);
                    }
                }
            }
        }
        List<Long> xListSorted = xList.stream().sorted().toList();
        List<Long> yListSorted = yList.stream().sorted().toList();

        xStrechList = initStretchList(xListSorted);
        yStrechList = initStretchList(yListSorted);

        sizeX = xList.size() * 2 - 1;
        sizeY = yList.size() * 2 - 1;

        this.digMap = new DigTileType[sizeX][sizeY];
        for (DigTileType[] column : digMap) {
            Arrays.fill(column, DigTileType.EMPTY);
        }

        startX = xListSorted.indexOf(0L) * 2;
        startY = yListSorted.indexOf(0L) * 2;

        return mapInstructionList(instructionList, xListSorted, yListSorted);
    }

    private List<DigInstruction> mapInstructionList(
            List<DigInstruction> instructionList, List<Long> xListSorted, List<Long> yListSorted) {
        int x = startX;
        int y = startY;

        long realX = 0L;
        long realY = 0L;

        List<DigInstruction> mappedInstructionList = new ArrayList<>();
        for (DigInstruction digInstruction : instructionList) {
            long amount = digInstruction.amount();
            DigDirection direction = digInstruction.direction();
            String color = digInstruction.color();
            int mappedAmount = 0;
            switch (direction) {
                case UP -> {
                    mappedAmount = y - yListSorted.indexOf(realY - amount) * 2;
                    y -= mappedAmount;
                    realY -= amount;
                }
                case RIGHT -> {
                    mappedAmount = xListSorted.indexOf(realX + amount) * 2 - x;
                    x += mappedAmount;
                    realX += amount;
                }
                case DOWN -> {
                    mappedAmount = yListSorted.indexOf(realY + amount) * 2 - y;
                    y += mappedAmount;
                    realY += amount;
                }
                case LEFT -> {
                    mappedAmount = x - xListSorted.indexOf(realX - amount) * 2;
                    x -= mappedAmount;
                    realX -= amount;
                }
            }
            mappedInstructionList.add(new DigInstruction(direction, mappedAmount, color));
        }
        return mappedInstructionList;
    }

    private List<Long> initStretchList(List<Long> sortedLongValues) {
        List<Long> strechList = new ArrayList<>();
        strechList.add(1L);
        for (int i = 0; i < sortedLongValues.size() - 1; i++) {
            long diff = sortedLongValues.get(i + 1) - sortedLongValues.get(i);
            strechList.add(diff - 1);
            strechList.add(1L);
        }
        return strechList;
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
