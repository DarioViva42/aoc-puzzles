package tk.vivas.adventofcode.year2023.day23;

import java.util.Set;

class PathSection {
    private final int length;
    private final Set<PathSection> nextPaths;

    PathSection(int length, Set<PathSection> nextPaths) {
        this.length = length;
        this.nextPaths = nextPaths;
    }

    int getLongestPathLength() {
        int longestContinuation = nextPaths.stream()
                .mapToInt(PathSection::getLongestPathLength)
                .max().orElse(0);
        return length + longestContinuation;
    }
}
