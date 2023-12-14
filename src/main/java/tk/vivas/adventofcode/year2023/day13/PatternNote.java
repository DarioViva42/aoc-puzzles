package tk.vivas.adventofcode.year2023.day13;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class PatternNote {

    private final int patternSizeY;
    private final int patternSizeX;
    private final boolean[][] pattern;

    PatternNote(String raw) {
        List<String> lines = raw.lines().toList();

        patternSizeX = lines.getFirst().length();
        patternSizeY = lines.size();

        pattern = new boolean[patternSizeX][patternSizeY];

        for (int x = 0; x < patternSizeX; x++) {
            for (int y = 0; y < patternSizeY; y++) {
                pattern[x][y] = lines.get(y).charAt(x) == '#';
            }
        }
    }

    long rowsAboveReflection() {
        return Stream.iterate(0, i -> i < patternSizeY - 1, i -> i + 1)
                .filter(i -> Stream.iterate(0, j -> j < Math.min(i + 1, patternSizeY - i - 1), j -> j + 1)
                        .allMatch(j -> rowsAtIndicesAreEqual(i - j, i + j + 1)))
                .findFirst()
                .map(i -> i + 1)
                .orElse(0);
    }

    private boolean rowsAtIndicesAreEqual(Integer i, Integer j) {
        return IntStream.range(0, patternSizeX)
                .allMatch(x -> pattern[x][i] == pattern[x][j]);
    }

    long columnsToTheLeftOfReflection() {
        return Stream.iterate(0, i -> i < patternSizeX - 1, i -> i + 1)
                .filter(i -> Stream.iterate(0, j -> j < Math.min(i + 1, patternSizeX - i - 1), j -> j + 1)
                        .allMatch(j -> columnsAtIndicesAreEqual(i - j, i + j + 1)))
                .findFirst()
                .map(i -> i + 1)
                .orElse(0);
    }

    private boolean columnsAtIndicesAreEqual(Integer i, Integer j) {
        return Arrays.equals(pattern[i], pattern[j]);
    }
}
