package tk.vivas.adventofcode.year2024.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class FileArranger {

    private final List<DiskMapToken> diskMap;

    FileArranger(String input) {

        char[] charArray = input.toCharArray();

        charArray[charArray.length - 1] = '0';

        diskMap = IntStream.iterate(0, i -> i < charArray.length, i -> i + 2)
                .mapToObj(i -> DiskMapToken.createDiskMapToken(charArray, i))
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
        IntStream.iterate(0, i -> i < diskSpace.size(), i -> i + 1)
                .filter(pointer -> null == diskSpace.get(pointer))
                .forEach(pointer -> moveBlock(diskSpace, pointer));
    }

    private void moveBlock(List<Integer> diskSpace, int pointer) {
        int last = Stream.generate(diskSpace::removeLast)
                .dropWhile(Objects::isNull)
                .findFirst().orElseThrow();
        diskSpace.set(pointer, last);
    }

    private long calculateChecksum(List<Integer> diskSpace) {
        return IntStream.range(0, diskSpace.size())
                .filter(blockNumber -> null != diskSpace.get(blockNumber))
                .mapToLong(blockNumber -> (long) blockNumber * diskSpace.get(blockNumber))
                .sum();
    }
}
