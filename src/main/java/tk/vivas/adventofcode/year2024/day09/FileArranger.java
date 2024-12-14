package tk.vivas.adventofcode.year2024.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

class FileArranger {

    private final List<DiskMapToken> diskMap;

    FileArranger(String input) {

        char[] charArray = input.toCharArray();

        charArray[charArray.length - 1] = '0';

        diskMap = IntStream.iterate(0, i -> i < charArray.length, i -> i + 2)
                .mapToObj(i -> {
                    int fileLength = charArray[i] - '0';
                    int freeSpaceLength = charArray[i + 1] - '0';
                    return new DiskMapToken(i / 2, fileLength, freeSpaceLength);
                })
                .toList();
    }

    long filesystemChecksumWithFragmenting() {
        List<Integer> diskSpace = fillDiskSpace(diskMap);
        fragmentDiskSpace(diskSpace);
        return calculateChecksum(diskSpace);
    }

    long filesystemChecksumWithoutFragmenting() {
        List<DiskMapToken> arrangedDiskMap = new WholeFileArranger(diskMap).getArrangedMap();
        List<Integer> diskSpace = fillDiskSpace(arrangedDiskMap);
        return calculateChecksum(diskSpace);
    }

    private List<Integer> fillDiskSpace(List<DiskMapToken> tokens) {
        List<Integer> diskSpace = new ArrayList<>();
        for (DiskMapToken token : tokens) {
            for (int j = 0; j < token.fileLength(); j++) {
                diskSpace.add(token.fileId());
            }
            for (int j = 0; j < token.freeSpaceLength(); j++) {
                diskSpace.add(null);
            }
        }
        return diskSpace;
    }

    private void fragmentDiskSpace(List<Integer> diskSpace) {
        int pointer = 0;
        while (pointer < diskSpace.size()) {
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
                .mapToLong(blockNumber -> {
                    int fileId = Objects.requireNonNullElse(diskSpace.get(blockNumber), 0);
                    return (long) blockNumber * fileId;
                })
                .sum();
    }
}
