package vivas.tk.adventofcode.day11;

import java.util.Arrays;
import java.util.List;

public class KeepAwayGame {
    List<Monkey> monkeys;
    public KeepAwayGame(String input) {
        monkeys = Arrays
                .stream(input.split("(\\n|\\r\\n){2}"))
                .map(Monkey::parse)
                .toList();
        monkeys.forEach(monkey -> monkey.prepare(monkeys));
    }

    public int play(int rounds) {
        for (int i = 0; i < rounds; i++) {
            monkeys.forEach(Monkey::play);
        }
        return countMonkeyBusiness();
    }

    private int countMonkeyBusiness() {
        return monkeys.stream()
                .mapToInt(Monkey::getInspectionCount)
                .map(i -> -i)
                .sorted()
                .limit(2)
                .reduce((i, j) -> i * j)
                .orElseThrow();
    }
}
