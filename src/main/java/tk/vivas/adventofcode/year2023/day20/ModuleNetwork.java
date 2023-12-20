package tk.vivas.adventofcode.year2023.day20;

import java.util.Map;
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
        lowCount++;
        broadcaster.receive(Pulse.LOW_PULSE);
        do {
            modules.values()
                    .forEach(CommunicationModule::tick);
            modules.values().stream()
                    .map(CommunicationModule::send)
                    .forEach(pair -> {
                        highCount += pair.getRight();
                        lowCount += pair.getLeft();
                    });
        } while (containsReceivingModules());
    }

    private boolean containsReceivingModules() {
        return modules.values().stream()
                .anyMatch(CommunicationModule::isReceiving);
    }
}
