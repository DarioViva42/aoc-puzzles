package tk.vivas.adventofcode.year2025.day10;

import one.util.streamex.MoreCollectors;

class IndicatorLightDiagram {

    private final boolean[] indicators;

    IndicatorLightDiagram(String input) {
        indicators = input.chars()
                .mapToObj(indicator -> '#' == (char) indicator)
                .collect(MoreCollectors.toBooleanArray(bool -> bool));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean indicator : indicators) {
            sb.append(indicator ? "#" : ".");
        }
        return sb.toString();
    }
}
