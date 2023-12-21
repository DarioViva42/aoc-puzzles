package tk.vivas.adventofcode.year2023.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static tk.vivas.adventofcode.year2023.day20.Pulse.HIGH_PULSE;
import static tk.vivas.adventofcode.year2023.day20.Pulse.LOW_PULSE;

final class Conjunction extends CommunicationModule {

    private final Map<String, Pulse> memory;

    public Conjunction(String name, String[] connectedModuleNames) {
        super(name, connectedModuleNames);
        memory = new HashMap<>();
    }

    @Override
    protected void announce(String name) {
        memory.put(name, LOW_PULSE);
    }

    @Override
    protected void receive(String name, Pulse pulse) {
        memory.put(name, pulse);
        super.receive(name, pulse);
    }

    @Override
    protected Pulse process(Pulse incomingPulse) {
        boolean memoryAllHigh = memory.values().stream()
                .allMatch(HIGH_PULSE::equals);
        return memoryAllHigh ? LOW_PULSE : HIGH_PULSE;
    }

    @Override
    public String toString() {
        String memoryString = memory.entrySet().stream()
                .map(entry -> "%s:%s".formatted(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(","));
        return "conjunction %s %s".formatted(name(), memoryString);
    }
}
