package vivas.tk.adventofcode.day05;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readPuzzleInput;

class RearrangementProcedureTest {

    @Test
    void operateCrane_9000() {
        String input = readPuzzleInput();
        RearrangementProcedure rearrangementProcedure = new RearrangementProcedure(input);

        CargoCrane cargoCrane = new CrateMover9000();

        assertThat(rearrangementProcedure.operateCargoCrane(cargoCrane))
                .isEqualTo("CMZ");
    }

    @Test
    void operateCrane_9001() {
        String input = readPuzzleInput();
        RearrangementProcedure rearrangementProcedure = new RearrangementProcedure(input);

        CargoCrane cargoCrane = new CrateMover9001();

        assertThat(rearrangementProcedure.operateCargoCrane(cargoCrane))
                .isEqualTo("MCD");
    }
}