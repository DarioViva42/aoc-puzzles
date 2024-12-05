package tk.vivas.adventofcode.year2024.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class WordSearch {

    private final List<String> horizontalLines;
    private final List<String> allLines;

    private final int width;
    private final int height;

    private final Pattern XMASPattern = Pattern.compile("XMAS|SAMX");

    WordSearch(String input) {
        horizontalLines = input.lines().toList();

        width = horizontalLines.getFirst().length();
        height = horizontalLines.size();

        allLines = new ArrayList<>(horizontalLines);

        IntStream.range(0, width)
                .mapToObj(this::createVerticalLine)
                .forEach(allLines::add);

        IntStream.range(-height + 1, height)
                .mapToObj(this::createBackwardsDiagonal)
                .forEach(allLines::add);

        IntStream.range(-height + 1, height)
                .mapToObj(this::createForwardsDiagonal)
                .forEach(allLines::add);
    }

    private String createVerticalLine(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        horizontalLines.stream()
                .map(line -> line.charAt(i))
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    private String createBackwardsDiagonal(int i) {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, height)
                .filter(j -> inHorizontalRange(i, j))
                .mapToObj(j -> horizontalLines.get(j).charAt(j + i))
                .forEach(builder::append);
        return builder.toString();
    }

    private String createForwardsDiagonal(int i) {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, height)
                .filter(j -> inHorizontalRange(i, j))
                .mapToObj(j -> horizontalLines.get(height - j - 1).charAt(j + i))
                .forEach(builder::append);
        return builder.toString();
    }

    private boolean inHorizontalRange(int i, int j) {
        return j + i >= 0 && j + i < width;
    }

    long countXMAS() {
        return allLines.stream()
                .mapToLong(this::countXMAS)
                .sum();
    }

    private long countXMAS(String line) {
        String preparedLine = line
                .replace("S", "SS")
                .replace("X", "XX");
        return XMASPattern.matcher(preparedLine)
                .results().count();
    }

    long countX_MAS() {
        return IntStream.range(1, height - 1)
                .mapToLong(i -> IntStream.range(1, height - 1)
                        .filter(j -> checkMiddle(i, j))
                        .filter(j -> checkBackwardDiagonal(i, j))
                        .filter(j -> checkForwardDiagonal(i, j))
                        .count())
                .sum();
    }

    private boolean checkMiddle(int i, int j) {
        return 'A' == horizontalLines.get(j).charAt(i);
    }

    private boolean checkBackwardDiagonal(int i, int j) {
        char topLeft = horizontalLines.get(j - 1).charAt(i - 1);
        char bottomRight = horizontalLines.get(j + 1).charAt(i + 1);
        return ('M' == topLeft && 'S' == bottomRight) || ('S' == topLeft && 'M' == bottomRight);
    }

    private boolean checkForwardDiagonal(int i, int j) {
        char topRight = horizontalLines.get(j - 1).charAt(i + 1);
        char bottomLeft = horizontalLines.get(j + 1).charAt(i - 1);
        return ('M' == topRight && 'S' == bottomLeft) || ('S' == topRight && 'M' == bottomLeft);
    }
}
