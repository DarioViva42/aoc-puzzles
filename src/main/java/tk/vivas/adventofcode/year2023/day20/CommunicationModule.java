package tk.vivas.adventofcode.year2023.day20;

import java.util.*;

abstract sealed class CommunicationModule permits BroadCaster, FlipFlop, Conjunction, Output {

    private final String name;
    private final String[] moduleNames;
    private final List<Pulse> receivedPulses;
    private List<CommunicationModule> outputModules;
    private final List<CommunicationModule> inputModules;

    protected CommunicationModule(String name, String[] moduleNames) {
        this.name = name;
        this.moduleNames = moduleNames;

        receivedPulses = new LinkedList<>();
        inputModules = new ArrayList<>();
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
        outputModules = Arrays.stream(moduleNames)
                .map(moduleMap::get)
                .map(module -> module == null ? Output.get() : module)
                .toList();
        outputModules
                .forEach(module -> module.announce(this));
    }

    protected void announce(CommunicationModule module) {
        inputModules.add(module);
    }

    List<CommunicationModule> getInputModules() {
        return inputModules;
    }

    String name() {
        return name;
    }

    Pulse send() {
        Pulse receivedPulse = receivedPulses.removeFirst();
        Pulse sendingPulse = process(receivedPulse);
        if (sendingPulse == null) {
            return null;
        }
        outputModules.forEach(module -> module.receive(name, sendingPulse));
        return sendingPulse;
    }

    protected void receive(String name, Pulse pulse) {
        receivedPulses.add(pulse);
    }

    List<CommunicationModule> getOutputModules() {
        return outputModules;
    }

    protected abstract Pulse process(Pulse incomingPulse);

    abstract void reset();
}
