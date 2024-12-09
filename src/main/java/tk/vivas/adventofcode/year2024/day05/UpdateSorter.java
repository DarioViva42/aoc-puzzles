package tk.vivas.adventofcode.year2024.day05;

import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

class UpdateSorter {

    private final int[] orderedPages;

    UpdateSorter(Update unsortedUpdate, List<PageOrderingRule> rules) {
        orderedPages = unsortedUpdate.getPageNumbers().clone();

        List<PageOrderingRule> significantRules = rules.stream()
                .filter(rule -> rule.containsOnlyNumberFrom(orderedPages))
                .toList();

        while (true) {
            if (!partialSort(significantRules)) {
                break;
            }
        }
    }

    private boolean partialSort(List<PageOrderingRule> rules) {
        boolean changed = false;
        for (PageOrderingRule rule : rules) {
            if (incorporate(rule)) {
                changed = true;
            }
        }
        return changed;
    }

    private boolean incorporate(PageOrderingRule rule) {
        int before = rule.getBefore();
        int after = rule.getAfter();

        int beforeIndex = ArrayUtils.indexOf(orderedPages, before);
        int afterIndex = ArrayUtils.indexOf(orderedPages, after);

        if (beforeIndex > afterIndex) {
            orderedPages[beforeIndex] = after;
            orderedPages[afterIndex] = before;
            return true;
        }
        return false;
    }

    Update getOrderedUpdate() {
        return new Update(orderedPages);
    }
}
