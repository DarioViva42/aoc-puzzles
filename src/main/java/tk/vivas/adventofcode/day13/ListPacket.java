package tk.vivas.adventofcode.day13;

import java.util.ArrayList;
import java.util.List;

final class ListPacket implements Packet {
    List<Packet> value;

    public ListPacket(List<Packet> value) {
        this.value = value;
    }

    public static ListPacket parse(String input) {
        String inside = input.substring(1, input.length() - 1);
        List<Packet> packetList = parseItems(inside).stream()
                .map(Packet::parse)
                .toList();
        return new ListPacket(packetList);
    }

    private static List<String> parseItems(String inside) {
        List<String> items = new ArrayList<>();
        int index = 0;
        int braceBalance = 0;
        for (int i = 0; i < inside.length(); i++) {
            char character = inside.charAt(i);
            switch (character) {
                case '[' -> braceBalance++;
                case ']' -> braceBalance--;
                case ',' -> {
                    if (braceBalance == 0) {
                        String item = inside.substring(index, i);
                        items.add(item);
                        index = i + 1;
                    }
                }
            }
        }
        String lastItem = inside.substring(index);
        if (!lastItem.isEmpty()) {
            items.add(lastItem);
        }
        return items;
    }

    @Override
    public int compareTo(Packet other) {
        return switch (other) {
            case IntegerPacket integerPacket -> compareTo(new ListPacket(List.of(integerPacket)));
            case ListPacket listPacket -> compareListPackets(listPacket);
        };
    }

    private int compareListPackets(ListPacket listPacket) {
        int smallerSize = Math.min(value.size(), listPacket.value.size());
        for (int i = 0; i < smallerSize; i++) {
            int compared = value.get(i).compareTo(listPacket.value.get(i));
            if (compared != 0) {
                return compared;
            }
        }
        return value.size() - listPacket.value.size();
    }
}
