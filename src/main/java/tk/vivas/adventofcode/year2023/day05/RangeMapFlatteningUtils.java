package tk.vivas.adventofcode.year2023.day05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RangeMapFlatteningUtils {
    private RangeMapFlatteningUtils() {
    }

    static RangeMap flatten(RangeMap a, RangeMap b) {
        List<RangeMapEntry> flattenedEntries = new ArrayList<>();

        List<RangeMapEntry> aSorted = a.getMapEntries().stream()
                .sorted(Comparator.comparing(RangeMapEntry::getDestinationRangeStart))
                .toList();
        List<RangeMapEntry> bSorted = b.getMapEntries().stream()
                .sorted(Comparator.comparing(RangeMapEntry::getSourceRangeStart))
                .toList();

        int bIndex = 0;
        boolean bOutOfBounds = false;
        for (RangeMapEntry aEntry : aSorted) {
            if (bOutOfBounds) {
                flattenedEntries.add(aEntry);
                continue;
            }
            RangeMapEntry bEntry = bSorted.get(bIndex);
            long bSourceStart = bEntry.getSourceRangeStart();
            long aDestinationEnd = aEntry.getDestinationRangeEnd();
            if (bSourceStart > aDestinationEnd) {
                flattenedEntries.add(aEntry);
                continue;
            }
            long aDestinationStart = aEntry.getDestinationRangeStart();
            long aSourceStart = aEntry.getSourceRangeStart();

            while (bEntry.getSourceRangeEnd() < aDestinationStart) {
                bIndex += 1;
                if (bIndex == bSorted.size()) {
                    flattenedEntries.add(aEntry);
                    bOutOfBounds = true;
                    break;
                }
                bEntry = bSorted.get(bIndex);
            }
            if (bOutOfBounds) {
                continue;
            }

            long shift = 0;
            while (bSourceStart <= aDestinationEnd) {
                if (aDestinationStart + shift < bSourceStart) {
                    flattenedEntries.add(new RangeMapEntry(
                            aDestinationStart + shift,
                            aSourceStart + shift,
                            bSourceStart - aDestinationStart - shift
                    ));
                }

                long bSourceEnd = bEntry.getSourceRangeEnd();
                long maxStart = Math.max(aDestinationStart + shift, bSourceStart);
                long minEnd = Math.min(aDestinationEnd, bSourceEnd);

                flattenedEntries.add(new RangeMapEntry(
                        maxStart + bEntry.getShift(),
                        maxStart - aEntry.getShift(),
                        minEnd - maxStart + 1
                ));

                shift = bSourceEnd - aDestinationStart + 1;
                bIndex += 1;
                if (bIndex == bSorted.size()) {
                    break;
                }
                bEntry = bSorted.get(bIndex);
                bSourceStart = bEntry.getSourceRangeStart();
            }

            bEntry = bSorted.get(--bIndex);

            long bSourceEnd = bEntry.getSourceRangeEnd();
            if (bSourceEnd < aDestinationEnd) {
                flattenedEntries.add(new RangeMapEntry(
                        aDestinationStart + shift,
                        aSourceStart + shift,
                        aDestinationEnd - bSourceEnd
                ));
            }
        }
        return new RangeMap(flattenedEntries);
    }
}
