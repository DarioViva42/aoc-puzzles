package tk.vivas.adventofcode.year2025.day04;

import org.junit.jupiter.api.Test;
import tk.vivas.adventofcode.AocUtils;

import static org.assertj.core.api.Assertions.assertThat;

class PrintingDepartmentTest {

    @Test
    void accessibleRollsOfPaper() {
        String input = AocUtils.readPuzzleInput();

        PrintingDepartment department = new PrintingDepartment(input);

        assertThat(department.accessibleRollsOfPaper()).isEqualTo(13);
    }

    @Test
    void removableRollsOfPaper() {
        String input = AocUtils.readPuzzleInput();

        PrintingDepartment department = new PrintingDepartment(input);

        assertThat(department.removableRollsOfPaper()).isEqualTo(43);
    }
}
