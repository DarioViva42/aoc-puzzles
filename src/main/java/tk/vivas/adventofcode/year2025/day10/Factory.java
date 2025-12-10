package tk.vivas.adventofcode.year2025.day10;

import java.util.List;

class Factory {

    private final List<Machine> machines;

    Factory(String input) {
        machines = input.lines()
                .map(Machine::new)
                .toList();
    }

    long fewestButtonPresses() {
        return machines.stream()
                .mapToLong(Machine::fewestButtonPresses)
                .sum();
    }
}
