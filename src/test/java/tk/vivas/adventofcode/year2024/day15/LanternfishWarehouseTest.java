package tk.vivas.adventofcode.year2024.day15;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class LanternfishWarehouseTest {

    @Test
    void coordinatesSum_small() {
        String input = AocUtils.readPuzzleInput(1);

        LanternfishWarehouse warehouse = new LanternfishWarehouse(input);

        assertThat(warehouse.coordinatesSum()).isEqualTo(2028);
    }

    @Test
    void coordinatesSum() {
        String input = AocUtils.readPuzzleInput(2);

        LanternfishWarehouse warehouse = new LanternfishWarehouse(input);

        assertThat(warehouse.coordinatesSum()).isEqualTo(10092);
    }

    @Test
    void coordinatesSumInLargeWarehouse() {
        String input = AocUtils.readPuzzleInput(2);

        LanternfishWarehouse warehouse = new LanternfishWarehouse(input);

        assertThat(warehouse.coordinatesSumInLargeWarehouse()).isEqualTo(9021);
    }
}