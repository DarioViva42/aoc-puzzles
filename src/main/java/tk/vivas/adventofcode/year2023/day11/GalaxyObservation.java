package tk.vivas.adventofcode.year2023.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

class GalaxyObservation {

    private final Boolean[][] galaxyMap;
    private final HashSet<Integer> emptyRows;
    private final HashSet<Integer> emptyColumns;
    private final int observationSizeX;
    private final int observationSizeY;

    GalaxyObservation(String input) {
        galaxyMap = input.lines()
                .map(line -> line.chars()
                        .mapToObj(character -> '#' == character)
                        .toArray(Boolean[]::new))
                .toArray(Boolean[][]::new);

        observationSizeX = galaxyMap[0].length;
        observationSizeY = galaxyMap.length;

        emptyRows = new HashSet<>();
        emptyColumns = new HashSet<>();
        IntStream.range(0, observationSizeY).forEach(i -> {
            boolean emptyRow = Arrays.stream(galaxyMap[i]).noneMatch(e -> e);
            if (emptyRow) {
                emptyRows.add(i);
            }
        });
        IntStream.range(0, observationSizeX).forEach(i -> {
            boolean emptyColumn = Arrays.stream(galaxyMap).noneMatch(line -> line[i]);
            if (emptyColumn) {
                emptyColumns.add(i);
            }
        });
    }

    long sumUpDistances() {
        List<Galaxy> galaxyList = createGalaxyList();
        long doubleSum = galaxyList.stream()
                .mapToLong(galaxy -> galaxyList.stream()
                        .filter(not(galaxy::equals))
                        .mapToLong(galaxy::distance)
                        .sum())
                .sum();
        return doubleSum / 2;
    }

    private List<Galaxy> createGalaxyList() {
        List<Galaxy> galaxyList = new ArrayList<>();
        int yGrowth = 0;
        for (int y = 0; y < observationSizeY; y++) {
            int xGrowth = 0;
            if (emptyRows.contains(y)) {
                yGrowth++;
                continue;
            }
            for (int x = 0; x < observationSizeX; x++) {
                if (emptyColumns.contains(x)) {
                    xGrowth++;
                } else if (galaxyMap[y][x]) {
                    galaxyList.add(new Galaxy(x + xGrowth, y + yGrowth));
                }
            }
        }
        return galaxyList;
    }
}
