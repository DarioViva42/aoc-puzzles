package tk.vivas.adventofcode.year2025.day02;

import tk.vivas.MathUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.LongStream;

import static java.util.function.Predicate.not;

class IdRange {

    private final long start;
    private final long end;

    static final Map<Integer, Set<Integer>> factorsCache = new HashMap<>();

    IdRange(String input) {
        String[] split = input.split("-");
        start = Long.parseLong(split[0]);
        end = Long.parseLong(split[1]);
    }

    LongStream invalidIds() {
        return LongStream.rangeClosed(start, end)
                .filter(this::isInvalidId);
    }

    private boolean isInvalidId(long id) {
        int amountOfDigits = amountOfDigits(id);
        if (isOdd(amountOfDigits)) {
            return false;
        }

        int halfPoint = powOf10(amountOfDigits / 2);
        //     first-half        second-half
        return id / halfPoint == id % halfPoint;
    }

    LongStream allInvalidIds() {
        return LongStream.rangeClosed(start, end)
                .filter(this::isTrulyInvalidId);
    }

    private boolean isTrulyInvalidId(long id) {
        int amountOfDigits = amountOfDigits(id);
        Set<Integer> factors = factorsCache.computeIfAbsent(amountOfDigits, MathUtils::factors);
        return factors.stream()
                .filter(not(factor -> factor.equals(amountOfDigits)))
                .anyMatch(patternLength -> hasRepeatingPattern(id, patternLength, amountOfDigits));
    }

    private boolean hasRepeatingPattern(long id, int patternLength, int idLength) {
        int dividingPoint = powOf10(idLength - patternLength);
        long pattern = id / dividingPoint;

        long temp = id - (dividingPoint * pattern);
        for (int digitIndex = idLength - 2 * patternLength; digitIndex >= 0; digitIndex -= patternLength) {
            int dp = powOf10(digitIndex);
            long repeatingPattern = temp / dp;
            if (repeatingPattern != pattern) {
                return false;
            }
            temp = temp - (dp * repeatingPattern);
        }
        return true;
    }

    private static int powOf10(int i) {
        return (int) Math.pow(10, i);
    }

    private boolean isOdd(long amountOfDigits) {
        return amountOfDigits % 2 == 1;
    }

    private int amountOfDigits(long id) {
        return (int) Math.floor(Math.log10(id)) + 1;
    }
}
