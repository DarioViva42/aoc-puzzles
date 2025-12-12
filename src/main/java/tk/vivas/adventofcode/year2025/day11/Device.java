package tk.vivas.adventofcode.year2025.day11;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class Device {

    private final String name;
    private final List<String> childNames;

    private List<Device> children;
    private long numberOfPaths;

    Device(String input) {
        String[] split = input.split(": ");
        name = split[0];

        numberOfPaths = - 1;

        childNames = Arrays.stream(split[1].split(" ")).toList();
    }

    Device() {
        name = "out";
        numberOfPaths = 1;
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
}
