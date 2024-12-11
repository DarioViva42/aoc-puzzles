package tk.vivas.adventofcode.year2024.day08;

import tk.vivas.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AntinodeCounter {

    private final Map<Character, List<Position>> antennaPositions;
    private final int height;
    private final int width;

    AntinodeCounter(String input) {
        List<String> lines = input.lines().toList();

        height = lines.size();
        width = lines.getFirst().length();

        antennaPositions = new HashMap<>();

        for (int j = 0; j < height; j++) {
            char[] line = lines.get(j).toCharArray();
            for (int i = 0; i < line.length; i++) {
                char c = line[i];
                if (c == '.') {
                    continue;
                }
                antennaPositions
                        .computeIfAbsent(c, character -> new ArrayList<>())
                        .add(new Position(i, j));
            }
        }
    }

    long countUniqueLocations() {
        return antennaPositions.values().stream()
                .map(AntinodeFinder::new)
                .flatMap(AntinodeFinder::findAntinodes)
                .filter(this::isInBoundary)
                .distinct()
                .count();
    }

    private boolean isInBoundary(Position position) {
        return position.x() >= 0 && position.x() < width
                && position.y() >= 0 && position.y() < height;
    }
}
