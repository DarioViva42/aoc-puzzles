package tk.vivas.adventofcode.year2024.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

class WholeFileArranger {

    private final List<DiskMapToken> targetMap;

    WholeFileArranger(List<DiskMapToken> sourceMap) {
        this.targetMap = new ArrayList<>(sourceMap);

        IntStream.iterate(targetMap.size() - 1, i -> i > 0, i -> i - 1)
                .mapToObj(this::findIndexById)
                .mapToInt(OptionalInt::orElseThrow)
                .forEach(sourceIndex -> findTargetIndex(sourceIndex).ifPresent(
                        targetIndex -> moveToken(sourceIndex, targetIndex)));
    }

    private OptionalInt findIndexById(int fileId) {
        return IntStream.iterate(targetMap.size() - 1, i -> i > 0, i -> i - 1)
                .filter(index -> fileId == targetMap.get(index).fileId())
                .findFirst();
    }

    private OptionalInt findTargetIndex(int sourceIndex) {
        return IntStream.range(0, sourceIndex)
                .filter(index -> targetMap.get(index).freeSpaceLength() >= targetMap.get(sourceIndex).fileLength())
                .findFirst();
    }

    private void moveToken(int sourceIndex, int targetIndex) {
        DiskMapToken precursor = targetMap.get(sourceIndex - 1);
        DiskMapToken source = targetMap.remove(sourceIndex);

        int freeSpaceLeftBehind = precursor.freeSpaceLength() + source.fileLength() + source.freeSpaceLength();
        targetMap.set(sourceIndex - 1, precursor.withFreeSpace(freeSpaceLeftBehind));

        DiskMapToken target = targetMap.get(targetIndex);

        int reducedFreeSpace = target.freeSpaceLength() - source.fileLength();
        targetMap.add(targetIndex + 1, source.withFreeSpace(reducedFreeSpace));
        targetMap.set(targetIndex, target.withFreeSpace(0));
    }

    List<DiskMapToken> getArrangedMap() {
        return targetMap;
    }
}


