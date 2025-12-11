package tk.vivas.adventofcode.year2025.day10;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Button {

    private final Set<Integer> connectedLights;

    Button(String input) {
        String withoutBrackets = input.substring(1, input.length() - 1);

        List<Integer> connectedLightList = Arrays.stream(withoutBrackets.split(","))
                .map(Integer::parseInt)
                .toList();

        connectedLights = Set.copyOf(connectedLightList);

        if (connectedLightList.size() != connectedLights.size()) {
            throw new IllegalArgumentException("Buttons are not allowed to have multiple wires to one light");
        }
    }

    boolean switchesLight(int index) {
        return connectedLights.contains(index);
    }

    @Override
    public String toString() {
        String encoded = connectedLights.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return "(" + encoded + ')';
    }
}
