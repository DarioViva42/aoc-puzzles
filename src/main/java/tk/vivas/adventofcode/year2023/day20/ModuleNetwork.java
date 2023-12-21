package tk.vivas.adventofcode.year2023.day20;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    long simulate() {
        IntStream.range(0, 1000)
                .forEach(i -> pressButton());
        return (long) lowCount * highCount;
    }

    private void pressButton() {
        String stateString = modules.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
        System.out.println("\nState:\n" + stateString);
        System.out.println("\nAction:");
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
