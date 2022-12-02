package vivas.tk.adventofcode.day02;

import junit.framework.TestCase;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.day02.HandShape.*;

public class HandShapeTest extends TestCase {

    public void testWinsAgainst_true() {
        assertThat(ROCK.winsAgainst(SCISSORS)).isTrue();
        assertThat(PAPER.winsAgainst(ROCK)).isTrue();
        assertThat(SCISSORS.winsAgainst(PAPER)).isTrue();
    }

    public void testWinsAgainst_false() {
        assertThat(ROCK.winsAgainst(PAPER)).isFalse();
        assertThat(PAPER.winsAgainst(SCISSORS)).isFalse();
        assertThat(SCISSORS.winsAgainst(ROCK)).isFalse();
    }

    public void testGetWinningHandShape() {
        assertThat(ROCK.getWinningHandShape()).isEqualTo(PAPER);
        assertThat(PAPER.getWinningHandShape()).isEqualTo(SCISSORS);
        assertThat(SCISSORS.getWinningHandShape()).isEqualTo(ROCK);
    }

    public void testGetLoosingHandShape() {
        assertThat(ROCK.getLoosingHandShape()).isEqualTo(SCISSORS);
        assertThat(PAPER.getLoosingHandShape()).isEqualTo(ROCK);
        assertThat(SCISSORS.getLoosingHandShape()).isEqualTo(PAPER);
    }
}