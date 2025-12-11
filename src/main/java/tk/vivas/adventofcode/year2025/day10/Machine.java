package tk.vivas.adventofcode.year2025.day10;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Machine {

    private final static Pattern PATTERN =
            Pattern.compile("\\[(?<lights>[.#]+)] (?<buttons>[\\d, ()]+) \\{(?<joltage>[\\d,]+)}");

    private final IndicatorLightDiagram lightDiagram;
    private final ButtonWiringSchematics wiringSchematics;
    private final JoltageRequirements requirements;

    public Machine(String line) {
        Matcher matcher = PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(line + " is not a valid machine");
        }

        lightDiagram = new IndicatorLightDiagram(matcher.group("lights"));
        wiringSchematics = new ButtonWiringSchematics(matcher.group("buttons"));
        requirements = new JoltageRequirements(matcher.group("joltage"));
    }

    long fewestButtonPresses() {
        return wiringSchematics.allButtonCombinations()
                .filter(lightDiagram::correctlyConfiguresLights)
                .mapToInt(List::size)
                .findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return lightDiagram + " " + wiringSchematics + " " + requirements;
    }
}
