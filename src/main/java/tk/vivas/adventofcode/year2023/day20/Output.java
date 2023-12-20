package tk.vivas.adventofcode.year2023.day20;

import java.util.List;

final class Output extends CommunicationModule {

    static Output instance = new Output();

    private Output() {
        super("output", new String[]{});
    }

    static Output get() {
        return instance;
    }

    @Override
    protected Pulse process(List<Pulse> incomingPulses) {
        return null;
    }
}
