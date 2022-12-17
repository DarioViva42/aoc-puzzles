package vivas.tk.adventofcode.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Volcano {

    private final Map<String, Valve> valves;

    public Volcano(String input) {
        valves = input.lines()
                .map(Valve::parse)
                .collect(Collectors.toMap(Valve::id, Function.identity()));

        valves.values().forEach(valve -> valve.attachNeighbours(valves));
    }

    public int findBestPressureReleaseStrategy() {
        Valve startingValve = valves.get("AA");
        List<Strategy> strategyList = new ArrayList<>();
        strategyList.add(new Strategy(startingValve));
        for (int leftMinutes = 29; leftMinutes > 0; leftMinutes--) {
            List<Strategy> nextStrategies = new ArrayList<>();
            strategyList.forEach(Strategy::writeScoreToValve);
            for (Strategy strategy : strategyList) {
                addToNextStrategies(leftMinutes, nextStrategies, strategy);
            }
            if (nextStrategies.isEmpty()) {
                break;
            }
            strategyList = nextStrategies;
        }
        return strategyList.stream()
                .mapToInt(Strategy::getScore)
                .max().orElseThrow();
    }

    private void addToNextStrategies(int leftMinutes, List<Strategy> nextStrategies, Strategy strategy) {
        Valve currentValve = strategy.getCurrentValve();
        currentValve.getNeighbours().stream()
                .filter(valve -> valve.getBestScore() < strategy.getScore())
                .map(valve -> new Strategy(strategy, valve))
                .forEach(nextStrategies::add);
        if (strategy.shouldOpenValve()) {
            strategy.openValve();
            strategy.addScore(currentValve.flowRate() * leftMinutes);
            nextStrategies.add(strategy);
        }
    }
}
