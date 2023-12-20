package tk.vivas.adventofcode.year2023.day20;

enum Pulse {
    LOW_PULSE("low"), 
    HIGH_PULSE("high");

    private final String displayName;

    Pulse(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
