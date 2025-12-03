package tk.vivas.adventofcode.year2025.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class BatteryBank {

    public static final int NUMBER_OF_DIGITS = 12;
    private final List<Integer> batteryJoltageValues;

    BatteryBank(String line) {
        batteryJoltageValues = line.chars()
                .mapToObj(Character::getNumericValue)
                .toList();
    }

    int getMaxJoltage() {
        int firstDigit = batteryJoltageValues.stream()
                .limit(batteryJoltageValues.size() - (2 - 1))
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();
        int secondDigit = batteryJoltageValues.stream()
                .dropWhile(digit -> digit != firstDigit)
                .skip(1)
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();

        return 10 * firstDigit + secondDigit;
    }

    public long getImprovedMaxJoltage() {
        int size = batteryJoltageValues.size();

        int firstDigit = batteryJoltageValues.stream()
                .limit(size - (NUMBER_OF_DIGITS - 1))
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();
        List<Integer> digits = new ArrayList<>(NUMBER_OF_DIGITS);
        digits.add(firstDigit);
        int digitPosition = getJoltageIndexFrom(0, firstDigit);
        for (int i = 0; i < NUMBER_OF_DIGITS - 1; i++) {
            int digit = batteryJoltageValues.stream()
                    .limit(size - (NUMBER_OF_DIGITS - i - 2))
                    .skip(digitPosition + 1)
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElseThrow();
            digits.add(digit);

            digitPosition = getJoltageIndexFrom(digitPosition, digit);
        }
        return IntStream.range(0, NUMBER_OF_DIGITS)
                .mapToLong(i -> (long) Math.pow(10, NUMBER_OF_DIGITS - 1 - i) * digits.get(i))
                .sum();
    }

    private int getJoltageIndexFrom(int from, int digit) {
        return from + batteryJoltageValues
                .subList(from, batteryJoltageValues.size())
                .indexOf(digit);
    }
}
