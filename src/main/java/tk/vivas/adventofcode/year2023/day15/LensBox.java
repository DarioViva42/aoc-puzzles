package tk.vivas.adventofcode.year2023.day15;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.IntStream;

class LensBox {

    private final int boxNumber;
    private final LinkedList<LabeledLens> lensList;

    LensBox(int boxNumber) {
        this.boxNumber = boxNumber;

        lensList = new LinkedList<>();
    }

    void removeLens(String label) {
        findLens(label).ifPresent(lensList::remove);
    }

    void addLens(String label, int focalLength) {
        LabeledLens newLens = new LabeledLens(label, focalLength);
        findLens(label)
                .map(lensList::indexOf)
                .ifPresentOrElse(integer -> replaceLens(integer, newLens), () -> addLens(newLens));
    }

    private void replaceLens(int index, LabeledLens newLens) {
        lensList.remove(index);
        lensList.add(index, newLens);
    }

    void addLens(LabeledLens lens) {
        lensList.add(lens);
    }

    private Optional<LabeledLens> findLens(String label) {
        return lensList.stream()
                .filter(lens -> lens.label().equals(label))
                .findFirst();
    }

    long focusingPower() {
        return IntStream.range(0, lensList.size())
                .mapToLong(this::focusingPower)
                .sum();
    }

    long focusingPower(int index) {
        return (1L + boxNumber) * (1L + index) * lensList.get(index).focalLength();
    }
}
