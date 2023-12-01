package tk.vivas.adventofcode.day16;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Volcano {

    private static final Logger LOGGER = LogManager.getLogger(Volcano.class);

    private final Map<String, Valve> valves;
    private final Map<String, MegaValve> simplifiedValves;

    public Volcano(String input) {
        valves = input.lines()
                .map(Valve::parse)
                .collect(Collectors.toMap(Valve::id, Function.identity()));

        valves.values().forEach(valve -> valve.attachNeighbours(valves));

        simplifiedValves = valves.values().stream()
                .filter(this::shouldValueExistInSimplifiedGraph)
                .map(MegaValve::new)
                .collect(Collectors.toMap(MegaValve::id, Function.identity()));

        simplifiedValves.values()
                .forEach(megaValve -> megaValve.attachNeighbours(simplifiedValves));
    }

    private boolean shouldValueExistInSimplifiedGraph(Valve valve) {
        return valve.flowRate() > 0 || valve.id().equals("AA") || valve.getNeighbours().size() > 2;
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

    public int findBestPressureReleaseStrategyWithElephant() {
        MegaValve startingValve = simplifiedValves.get("AA");
        Map<PositionPair, List<ElephantStrategy>> saveMap = new HashMap<>();
        ElephantStrategy initialStrategy = new ElephantStrategy(startingValve, 25);
        return initialStrategy.getBestStrategy(saveMap)
                .map(ElephantStrategy::getScore)
                .orElse(0);
    }
}
