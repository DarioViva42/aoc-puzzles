package tk.vivas.adventofcode.year2025.day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class CephalopodsHomework {

    private final String operatorLine;
    private final List<String> numberLines;
    private final List<Operator> operators;

    CephalopodsHomework(String input) {
        List<String> lines = input.lines()
                .toList();

        operatorLine = lines.getLast();
        operators = Arrays.stream(split(operatorLine))
                .map(Operator::of)
                .toList();

        numberLines = lines.stream()
                .limit(lines.size() - 1)
                .toList();
    }

    private static String[] split(String line) {
        return line.strip().split(" +");
    }

    private static Stream<String> transposeIndex(int i, List<String[]> untransposed) {
        return untransposed.stream()
                .map(numbers -> numbers[i]);
    }

    long solve() {
        return solveWithNumbers(extractNumbersNaive());
    }

    private List<List<Long>> extractNumbersNaive() {
        List<String[]> untransposed = numberLines.stream()
                .map(CephalopodsHomework::split)
                .toList();
        return IntStream.range(0, operators.size())
                .mapToObj(i -> transposeIndex(i, untransposed))
                .map(numberString -> numberString.map(Long::parseLong))
                .map(Stream::toList)
                .toList();
    }

    long solveCorrect() {
        return solveWithNumbers(extractNumbersCorrect());
    }

    private List<List<Long>> extractNumbersCorrect() {
        List<Integer> problemIndexes = new ArrayList<>(operators.size());
        IntStream.range(0, operatorLine.length())
                .filter(i -> operatorLine.charAt(i) != ' ')
                .forEach(problemIndexes::add);
        int maxLength = numberLines.stream()
                .mapToInt(String::length)
                .max().orElseThrow();
        problemIndexes.add(maxLength + 1);
        return IntStream.range(0, problemIndexes.size() - 1)
                .mapToObj(i -> IntStream.range(problemIndexes.get(i), problemIndexes.get(i + 1) - 1))
                .map(range -> range.mapToObj(this::getNumberOnColumn))
                .map(Stream::toList)
                .toList();
    }

    private long getNumberOnColumn(int index) {
        Integer[] digits = IntStream.iterate(numberLines.size() - 1, i -> i >= 0, i -> i - 1)
                .mapToObj(numberLines::get)
                .filter(line -> line.length() > index)
                .map(line -> line.charAt(index))
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .toArray(Integer[]::new);

        return IntStream.range(0, digits.length)
                .mapToLong(i -> digits[i] * (long) Math.pow(10, i))
                .sum();
    }

    private long solveWithNumbers(List<List<Long>> numbers) {
        return IntStream.range(0, operators.size())
                .mapToObj(i -> new MathProblem(operators.get(i), numbers.get(i)))
                .mapToLong(MathProblem::solve)
                .sum();
    }
}
