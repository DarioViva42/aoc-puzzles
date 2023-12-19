package tk.vivas.adventofcode.year2023.day19;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class MachinePartsOrganizerTest {

    @Test
    void acceptedPropertiesSum() {
        String input = AocUtils.readPuzzleInput();

        MachinePartsOrganizer organizer = new MachinePartsOrganizer(input);

        assertThat(organizer.acceptedPropertiesSum()).isEqualTo(19114);
    }
}
