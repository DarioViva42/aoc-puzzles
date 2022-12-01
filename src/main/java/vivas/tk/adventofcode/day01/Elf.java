package vivas.tk.adventofcode.day01;

import java.util.ArrayList;
import java.util.List;

public class Elf {

    private final List<String> foodList;

    public Elf() {
        foodList = new ArrayList<>();
    }

    public void addCalories(String calories) {
        foodList.add(calories);
    }

    public int totalCalories() {
        return foodList.stream()
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);
    }
}
