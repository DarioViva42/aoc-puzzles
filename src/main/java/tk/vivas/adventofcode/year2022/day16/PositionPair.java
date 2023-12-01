package tk.vivas.adventofcode.year2022.day16;

import java.util.Objects;

public class PositionPair {
    MegaValve valveA;
    MegaValve valveB;

    public PositionPair(MegaValve valveA, MegaValve valveB) {
        this.valveA = valveA;
        this.valveB = valveB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionPair that = (PositionPair) o;
        boolean normal = Objects.equals(valveA, that.valveA) && Objects.equals(valveB, that.valveB);
        boolean swapped = Objects.equals(valveA, that.valveA) && Objects.equals(valveB, that.valveB);
        return normal || swapped;
    }

    @Override
    public int hashCode() {
        int hashA = Math.min(valveA.hashCode(), valveB.hashCode());
        int hashB = Math.max(valveA.hashCode(), valveB.hashCode());
        return Objects.hash(hashA, hashB);
    }
}
