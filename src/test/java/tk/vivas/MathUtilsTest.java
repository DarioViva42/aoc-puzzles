package tk.vivas;

import org.junit.jupiter.api.Test;

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
}