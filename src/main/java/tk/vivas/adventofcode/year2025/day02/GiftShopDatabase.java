package tk.vivas.adventofcode.year2025.day02;

import java.util.Arrays;
import java.util.List;

class GiftShopDatabase {

    private final List<IdRange> idRanges;

    GiftShopDatabase(String input) {
        String[] split = input.replace("\n", "").split(",");
        idRanges = Arrays.stream(split)
                .map(IdRange::new)
                .toList();
    }

    long sumOfInvalidIds() {
        return idRanges.stream()
                .flatMapToLong(IdRange::invalidIds)
                .sum();
    }

    long sumOfAllInvalidIds() {
        return idRanges.stream()
                .flatMapToLong(IdRange::allInvalidIds)
                .sum();
    }
}
