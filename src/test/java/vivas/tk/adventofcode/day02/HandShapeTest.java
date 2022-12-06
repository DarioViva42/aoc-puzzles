package vivas.tk.adventofcode.day02;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.day02.HandShape.*;

class HandShapeTest {

    @Test
    void winsAgainst_true() {
        assertThat(ROCK.winsAgainst(SCISSORS)).isTrue();
        assertThat(PAPER.winsAgainst(ROCK)).isTrue();
        assertThat(SCISSORS.winsAgainst(PAPER)).isTrue();
    }

    @Test
    void winsAgainst_false() {
        assertThat(ROCK.winsAgainst(PAPER)).isFalse();
        assertThat(PAPER.winsAgainst(SCISSORS)).isFalse();
        assertThat(SCISSORS.winsAgainst(ROCK)).isFalse();
    }

    @Test
    void getWinningHandShape() {
        assertThat(ROCK.getWinningHandShape()).isEqualTo(PAPER);
        assertThat(PAPER.getWinningHandShape()).isEqualTo(SCISSORS);
        assertThat(SCISSORS.getWinningHandShape()).isEqualTo(ROCK);
    }

    @Test
    void getLoosingHandShape() {
        assertThat(ROCK.getLoosingHandShape()).isEqualTo(SCISSORS);
        assertThat(PAPER.getLoosingHandShape()).isEqualTo(ROCK);
        assertThat(SCISSORS.getLoosingHandShape()).isEqualTo(PAPER);
    }
}