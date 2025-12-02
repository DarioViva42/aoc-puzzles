package tk.vivas.adventofcode.year2025.day02;

import java.util.stream.LongStream;

class IdRange {

    private final long start;
    private final long end;

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
        long amountOfDigits = amountOfDigits(id);
        if (isOdd(amountOfDigits)) {
            return false;
        }

        long halfPoint = (long) Math.pow(10.0, amountOfDigits / 2);
        //     first-half        second-half
        return id / halfPoint == id % halfPoint;
    }

    private boolean isOdd(long amountOfDigits) {
        return amountOfDigits % 2 == 1;
    }

    private long amountOfDigits(long id) {
        return (long) Math.floor(Math.log10(id)) + 1;
    }
}
