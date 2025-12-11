package tk.vivas.adventofcode.year2025.day10;

import one.util.streamex.MoreCollectors;

import java.util.List;
import java.util.stream.IntStream;

class IndicatorLightDiagram {

    private final boolean[] indicators;

    IndicatorLightDiagram(String input) {
        indicators = input.chars()
                .mapToObj(indicator -> '#' == (char) indicator)
                .collect(MoreCollectors.toBooleanArray(bool -> bool));

        boolean allIndicatorsOff = true;
        for (boolean indicator : indicators) {
            if (indicator) {
                allIndicatorsOff = false;
                break;
            }
        }
        if (allIndicatorsOff) {
            throw new IllegalArgumentException("At least one indicator must be in its on state");
        }
    }

    boolean correctlyConfiguresLights(List<Button> buttonCombination) {
        return IntStream.range(0, indicators.length)
                .allMatch(i -> indicators[i] == switchesLight(i, buttonCombination));
    }

    private boolean switchesLight(int index, List<Button> combination) {
        return combination.stream()
                .filter(button -> button.switchesLight(index))
                .count() % 2 == 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (boolean indicator : indicators) {
            sb.append(indicator ? "#" : ".");
        }
        sb.append("]");
        return sb.toString();
    }
}
