package tk.vivas.adventofcode.year2025.day06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class CephalopodsHomework {

    private final List<MathProblem> mathProblems;

    CephalopodsHomework(String input) {
        List<String> lines = input.lines()
                .toList();

        List<Operator> operators = Arrays.stream(split(lines.getLast()))
                .map(Operator::of)
                .toList();

        List<String[]> untransposed = lines.stream()
                .limit(lines.size() - 1)
                .map(CephalopodsHomework::split)
                .toList();
        List<List<Long>> numbers = IntStream.range(0, operators.size())
                .mapToObj(i -> transposeIndex(i, untransposed))
                .map(numberString -> numberString.map(Long::parseLong))
                .map(Stream::toList)
                .toList();

        mathProblems = IntStream.range(0, operators.size())
                .mapToObj(i -> new MathProblem(operators.get(i), numbers.get(i)))
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
        return mathProblems.stream()
                .mapToLong(MathProblem::solve)
                .sum();
    }
}
