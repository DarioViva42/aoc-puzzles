package tk.vivas.adventofcode.year2023.day12;

import java.util.List;

class HotSpringsRecords {

    private final List<HotSpringsRow> rows;

    public HotSpringsRecords(String input) {
        rows = input.lines()
                .map(HotSpringsRow::from)
                .toList();
    }

    long countArrangements() {
        return rows.stream()
                .mapToLong(HotSpringsRow::countArrangements)
                .sum();
    }

    public long countUnfoldedArrangements() {
        return rows.stream()
                .map(HotSpringsRow::unfold)
                .mapToLong(HotSpringsRow::countArrangements)
                .sum();
    }
}
