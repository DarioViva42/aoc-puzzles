package tk.vivas.adventofcode.year2023.day20;

import tk.vivas.MathUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ModuleNetwork {

    private final Map<String, CommunicationModule> modules;
    private final CommunicationModule broadcaster;
    private int highCount;
    private int lowCount;

    public ModuleNetwork(String input) {
        modules = input.lines()
                .map(CommunicationModule::from)
                .collect(Collectors.toMap(CommunicationModule::name, Function.identity()));
        modules.values()
                .forEach(module -> module.init(modules));
        broadcaster = modules.get("broadcaster");
        highCount = 0;
        lowCount = 0;
    }

    long countButtonPresses() {
        resetModules();
        Map<Conjunction, Integer> collectorConjunctionMap = obtainCollectorConjunctionMap();
        Stream.iterate(1, i -> !allValuesCollected(collectorConjunctionMap), i -> i + 1)
                .map(this::pressButtonWithoutCounting)
                .forEach(i -> collectorConjunctionMap.keySet().stream()
                        .filter(Conjunction::changed)
                        .filter(module -> collectorConjunctionMap.get(module) == 0)
                        .forEach(module -> collectorConjunctionMap.put(module, i)));
        long[] cycles = collectorConjunctionMap.values().stream()
                .mapToLong(e -> e)
                .toArray();
        return MathUtils.lcm(cycles);
    }

    private static boolean allValuesCollected(Map<Conjunction, Integer> map) {
        return map.values().stream()
                .noneMatch(integer -> integer == 0);
    }

    private void resetModules() {
        modules.values()
                .forEach(CommunicationModule::reset);
    }

    private Map<Conjunction, Integer> obtainCollectorConjunctionMap() {
        return Output.get().getInputModules().getFirst()
                .getInputModules().stream()
                .map(CommunicationModule::getInputModules)
                .map(List::getFirst)
                .map(Conjunction.class::cast)
                .collect(Collectors.toMap(Function.identity(), e -> 0));
    }

    long simulate() {
        IntStream.range(0, 1000)
                .forEach(this::pressButton);
        return (long) lowCount * highCount;
    }

    private void pressButton(int i) {
        lowCount++;
        broadcaster.receive("button", Pulse.LOW_PULSE);
        List<CommunicationModule> activeModules = new LinkedList<>();
        activeModules.add(broadcaster);
        while (!activeModules.isEmpty()) {
            CommunicationModule module = activeModules.removeFirst();
            Pulse sentPulse = module.send();
            if (sentPulse == null) {
                continue;
            }
            List<CommunicationModule> outputModules = module.getOutputModules();
            if (sentPulse == Pulse.HIGH_PULSE) {
                highCount += outputModules.size();
            } else {
                lowCount += outputModules.size();
            }
            activeModules.addAll(outputModules);
        }
    }

    private int pressButtonWithoutCounting(int i) {
        broadcaster.receive("button", Pulse.LOW_PULSE);
        List<CommunicationModule> activeModules = new LinkedList<>();
        activeModules.add(broadcaster);
        while (!activeModules.isEmpty()) {
            CommunicationModule module = activeModules.removeFirst();
            if (module.send() != null) activeModules.addAll(module.getOutputModules());
        }
        return i;
    }
}
