package tk.vivas.adventofcode.year2023.day20;

import java.util.List;

import static tk.vivas.adventofcode.year2023.day20.Pulse.HIGH_PULSE;
import static tk.vivas.adventofcode.year2023.day20.Pulse.LOW_PULSE;

final class FlipFlop extends CommunicationModule {

    private boolean power;

    public FlipFlop(String name, String[] connectedModuleNames) {
        super(name, connectedModuleNames);
        power = false;
    }

    @Override
    protected Pulse process(List<Pulse> incomingPulses) {
        incomingPulses = incomingPulses.stream()
                .filter(LOW_PULSE::equals)
                .toList();
        if (incomingPulses.isEmpty()) {
            return null;
        }
        if (incomingPulses.size() > 1) {
            throw new IllegalStateException("did not expect multiple signals per clock");
        }
        power = !power;
        return power ?  HIGH_PULSE : LOW_PULSE;
    }

    @Override
    public String toString() {
        return "FlipFlop %s %s".formatted(name(), power ? "on" : "off");
    }
}
