package vivas.tk.adventofcode.day03;

import java.util.ArrayList;
import java.util.List;

class Rucksack {
    private final List<Character> compartmentA;
    private final List<Character> compartmentB;
    public Rucksack(String input) {
        compartmentA = input
                .substring(0, input.length() / 2)
                .chars().mapToObj(c -> (char) c)
                .toList();
        compartmentB = input.substring(input.length() / 2)
                .chars().mapToObj(c -> (char) c)
                .toList();
    }

    public List<Character> getAllBadges() {
        List<Character> temp = new ArrayList<>(compartmentA);
        temp.addAll(compartmentB);
        return temp;
    }

    public int calculatePriority() {
        List<Character> temp = new ArrayList<>(compartmentA);
        temp.retainAll(compartmentB);
        return (temp.get(0) + 20) % 58;
    }
}
