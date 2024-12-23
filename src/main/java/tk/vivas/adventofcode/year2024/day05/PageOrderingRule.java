package tk.vivas.adventofcode.year2024.day05;

import org.apache.commons.lang3.ArrayUtils;

class PageOrderingRule {

    private final int before;
    private final int after;

    PageOrderingRule(String input) {
        String[] split = input.split("\\|");
        before = Integer.parseInt(split[0]);
        after = Integer.parseInt(split[1]);
    }

    boolean hasBefore(int before) {
        return this.before == before;
    }

    boolean hasAfter(int after) {
        return this.after == after;
    }

    int getBefore() {
        return before;
    }

    int getAfter() {
        return after;
    }

    boolean containsOnlyNumberFrom(int[] orderedPages) {
        return ArrayUtils.contains(orderedPages, before)
                && ArrayUtils.contains(orderedPages, after);
    }

    @Override
    public String toString() {
        return "%d|%d".formatted(before, after);
    }
}
