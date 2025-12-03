package tk.vivas.adventofcode.year2025.day03;

import java.util.List;

class BatteryBank {

    private final List<Integer> batteryJoltageValues;

    BatteryBank(String line) {
        batteryJoltageValues = line.chars()
                .mapToObj(Character::getNumericValue)
                .toList();
    }

    int getMaxJoltage() {
        int firstDigit = batteryJoltageValues.stream()
                .limit(batteryJoltageValues.size() - 1)
                .mapToInt(Integer::intValue).max()
                .orElseThrow();
        int secondDigit = batteryJoltageValues.stream()
                .dropWhile(digit -> digit != firstDigit)
                .skip(1)
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();

        return 10 * firstDigit + secondDigit;
    }
}
