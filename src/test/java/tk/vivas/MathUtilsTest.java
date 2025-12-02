package tk.vivas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MathUtilsTest {

    @Test
    void lcm() {
        long lcm = MathUtils.lcm(6, 8, 15);

        assertThat(lcm).isEqualTo(120);
    }

    @Test
    void lcm_twoNumbers() {
        long lcm = MathUtils.lcm(12, 18);

        assertThat(lcm).isEqualTo(36);
    }

    private static Stream<Arguments> factors_source() {
        return Stream.of(
                arguments(0, Set.of()),
                arguments(1, Set.of(1)),
                arguments(20, Set.of(1, 2, 4, 5, 10, 20)),
                arguments(24, Set.of(1, 2, 3, 4, 6, 8, 12, 24)),
                arguments(97, Set.of(1, 97)),
                arguments(99, Set.of(1, 3, 9, 11, 33, 99)),
                arguments(100, Set.of(1, 2, 4, 5, 10, 20, 25, 50, 100))
        );
    }

    @ParameterizedTest(name = "factors of {0} should be {1}")
    @MethodSource("factors_source")
    void factors(int number, Set<Integer> expected) {
        Set<Integer> factors = MathUtils.factors(number);

        assertThat(factors).containsExactlyInAnyOrderElementsOf(expected);
    }

    @ParameterizedTest(name = "{0} || {1} == {2}")
    @CsvSource(value = {
            "4,2,42",
            "10,45,1045",
            "627,91,62791",
            "51,203,51203"
    })
    void concatenate(int a, int b, int expected) {
        assertThat(MathUtils.concatenate(a, b))
                .isEqualTo(expected);
    }
}
