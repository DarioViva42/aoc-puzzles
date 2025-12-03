package tk.vivas.adventofcode.year2025.day03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BatteryBankTest {

    public static Stream<Arguments> getImprovedMaxJoltage_source() {
        return Stream.of(
                arguments("864444443222224", 864444443224L)
        );
    }

    @ParameterizedTest
    @MethodSource("getImprovedMaxJoltage_source")
    void getImprovedMaxJoltage(String input, long expected) {
        BatteryBank batteryBank = new BatteryBank(input);

        assertThat(batteryBank.getImprovedMaxJoltage()).isEqualTo(expected);
    }
}
