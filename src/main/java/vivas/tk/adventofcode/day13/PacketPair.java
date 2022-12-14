package vivas.tk.adventofcode.day13;

class PacketPair {

    private final Packet left;
    private final Packet right;

    public PacketPair(Packet left, Packet right) {
        this.left = left;
        this.right = right;
    }

    public static PacketPair parse(String input) {
        String[] split = input.split("\\n|\\r\\n");

        Packet leftPacket = ListPacket.parse(split[0]);
        Packet rightPacket = ListPacket.parse(split[1]);

        return new PacketPair(leftPacket, rightPacket);
    }

    public boolean isInOrder() {
        return left.compareTo(right) < 0;
    }

    public Packet getLeftPacket() {
        return left;
    }

    public Packet getRightPacket() {
        return right;
    }
}
