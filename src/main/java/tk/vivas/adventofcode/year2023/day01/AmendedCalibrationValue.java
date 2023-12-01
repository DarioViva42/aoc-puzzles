package tk.vivas.adventofcode.year2023.day01;

import java.util.List;

class AmendedCalibrationValue {

    private final List<Integer> digits;
    private final List<Integer> allDigits;

    private static final List<String> WRITTEN_DIGITS = List.of(
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    AmendedCalibrationValue(String amendedCalibrationValue) {
        digits = extractDigits(amendedCalibrationValue);

        for (int i = 0; i < 9; i++) {
            String writtenDigit = WRITTEN_DIGITS.get(i);
            amendedCalibrationValue = amendedCalibrationValue
                    .replace(writtenDigit, writtenDigit + (i + 1) + writtenDigit);
        }

        allDigits = extractDigits(amendedCalibrationValue);
    }

    private List<Integer> extractDigits(String amendedCalibrationValue) {
        return amendedCalibrationValue.chars()
                .mapToObj(e -> (char) e)
                .map(Object::toString)
                .filter(character -> character.matches("\\d"))
                .map(Integer::valueOf)
                .toList();
    }

    int recover() {
        return 10 * digits.getFirst() + digits.getLast();
    }

    int fullyRecover() {
        return 10 * allDigits.getFirst() + allDigits.getLast();
    }
}
