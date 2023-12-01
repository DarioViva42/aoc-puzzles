package tk.vivas.adventofcode.day01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class CaloriesCalculator {

    private final List<Elf> elves;

    public CaloriesCalculator(String input) {
        elves = Arrays.stream(input.split("(\\n|\\r\\n){2}"))
                .map(String::lines)
                .map(Elf::new)
                .toList();
    }

    public int calculateMaxCalories() {
        return elves.stream()
                .max(Comparator.comparingInt(Elf::totalCalories))
                .map(Elf::totalCalories)
                .orElse(0);
    }

    public int calculateMaxCaloriesTopThree() {
        return elves.stream()
                .sorted(Comparator.comparingInt(Elf::totalCalories).reversed())
                .mapToInt(Elf::totalCalories)
                .limit(3)
                .sum();
    }
}
