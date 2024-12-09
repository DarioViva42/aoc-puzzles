package tk.vivas.adventofcode.year2024.day06;

import java.util.Arrays;
import java.util.List;

import static tk.vivas.adventofcode.year2024.day06.TileType.VISITED;

class PrototypeSuitManufacturingLab {

    private final Guard guard;
    private final TileType[][] labMap;

    PrototypeSuitManufacturingLab(String input) {
        List<String> lines = input.lines().toList();
        int height = lines.size();
        int width = lines.getFirst().length();

        int guardStartX = 0;
        int guardStartY = 0;
        Direction guardStartDirection = null;

        labMap = new TileType[height][width];

        for (int j = 0; j < height; j++) {
            char[] line = lines.get(j).toCharArray();
            for (int i = 0; i < width; i++) {
                char c = line[i];
                TileType tile = TileType.of(c);

                if (VISITED.equals(tile)) {
                    guardStartDirection = Direction.of(c);
                    guardStartX = i;
                    guardStartY = j;
                }

                labMap[j][i] = tile;
            }
        }

        guard = new Guard(guardStartX, guardStartY, guardStartDirection, labMap, height, width);
    }

    long countVisitedPositions() {
        while (true) {
            if (guard.move()) {
                break;
            }
        }

        return Arrays.stream(labMap)
                .flatMap(Arrays::stream)
                .filter(VISITED::equals)
                .count();
    }
}
