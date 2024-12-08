package tk.vivas.adventofcode.year2024.day05;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class CorrectOrderChecker {

    private final LinkedList<Integer> orderedPages;
    private final Map<Integer, Integer> pageLocations;

    CorrectOrderChecker(List<PageOrderingRule> rules) {
        orderedPages = new LinkedList<>();

        for (PageOrderingRule rule : rules) {
            incorporate(rule);
        }


        pageLocations = orderedPages.stream()
                .collect(Collectors.toMap(Function.identity(), orderedPages::indexOf));
    }

    private void incorporate(PageOrderingRule rule) {
        int before = rule.getBefore();
        int after = rule.getAfter();

        int beforeIndex = orderedPages.indexOf(before);
        int afterIndex = orderedPages.indexOf(after);

        if (beforeIndex == -1 && afterIndex == -1) {
            orderedPages.add(before);
            orderedPages.add(after);
        } else if (afterIndex == -1) {
            orderedPages.add(after);
        } else if (beforeIndex == -1) {
            orderedPages.push(before);
        } else if (beforeIndex > afterIndex) {
            orderedPages.set(beforeIndex, after);
            orderedPages.set(afterIndex, before);
        }
    }

    boolean isOrdered(Update update) {
        int index = -1;
        for (Integer pageNumber : update.getPageNumbers()) {
            Integer currentIndex = pageLocations.get(pageNumber);
            if (currentIndex < index) {
                return false;
            }
            index = currentIndex;
        }
        return true;
    }
}
