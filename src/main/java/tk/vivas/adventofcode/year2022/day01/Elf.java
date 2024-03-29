package tk.vivas.adventofcode.year2022.day01;

import java.util.stream.Stream;

class Elf {

    private final int totalCalories;

    public Elf(Stream<String> calories) {
        totalCalories = calories
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public int totalCalories() {
        return totalCalories;
    }
}
