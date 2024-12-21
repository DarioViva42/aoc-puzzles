package tk.vivas.adventofcode.year2024.day15;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class LanternfishWarehouseTest {

    @Test
    void coordinatesSum() {
        String input = AocUtils.readPuzzleInput();

        LanternfishWarehouse warehouse = new LanternfishWarehouse(input);

        assertThat(warehouse.coordinatesSum()).isEqualTo(10092);
    }
}