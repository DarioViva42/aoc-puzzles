package tk.vivas.adventofcode.year2023.day20;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

abstract sealed class CommunicationModule permits BroadCaster, FlipFlop, Conjunction, Output {

    private final String name;
    private final String[] moduleNames;
    private List<Pulse> receivedPulses;
    private List<CommunicationModule> connectedModules;
    private Pulse commitedPulse;

    protected CommunicationModule(String name, String[] moduleNames) {
        this.name = name;
        this.moduleNames = moduleNames;

        receivedPulses = new ArrayList<>();
    }

    static CommunicationModule from(String input) {
        String[] split = input.split(" -> ");
        String fullName = split[0];
        String[] connectedModuleNames = split[1].split(", ");
        return switch (fullName.charAt(0)) {
            case 'b' -> new BroadCaster(connectedModuleNames);
            case '&' -> new Conjunction(fullName.substring(1), connectedModuleNames);
            case '%' -> new FlipFlop(fullName.substring(1), connectedModuleNames);
            default -> throw new IllegalStateException("Unexpected value: " + fullName);
        };
    }

    void init(Map<String, CommunicationModule> moduleMap) {
        connectedModules = Arrays.stream(moduleNames)
                .map(moduleMap::get)
                .map(module -> module == null ? Output.get() : module)
                .toList();
        connectedModules
                .forEach(module -> module.announce(name));
    }

    protected void announce(String name) {
    }

    String name() {
        return name;
    }

    Pair<Integer, Integer> send() {
        if (commitedPulse != null) {
            connectedModules.forEach(module -> module.receive(name, commitedPulse));
        }
        int amount = moduleNames.length;
        Pair<Integer, Integer> sent = switch (commitedPulse) {
            case LOW_PULSE -> Pair.of(amount, 0);
            case HIGH_PULSE -> Pair.of(0, amount);
            case null -> Pair.of(0, 0);
        };
        commitedPulse = null;
        return sent;
    }

    protected void receive(String name, Pulse pulse) {
        System.out.printf("%s -%s-> %s%n", name, pulse, this.name);
        receivedPulses.add(pulse);
    }

    void receive(Pulse pulse) {
        receivedPulses.add(pulse);
    }

    void tick() {
        commitedPulse = process(receivedPulses);
        receivedPulses = new ArrayList<>();
    }

    protected abstract Pulse process(List<Pulse> incomingPulses);

    boolean isReceiving() {
        return !receivedPulses.isEmpty();
    }
}
