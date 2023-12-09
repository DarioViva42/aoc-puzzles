package tk.vivas.adventofcode.year2023.day09;

import java.util.List;

class OASIS {

    private final List<Sequence> sequenceList;

    OASIS(String input) {
        sequenceList = input.lines()
                .map(Sequence::of)
                .toList();
    }


    long sumUpExtrapolatedValues() {
        return sequenceList.stream()
                .mapToLong(Sequence::findExtrapolatedValue)
                .sum();
    }
}
