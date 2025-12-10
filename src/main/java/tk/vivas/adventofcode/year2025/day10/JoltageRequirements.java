package tk.vivas.adventofcode.year2025.day10;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JoltageRequirements {

    private final List<Integer> requirements;

    public JoltageRequirements(String input) {
        requirements = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    @Override
    public String toString() {
        String encoded = requirements.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return "{" + encoded + '}';
    }
}
