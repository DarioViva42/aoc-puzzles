package tk.vivas.adventofcode.year2025.day03;

import java.util.List;

class EscalatorPowerSource {

    private final List<BatteryBank> batteryBanks;

    EscalatorPowerSource(String input) {
        batteryBanks = input.lines()
                .map(BatteryBank::new)
                .toList();
    }

    public int totalJoltage() {
        return batteryBanks.stream()
                .mapToInt(BatteryBank::getMaxJoltage)
                .sum();
    }

    public long improvedTotalJoltage() {
        return batteryBanks.stream()
                .mapToLong(BatteryBank::getImprovedMaxJoltage)
                .sum();
    }
}
