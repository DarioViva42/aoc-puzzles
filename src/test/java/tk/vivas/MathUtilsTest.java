package tk.vivas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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