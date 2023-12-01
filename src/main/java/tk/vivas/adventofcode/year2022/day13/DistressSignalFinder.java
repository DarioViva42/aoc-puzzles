package tk.vivas.adventofcode.year2022.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DistressSignalFinder {

    List<PacketPair> pairs;

    public DistressSignalFinder(String input) {
        pairs = Arrays
                .stream(input.split("(\\n|\\r\\n){2}"))
                .map(PacketPair::parse)
                .toList();
    }

    public int evaluateCorrectlyOrderedPairs() {
        int score = 0;
        for (int i = 0; i < pairs.size(); i++) {
            if (pairs.get(i).isInOrder()) {
                score += i + 1;
            }
        }
        return score;
    }

    public int findDecoderKey() {
        List<Packet> packetList = new ArrayList<>();
        for (PacketPair pair : pairs) {
            packetList.add(pair.getLeftPacket());
            packetList.add(pair.getRightPacket());
        }

        Packet dividerOne = Packet.parse("[[2]]");
        Packet dividerTwo = Packet.parse("[[6]]");
        packetList.add(dividerOne);
        packetList.add(dividerTwo);

        packetList.sort(Comparable::compareTo);

        int indexOfDividerOne = packetList.indexOf(dividerOne) + 1;
        int indexOfDividerTwo = packetList.indexOf(dividerTwo) + 1;
        return indexOfDividerOne * indexOfDividerTwo;
    }
}
