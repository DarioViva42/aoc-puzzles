package tk.vivas.adventofcode.year2022.day11;

import java.util.Arrays;
import java.util.List;

class KeepAwayGame {
    private final List<Monkey> monkeys;
    private final int groupDivisionNumber;

    public KeepAwayGame(String input) {
        monkeys = Arrays
                .stream(input.split("(\\n|\\r\\n){2}"))
                .map(Monkey::parse)
                .toList();
        monkeys.forEach(monkey -> monkey.prepare(monkeys));
        groupDivisionNumber = monkeys.stream()
                .mapToInt(Monkey::getDivisionNumber)
                .reduce((i, j) -> i * j).orElseThrow();
    }

    public long play(int rounds) {
        for (int i = 0; i < rounds; i++) {
            monkeys.forEach(monkey -> monkey.throwItems(x -> x / 3));
        }
        return countMonkeyBusiness();
    }

    public long playWithoutRelief(int rounds) {
        for (int i = 0; i < rounds; i++) {
            monkeys.forEach(monkey -> monkey.throwItems(x -> x % groupDivisionNumber));
        }
        return countMonkeyBusiness();
    }

    private long countMonkeyBusiness() {
        return monkeys.stream()
                .mapToLong(Monkey::getInspectionCount)
                .map(i -> -i)
                .sorted()
                .limit(2)
                .reduce((i, j) -> i * j)
                .orElseThrow();
    }
}
