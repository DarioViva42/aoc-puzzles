package vivas.tk.adventofcode.day01;

import java.util.List;

public class Elf {

    private final List<String> foodList;

    public Elf(List<String> calories) {
        foodList = calories;
    }

    public int totalCalories() {
        return foodList.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
