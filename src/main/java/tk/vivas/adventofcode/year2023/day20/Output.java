package tk.vivas.adventofcode.year2023.day20;

final class Output extends CommunicationModule {

    static Output instance = new Output();

    private Output() {
        super("output", new String[]{});
    }

    static Output get() {
        return instance;
    }

    @Override
    protected Pulse process(Pulse incomingPulse) {
        return null;
    }

    @Override
    void reset() {
    }
}
