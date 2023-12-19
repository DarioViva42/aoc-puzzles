package tk.vivas.adventofcode.year2023.day19;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class MachinePartsOrganizer {

    private final List<MachinePart> machineParts;
    private final MachineWorkflow startWorkflow;

    MachinePartsOrganizer(String input) {
        String[] split = input.split("\n\n");
        Map<String, MachineWorkflow> workflows = split[0].lines()
                .map(MachineWorkflow::from)
                .collect(Collectors.toMap(MachineWorkflow::getName, Function.identity()));

        workflows.values()
                .forEach(workflow -> workflow.init(workflows));

        startWorkflow = workflows.get("in");

        machineParts = split[1].lines()
                .map(MachinePart::from)
                .toList();
    }

    long acceptedPropertiesSum() {
        return machineParts.stream()
                .filter(startWorkflow::classify)
                .mapToLong(MachinePart::totalRanking)
                .sum();
    }

    long countAllAcceptedCombinations() {
        return startWorkflow.countAcceptedPossibilities(
                1, 4000,
                1, 4000,
                1, 4000,
                1, 4000,
                0
        );
    }
}
