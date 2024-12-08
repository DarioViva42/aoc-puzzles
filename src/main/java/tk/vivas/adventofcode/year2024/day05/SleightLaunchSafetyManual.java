package tk.vivas.adventofcode.year2024.day05;

import java.util.List;

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
        for (int i = 0; i < pageNumbers.length; i++) {
            int currentPageNumber = pageNumbers[i];
            List<Integer> allowedBefore = pageOrderingRules.stream()
                    .filter(rule -> rule.hasAfter(currentPageNumber))
                    .map(PageOrderingRule::getBefore)
                    .toList();

            List<Integer> allowedAfter = pageOrderingRules.stream()
                    .filter(rule -> rule.hasBefore(currentPageNumber))
                    .map(PageOrderingRule::getAfter)
                    .toList();

            for (int j = 0; j < i; j++) {
                if (!allowedBefore.contains(pageNumbers[j])) {
                    return false;
                }
            }

            for (int j = i + 1; j < pageNumbers.length; j++) {
                if (!allowedAfter.contains(pageNumbers[j])) {
                    return false;
                }
            }
        }
        return true;
    }


}
