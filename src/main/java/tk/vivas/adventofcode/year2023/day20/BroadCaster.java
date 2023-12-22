package tk.vivas.adventofcode.year2023.day20;

final class BroadCaster extends CommunicationModule {

    public BroadCaster(String[] moduleNames) {
        super("broadcaster", moduleNames);
    }

    @Override
    public Pulse process(Pulse incomingPulse) {
        return incomingPulse;
    }

    @Override
    void reset() {
    }

    @Override
    public String toString() {
        return "broadcaster";
    }
}
