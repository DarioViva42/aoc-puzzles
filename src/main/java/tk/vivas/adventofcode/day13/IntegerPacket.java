package tk.vivas.adventofcode.day13;

import java.util.List;

final class IntegerPacket implements Packet {
    int value;

    public IntegerPacket(int value) {
        this.value = value;
    }

    public static IntegerPacket parse(String input) {
        return new IntegerPacket(Integer.parseInt(input));
    }

    @Override
    public int compareTo(Packet other) {
        return switch (other) {
            case IntegerPacket integerPacket -> value - integerPacket.value;
            case ListPacket listPacket -> new ListPacket(List.of(this)).compareTo(listPacket);
        };
    }
}
