package tk.vivas.adventofcode.year2023.day20;

import java.util.List;

final class BroadCaster extends CommunicationModule {

    public BroadCaster(String[] moduleNames) {
        super("broadcaster", moduleNames);
    }

    @Override
    public Pulse process(List<Pulse> incomingPulses) {
        int size = incomingPulses.size();
        if (size > 1) {
            throw new IllegalStateException("did not expect %s signals per clock".formatted(size));
        }
        return (incomingPulses.isEmpty()) ? null : incomingPulses.getFirst();
    }

    @Override
    public String toString() {
        return "broadcaster";
    }
}
