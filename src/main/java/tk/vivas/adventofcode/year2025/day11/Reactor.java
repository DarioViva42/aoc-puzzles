package tk.vivas.adventofcode.year2025.day11;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Reactor {

    private final Device startDevice;

    Reactor(String input) {
        Map<String, Device> deviceLookup = input.lines()
                .map(Device::new)
                .collect(Collectors.toMap(Device::getName, Function.identity()));

        deviceLookup.put("out", new Device());

        deviceLookup.values()
                .forEach(device -> device.resolveChildren(deviceLookup));

        startDevice = deviceLookup.get("you");
    }

    long numberOfPaths() {
        return startDevice.numberOfPaths();
    }
}
