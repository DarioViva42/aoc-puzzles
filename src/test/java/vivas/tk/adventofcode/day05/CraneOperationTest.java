package vivas.tk.adventofcode.day05;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readFile;

class CraneOperationTest {

    @Test
    void operateCrane_9000() {
        List<String> input = readFile("/05.txt");

        CraneOperation craneOperation = new CraneOperation(input);
        CargoCrane cargoCrane = new CrateMover9000();
        assertThat(craneOperation.operateCargoCrane(cargoCrane)).isEqualTo("CMZ");
    }

    @Test
    void operateCrane_9001() {
        List<String> input = readFile("/05.txt");

        CraneOperation craneOperation = new CraneOperation(input);
        CargoCrane cargoCrane = new CrateMover9001();
        assertThat(craneOperation.operateCargoCrane(cargoCrane)).isEqualTo("MCD");
    }
}