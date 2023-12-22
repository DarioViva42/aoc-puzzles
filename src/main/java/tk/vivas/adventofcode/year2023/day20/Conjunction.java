package tk.vivas.adventofcode.year2023.day20;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static tk.vivas.adventofcode.year2023.day20.Pulse.HIGH_PULSE;
import static tk.vivas.adventofcode.year2023.day20.Pulse.LOW_PULSE;

final class Conjunction extends CommunicationModule {

    private final Map<String, Pulse> memory;
    private boolean seenAllHigh;

    public Conjunction(String name, String[] connectedModuleNames) {
        super(name, connectedModuleNames);
        memory = new HashMap<>();
        seenAllHigh = false;
    }

    @Override
    protected void announce(CommunicationModule module) {
        super.announce(module);
        memory.put(module.name(), LOW_PULSE);
    }

    @Override
    protected void receive(String name, Pulse pulse) {
        memory.put(name, pulse);
        super.receive(name, pulse);
    }

    @Override
    protected Pulse process(Pulse incomingPulse) {
        boolean memoryAllHigh = isMemoryAllHigh();
        if (memoryAllHigh) {
            seenAllHigh = true;
        }
        return memoryAllHigh ? LOW_PULSE : HIGH_PULSE;
    }

    private boolean isMemoryAllHigh() {
        return memory.values().stream()
                .allMatch(HIGH_PULSE::equals);
    }

    @Override
    void reset() {
        memory.keySet()
                .forEach(key -> memory.put(key, LOW_PULSE));
    }

    @Override
    public String toString() {
        String memoryString = memory.entrySet().stream()
                .map(entry -> "%s:%s".formatted(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(","));
        return "conjunction %s %s".formatted(name(), memoryString);
    }

    boolean changed() {
        boolean temp = seenAllHigh;
        seenAllHigh = false;
        return temp;
    }
}
