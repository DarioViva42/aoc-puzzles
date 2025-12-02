package tk.vivas.adventofcode.year2025.day02;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class GiftShopDatabaseTest {

    @Test
    void sumOfInvalidIds() {
        String input = AocUtils.readPuzzleInput();

        GiftShopDatabase database = new GiftShopDatabase(input);

        assertThat(database.sumOfInvalidIds()).isEqualTo(1227775554);
    }
}
