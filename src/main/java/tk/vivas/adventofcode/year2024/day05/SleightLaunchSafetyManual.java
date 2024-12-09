package tk.vivas.adventofcode.year2024.day05;

import java.util.List;
import java.util.stream.IntStream;

class SleightLaunchSafetyManual {

    private final List<PageOrderingRule> pageOrderingRules;
    private final List<Update> updates;

    SleightLaunchSafetyManual(String input) {
        String[] split = input.split("\n\n");

        pageOrderingRules = split[0].lines()
                .map(PageOrderingRule::new)
                .toList();

        updates = split[1].lines()
                .map(Update::new)
                .toList();
    }


    long correctPagesScore() {
        // CorrectOrderChecker orderChecker = new CorrectOrderChecker(pageOrderingRules);

        return updates.stream()
                .filter(this::isOrdered)
                .mapToLong(Update::getMiddlePage)
                .sum();
    }

    private boolean isOrdered(Update update) {
        int[] pageNumbers = update.getPageNumbers();
        return IntStream.range(0, pageNumbers.length)
                .allMatch(i -> checkPagesBefore(i, pageNumbers) && checkPagesAfter(i, pageNumbers));
    }

    private boolean checkPagesBefore(int i, int[] pageNumbers) {
        int pageNumber = pageNumbers[i];
        List<Integer> allowedBefore = pageOrderingRules.stream()
                .filter(rule -> rule.hasAfter(pageNumber))
                .map(PageOrderingRule::getBefore)
                .toList();

        return IntStream.range(0, i)
                .allMatch(j -> allowedBefore.contains(pageNumbers[j]));
    }

    private boolean checkPagesAfter(int i, int[] pageNumbers) {
        int pageNumber = pageNumbers[i];
        List<Integer> allowedAfter = pageOrderingRules.stream()
                .filter(rule -> rule.hasBefore(pageNumber))
                .map(PageOrderingRule::getAfter)
                .toList();

        return IntStream.range(i + 1, pageNumbers.length)
                .allMatch(j -> allowedAfter.contains(pageNumbers[j]));
    }
}
