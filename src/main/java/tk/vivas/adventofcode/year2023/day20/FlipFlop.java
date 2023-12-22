package tk.vivas.adventofcode.year2023.day20;

import static tk.vivas.adventofcode.year2023.day20.Pulse.HIGH_PULSE;
import static tk.vivas.adventofcode.year2023.day20.Pulse.LOW_PULSE;

final class FlipFlop extends CommunicationModule {

    private boolean power;

    public FlipFlop(String name, String[] connectedModuleNames) {
        super(name, connectedModuleNames);
        power = false;
    }

    @Override
    protected Pulse process(Pulse incomingPulse) {
        if (LOW_PULSE != incomingPulse) {
            return null;
        }
        power = !power;
        return power ?  HIGH_PULSE : LOW_PULSE;
    }

    @Override
    void reset() {
        power = false;
    }

    @Override
    public String toString() {
        return "FlipFlop %s %s".formatted(name(), power ? "on" : "off");
    }
}
