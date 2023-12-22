package tk.vivas.adventofcode.year2023.day20;

import tk.vivas.MathUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ModuleNetwork {

    private final Map<String, CommunicationModule> modules;
    private final CommunicationModule broadcaster;
    private int highCount;
    private int lowCount;
    private int countPressesToOutputPulse;

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
        List<List<Conjunction>> nestedConjunctionList = getRelevantConjunctions();
        Map<Conjunction, Long> collectorConjunctionMap = nestedConjunctionList.getLast().stream()
                .collect(Collectors.toMap(Function.identity(), e -> 0L));
        int i = 0;
        while (collectorConjunctionMap.values().stream().anyMatch(integer -> integer == 0)) {
            i++;
            pressButton(i);
            int current = i;

            collectorConjunctionMap.keySet().stream()
                    .filter(Conjunction::changed)
                    .forEach(module -> {
                        long number = collectorConjunctionMap.get(module);
                        if (number == 0) {
                            collectorConjunctionMap.put(module, (long) current);
                        }
                    });
        }
        long[] cycles = collectorConjunctionMap.values().stream()
                .mapToLong(e -> e)
                .toArray();
        return MathUtils.lcm(cycles);
    }

    private void resetModules() {
        modules.values()
                .forEach(CommunicationModule::reset);
    }

    private List<List<Conjunction>> getRelevantConjunctions() {
        List<List<CommunicationModule>> nestedList = new LinkedList<>();
        nestedList.add(List.of(Output.get()));
        int i = 0;
        while (!nestedList.get(i).isEmpty()) {
            List<CommunicationModule> current = nestedList.get(i);
            List<CommunicationModule> next = new ArrayList<>();
            nestedList.add(next);
            for (CommunicationModule module : current) {
                List<CommunicationModule> inputModuleList = module.getInputModules();
                if (inputModuleList.stream().allMatch(e -> e instanceof Conjunction)) {
                    next.addAll(inputModuleList);
                }
            }
            i++;
        }
        nestedList.removeFirst();
        nestedList.removeLast();
        return nestedList.stream()
                .map(e -> e.stream()
                        .map(Conjunction.class::cast)
                        .toList())
                .toList();
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
}
