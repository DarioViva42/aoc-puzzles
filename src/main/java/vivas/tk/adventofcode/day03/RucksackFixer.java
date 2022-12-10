package vivas.tk.adventofcode.day03;

import one.util.streamex.StreamEx;

import java.util.ArrayList;
import java.util.List;

class RucksackFixer {

    List<Rucksack> rucksacks;
    public RucksackFixer(String input) {
        rucksacks = input.lines()
                .map(Rucksack::new)
                .toList();
    }

    public int calculateErrorPrioritySum() {
        return rucksacks.stream()
                .mapToInt(Rucksack::calculatePriority)
                .sum();
    }

    public int calculateGroupPrioritySum() {
        return StreamEx.ofSubLists(rucksacks, 3)
                .mapToInt(this::calculateGroupPriority)
                .sum();
    }

    private int calculateGroupPriority(List<Rucksack> rucksacks) {
        List<Character> temp = new ArrayList<>(rucksacks.get(0).getAllBadges());
        temp.retainAll(rucksacks.get(1).getAllBadges());
        temp.retainAll(rucksacks.get(2).getAllBadges());
        return (temp.get(0) + 20) % 58;
    }
}
