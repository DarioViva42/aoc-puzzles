package tk.vivas.adventofcode.year2024.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class DiskFragmenter {

    private final List<DiskMapToken> diskMap;

    DiskFragmenter(String input) {

        char[] charArray = input.toCharArray();

        charArray[charArray.length - 1] = '0';

        diskMap = IntStream.iterate(0, i -> i < charArray.length, i -> i + 2)
                .mapToObj(i -> {
                    int fileLength = charArray[i] - '0';
                    int freeSpaceLength = charArray[i + 1] - '0';
                    return new DiskMapToken(fileLength, freeSpaceLength);
                })
                .toList();
    }

    long filesystemChecksum() {
        List<Integer> diskSpace = fillDiskSpace();
        rearrangeDiskSpace(diskSpace);
        return calculateChecksum(diskSpace);
    }

    private List<Integer> fillDiskSpace() {
        List<Integer> diskSpace = new ArrayList<>();
        for (int i = 0; i < diskMap.size(); i++) {
            DiskMapToken token = diskMap.get(i);
            for (int j = 0; j < token.fileLength(); j++) {
                diskSpace.add(i);
            }
            for (int j = 0; j < token.freeSpaceLength(); j++) {
                diskSpace.add(null);
            }
        }
        return diskSpace;
    }

    private void rearrangeDiskSpace(List<Integer> diskSpace) {
        int pointer = 0;
        while(pointer < diskSpace.size()) {
            if (diskSpace.get(pointer) == null) {
                moveBlock(diskSpace, pointer);
            }
            pointer++;
        }
    }

    private void moveBlock(List<Integer> diskSpace, int pointer) {
        Integer last;
        do {
            last = diskSpace.removeLast();
        } while (last == null);
        diskSpace.set(pointer, last);
    }

    private long calculateChecksum(List<Integer> diskSpace) {
        return IntStream.range(0, diskSpace.size())
                .mapToLong(blockNumber -> (long) blockNumber * diskSpace.get(blockNumber))
                .sum();
    }
}
