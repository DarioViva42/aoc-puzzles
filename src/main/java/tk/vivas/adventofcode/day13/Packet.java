package tk.vivas.adventofcode.day13;

sealed interface Packet extends Comparable<Packet> permits IntegerPacket, ListPacket {
    static Packet parse(String input) {
        if (input.charAt(0) == '[') {
            return ListPacket.parse(input);
        }
        return IntegerPacket.parse(input);
    }
}
