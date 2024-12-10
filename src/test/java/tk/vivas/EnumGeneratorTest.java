package tk.vivas;

import org.junit.jupiter.api.RepeatedTest;

import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class EnumGeneratorTest {

    @RepeatedTest(100)
    void generate() {
        var generator = new EnumGenerator<>(Month.class, 3, Month.APRIL, Month.JULY, Month.AUGUST, Month.DECEMBER);
        assertThat(generator.generate()).containsExactlyInAnyOrder(
                new Month[]{Month.APRIL, Month.APRIL, Month.APRIL},
                new Month[]{Month.APRIL, Month.APRIL, Month.JULY},
                new Month[]{Month.APRIL, Month.APRIL, Month.AUGUST},
                new Month[]{Month.APRIL, Month.APRIL, Month.DECEMBER},
                new Month[]{Month.APRIL, Month.JULY, Month.APRIL},
                new Month[]{Month.APRIL, Month.JULY, Month.JULY},
                new Month[]{Month.APRIL, Month.JULY, Month.AUGUST},
                new Month[]{Month.APRIL, Month.JULY, Month.DECEMBER},
                new Month[]{Month.APRIL, Month.AUGUST, Month.APRIL},
                new Month[]{Month.APRIL, Month.AUGUST, Month.JULY},
                new Month[]{Month.APRIL, Month.AUGUST, Month.AUGUST},
                new Month[]{Month.APRIL, Month.AUGUST, Month.DECEMBER},
                new Month[]{Month.APRIL, Month.DECEMBER, Month.APRIL},
                new Month[]{Month.APRIL, Month.DECEMBER, Month.JULY},
                new Month[]{Month.APRIL, Month.DECEMBER, Month.AUGUST},
                new Month[]{Month.APRIL, Month.DECEMBER, Month.DECEMBER},

                new Month[]{Month.JULY, Month.APRIL, Month.APRIL},
                new Month[]{Month.JULY, Month.APRIL, Month.JULY},
                new Month[]{Month.JULY, Month.APRIL, Month.AUGUST},
                new Month[]{Month.JULY, Month.APRIL, Month.DECEMBER},
                new Month[]{Month.JULY, Month.JULY, Month.APRIL},
                new Month[]{Month.JULY, Month.JULY, Month.JULY},
                new Month[]{Month.JULY, Month.JULY, Month.AUGUST},
                new Month[]{Month.JULY, Month.JULY, Month.DECEMBER},
                new Month[]{Month.JULY, Month.AUGUST, Month.APRIL},
                new Month[]{Month.JULY, Month.AUGUST, Month.JULY},
                new Month[]{Month.JULY, Month.AUGUST, Month.AUGUST},
                new Month[]{Month.JULY, Month.AUGUST, Month.DECEMBER},
                new Month[]{Month.JULY, Month.DECEMBER, Month.APRIL},
                new Month[]{Month.JULY, Month.DECEMBER, Month.JULY},
                new Month[]{Month.JULY, Month.DECEMBER, Month.AUGUST},
                new Month[]{Month.JULY, Month.DECEMBER, Month.DECEMBER},

                new Month[]{Month.AUGUST, Month.APRIL, Month.APRIL},
                new Month[]{Month.AUGUST, Month.APRIL, Month.JULY},
                new Month[]{Month.AUGUST, Month.APRIL, Month.AUGUST},
                new Month[]{Month.AUGUST, Month.APRIL, Month.DECEMBER},
                new Month[]{Month.AUGUST, Month.JULY, Month.APRIL},
                new Month[]{Month.AUGUST, Month.JULY, Month.JULY},
                new Month[]{Month.AUGUST, Month.JULY, Month.AUGUST},
                new Month[]{Month.AUGUST, Month.JULY, Month.DECEMBER},
                new Month[]{Month.AUGUST, Month.AUGUST, Month.APRIL},
                new Month[]{Month.AUGUST, Month.AUGUST, Month.JULY},
                new Month[]{Month.AUGUST, Month.AUGUST, Month.AUGUST},
                new Month[]{Month.AUGUST, Month.AUGUST, Month.DECEMBER},
                new Month[]{Month.AUGUST, Month.DECEMBER, Month.APRIL},
                new Month[]{Month.AUGUST, Month.DECEMBER, Month.JULY},
                new Month[]{Month.AUGUST, Month.DECEMBER, Month.AUGUST},
                new Month[]{Month.AUGUST, Month.DECEMBER, Month.DECEMBER},

                new Month[]{Month.DECEMBER, Month.APRIL, Month.APRIL},
                new Month[]{Month.DECEMBER, Month.APRIL, Month.JULY},
                new Month[]{Month.DECEMBER, Month.APRIL, Month.AUGUST},
                new Month[]{Month.DECEMBER, Month.APRIL, Month.DECEMBER},
                new Month[]{Month.DECEMBER, Month.JULY, Month.APRIL},
                new Month[]{Month.DECEMBER, Month.JULY, Month.JULY},
                new Month[]{Month.DECEMBER, Month.JULY, Month.AUGUST},
                new Month[]{Month.DECEMBER, Month.JULY, Month.DECEMBER},
                new Month[]{Month.DECEMBER, Month.AUGUST, Month.APRIL},
                new Month[]{Month.DECEMBER, Month.AUGUST, Month.JULY},
                new Month[]{Month.DECEMBER, Month.AUGUST, Month.AUGUST},
                new Month[]{Month.DECEMBER, Month.AUGUST, Month.DECEMBER},
                new Month[]{Month.DECEMBER, Month.DECEMBER, Month.APRIL},
                new Month[]{Month.DECEMBER, Month.DECEMBER, Month.JULY},
                new Month[]{Month.DECEMBER, Month.DECEMBER, Month.AUGUST},
                new Month[]{Month.DECEMBER, Month.DECEMBER, Month.DECEMBER}
        );
    }
}