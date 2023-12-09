package tk.vivas.adventofcode.year2023.day08;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NetworkDocumentTest {

    @Test
    void requiredSteps() {
        String input = AocUtils.readPuzzleInput(1);

        NetworkDocument networkDocument = new NetworkDocument(input);

        assertThat(networkDocument.requiredSteps()).isEqualTo(6);
    }

    @Test
    void requiredGhostSteps() {
        String input = AocUtils.readPuzzleInput(2);

        NetworkDocument networkDocument = new NetworkDocument(input);

        assertThat(networkDocument.requiredGhostSteps()).isEqualTo(6);
    }
}