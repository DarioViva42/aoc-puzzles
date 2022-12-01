package vivas.tk.adventofcode.day01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CaloriesCalculator {

    private final List<Elf> elves;

    public CaloriesCalculator(List<String> input) {
        elves = new ArrayList<>();

        Elf elf = new Elf();
        elves.add(elf);

        for (String line : input) {
            if (line.isEmpty()) {
                elf = new Elf();
                elves.add(elf);
            } else {
                elf.addCalories(line);
            }
        }
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
                .map(Elf::totalCalories)
                .reduce(0, Integer::sum);
    }
}
