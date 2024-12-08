package tk.vivas.adventofcode.year2024.day05;

import java.util.Arrays;

class Update {

    private final int[] pageNumbers;

    Update(String input) {
        pageNumbers = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public int[] getPageNumbers() {
        return pageNumbers;
    }

    int getMiddlePage() {
        int middlePageIndex = pageNumbers.length / 2;
        return pageNumbers[middlePageIndex];
    }
}
