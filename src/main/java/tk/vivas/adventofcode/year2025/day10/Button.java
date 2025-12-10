package tk.vivas.adventofcode.year2025.day10;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Button {

    private final List<Integer> connectedLights;

    Button(String input) {
        String withoutBrackets = input.substring(1, input.length() - 1);

        connectedLights = Arrays.stream(withoutBrackets.split(","))
                .map(Integer::parseInt)
                .toList();
    }

    @Override
    public String toString() {
        String encoded = connectedLights.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return "(" + encoded + ')';
    }
}
