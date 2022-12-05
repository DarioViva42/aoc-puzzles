package vivas.tk.adventofcode.day05;

import junit.framework.TestCase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class CraneOperationTest extends TestCase {

    public void testOperateCrane_9000() {
        List<String> input = readFile("/05.txt");

        CraneOperation craneOperation = new CraneOperation(input);
        CargoCrane cargoCrane = new CrateMover9000();
        assertThat(craneOperation.operateCargoCrane(cargoCrane)).isEqualTo("CMZ");
    }

    public void testOperateCrane_9001() {
        List<String> input = readFile("/05.txt");

        CraneOperation craneOperation = new CraneOperation(input);
        CargoCrane cargoCrane = new CrateMover9001();
        assertThat(craneOperation.operateCargoCrane(cargoCrane)).isEqualTo("MCD");
    }
}