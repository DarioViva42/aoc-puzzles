package tk.vivas.adventofcode.year2025.day11;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class Device {

    private static final Map<PathQuality, Long> OUT_MAP = Map.of(
            PathQuality.NONE, 1L,
            PathQuality.DAC, 0L,
            PathQuality.FFT, 0L,
            PathQuality.BOTH, 0L);

    private static final Map<PathQuality, PathQuality> DEFAULT_TRANSFORM_MAP = Map.of(
            PathQuality.NONE, PathQuality.NONE,
            PathQuality.DAC, PathQuality.DAC,
            PathQuality.FFT, PathQuality.FFT,
            PathQuality.BOTH, PathQuality.BOTH);

    private static final Map<PathQuality, PathQuality> DAC_TRANSFORM_MAP = Map.of(
            PathQuality.NONE, PathQuality.DAC,
            PathQuality.DAC, PathQuality.DAC,
            PathQuality.FFT, PathQuality.BOTH,
            PathQuality.BOTH, PathQuality.BOTH);

    private static final Map<PathQuality, PathQuality> FFT_TRANSFORM_MAP = Map.of(
            PathQuality.NONE, PathQuality.FFT,
            PathQuality.DAC, PathQuality.BOTH,
            PathQuality.FFT, PathQuality.FFT,
            PathQuality.BOTH, PathQuality.BOTH);

    private final String name;
    private final List<String> childNames;
    private final Map<PathQuality, PathQuality> transformMap;

    private List<Device> children;
    private long numberOfPaths;
    private Map<PathQuality, Long> numberOfPathsDetailed;

    Device(String input) {
        String[] split = input.split(": ");
        name = split[0];

        numberOfPaths = -1;
        numberOfPathsDetailed = null;

        transformMap = switch (name) {
            case "dac" -> DAC_TRANSFORM_MAP;
            case "fft" -> FFT_TRANSFORM_MAP;
            default -> DEFAULT_TRANSFORM_MAP;
        };

        childNames = Arrays.stream(split[1].split(" ")).toList();
    }

    Device() {
        name = "out";

        numberOfPaths = 1;
        numberOfPathsDetailed = OUT_MAP;

        transformMap = DEFAULT_TRANSFORM_MAP;

        childNames = Collections.emptyList();
    }

    void resolveChildren(Map<String, Device> lookup) {
        children = childNames.stream()
                .map(lookup::get)
                .toList();
    }

    String getName() {
        return name;
    }

    long numberOfPaths() {
        if (numberOfPaths != -1) {
            return numberOfPaths;
        }
        numberOfPaths = children.stream()
                .mapToLong(Device::numberOfPaths)
                .sum();
        return numberOfPaths;
    }

    Map<PathQuality, Long> numberOfPathsDetailed() {
        if (numberOfPathsDetailed != null) {
            return numberOfPathsDetailed;
        }

        long noneCount = 0;
        long dacCount = 0;
        long fftCount = 0;
        long bothCount = 0;

        for (Device child : children) {
            for (Map.Entry<PathQuality, Long> entry : child.numberOfPathsDetailed().entrySet()) {
                Long value = entry.getValue();
                switch (transformMap.get(entry.getKey())) {
                    case NONE -> noneCount += value;
                    case DAC -> dacCount += value;
                    case FFT -> fftCount += value;
                    case BOTH -> bothCount += value;
                }
            }
        }

        numberOfPathsDetailed = Map.of(
                PathQuality.NONE, noneCount,
                PathQuality.DAC, dacCount,
                PathQuality.FFT, fftCount,
                PathQuality.BOTH, bothCount);

        return numberOfPathsDetailed;
    }

}
