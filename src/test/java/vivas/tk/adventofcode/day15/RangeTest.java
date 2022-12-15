package vivas.tk.adventofcode.day15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.day15.TouchType.*;

class RangeTest {

    @Test
    void toTouchType_containing() {
        Range rangeOne = new Range(3, 10);
        Range rangeTwo = new Range(5, 8);

        assertThat(rangeOne.toTouchType(rangeTwo)).isEqualTo(CONTAINS);
        assertThat(rangeTwo.toTouchType(rangeOne)).isEqualTo(CONTAINED);
    }

    @Test
    void toTouchType_touching() {
        Range rangeOne = new Range(1, 3);
        Range rangeTwo = new Range(4, 9);

        assertThat(rangeOne.toTouchType(rangeTwo)).isEqualTo(LEFT);
        assertThat(rangeTwo.toTouchType(rangeOne)).isEqualTo(RIGHT);
    }

    @Test
    void toTouchType_overlapping() {
        Range rangeOne = new Range(1, 7);
        Range rangeTwo = new Range(4, 9);

        assertThat(rangeOne.toTouchType(rangeTwo)).isEqualTo(LEFT);
        assertThat(rangeTwo.toTouchType(rangeOne)).isEqualTo(RIGHT);
    }

    @Test
    void toTouchType_disjoint() {
        Range rangeOne = new Range(0, 2);
        Range rangeTwo = new Range(6, 11);

        assertThat(rangeOne.toTouchType(rangeTwo)).isEqualTo(NOT_TOUCHING);
        assertThat(rangeTwo.toTouchType(rangeOne)).isEqualTo(NOT_TOUCHING);
    }

    @Test
    void combine_containing() {
        Range rangeOne = new Range(3, 10);
        Range rangeTwo = new Range(5, 8);

        assertThat(rangeOne.combine(rangeTwo)).isEqualTo(rangeOne);
        assertThat(rangeTwo.combine(rangeOne)).isEqualTo(rangeOne);
    }

    @Test
    void combine_touching() {
        Range rangeOne = new Range(1, 3);
        Range rangeTwo = new Range(4, 9);

        Range combined = new Range(1, 9);

        assertThat(rangeOne.combine(rangeTwo)).isEqualTo(combined);
        assertThat(rangeTwo.combine(rangeOne)).isEqualTo(combined);
    }

    @Test
    void combine_overlapping() {
        Range rangeOne = new Range(1, 7);
        Range rangeTwo = new Range(4, 9);

        Range combined = new Range(1, 9);

        assertThat(rangeOne.combine(rangeTwo)).isEqualTo(combined);
        assertThat(rangeTwo.combine(rangeOne)).isEqualTo(combined);
    }

    @Test
    void combine_disjoint() {
        Range rangeOne = new Range(0, 2);
        Range rangeTwo = new Range(6, 11);

        assertThat(rangeOne.combine(rangeTwo)).isNull();
        assertThat(rangeTwo.combine(rangeOne)).isNull();
    }

    @ParameterizedTest
    @CsvSource({
            "3, 6",  // containing
            "8, 10", // touching
            "3, 10"  // overlapping
    })
    void touches_true() {
        Range rangeOne = new Range(0, 7);
        Range rangeTwo = new Range(3, 6);

        assertThat(rangeOne.touches(rangeTwo)).isTrue();
        assertThat(rangeTwo.touches(rangeOne)).isTrue();

        assertThat(rangeOne.isDisjoint(rangeTwo)).isFalse();
        assertThat(rangeTwo.isDisjoint(rangeOne)).isFalse();
    }

    @Test
    void touches_false() {
        Range rangeOne = new Range(0, 4);
        Range rangeTwo = new Range(6, 11);

        assertThat(rangeOne.touches(rangeTwo)).isFalse();
        assertThat(rangeTwo.touches(rangeOne)).isFalse();

        assertThat(rangeOne.isDisjoint(rangeTwo)).isTrue();
        assertThat(rangeTwo.isDisjoint(rangeOne)).isTrue();
    }
}