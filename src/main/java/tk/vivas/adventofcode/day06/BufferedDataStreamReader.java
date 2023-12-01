package tk.vivas.adventofcode.day06;

class BufferedDataStreamReader {

    private final String input;

    public BufferedDataStreamReader(String input) {
        this.input = input;
    }

    public int findStartOfPacketMarker() {
        return findMarkerOfLength(4);
    }

    public int findStartOfMessageMarker() {
        return findMarkerOfLength(14);
    }

    private int findMarkerOfLength(int length) {
        int index = length;
        while (true) {
            String fifo = input.substring(index - length, index);
            int uniqueCount = countUniqueCharacters(fifo);
            if (uniqueCount == length) {
                return index;
            }
            index += length - uniqueCount;
        }
    }

    private int countUniqueCharacters(String fifo) {
        return (int) fifo
                .chars()
                .distinct()
                .count();
    }
}
