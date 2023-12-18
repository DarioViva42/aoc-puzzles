package tk.vivas.adventofcode.year2023.day17;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class FactoryCityMapTest {

    @Test
    void findMinimalHeatLoss() {
        String input = AocUtils.readPuzzleInput();

        FactoryCityMap cityMap = new FactoryCityMap(input);

        int minimalHeatLoss = cityMap.findMinimalHeatLoss();

        System.out.println(cityMap);
        assertThat(minimalHeatLoss).isEqualTo(102);
    }
}