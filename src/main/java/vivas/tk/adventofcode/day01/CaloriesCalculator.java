package vivas.tk.adventofcode.day01;

import java.util.Comparator;
import java.util.List;

import static vivas.tk.adventofcode.GeneralUtils.splitBySeparator;

public class CaloriesCalculator {

    private final List<Elf> elves;

    public CaloriesCalculator(List<String> input) {
        elves = input.stream()
                .collect(splitBySeparator(String::isEmpty)).stream()
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
                .limit(3)
                .mapToInt(Elf::totalCalories)
                .sum();
    }
}
