package vivas.tk.adventofcode.day03;

import junit.framework.TestCase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readFile;

public class RucksackFixerTest extends TestCase {

    public void testCalculateErrorPrioritySum() {
        List<String> input = readFile("/03.txt");
        RucksackFixer rucksackFixer = new RucksackFixer(input);
        assertThat(rucksackFixer.calculateErrorPrioritySum()).isEqualTo(157);
    }

    public void testCalculateGroupPrioritySum() {
        List<String> input = readFile("/03.txt");
        RucksackFixer rucksackFixer = new RucksackFixer(input);
        assertThat(rucksackFixer.calculateGroupPrioritySum()).isEqualTo(70);
    }
}