package tk.vivas.adventofcode.year2023.day19;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

class MachineWorkflow {
    private final String name;
    private final List<WorkflowStep> steps;

    MachineWorkflow(String name, List<WorkflowStep> steps) {
        this.name = name;
        this.steps = steps;
    }

    static MachineWorkflow from(String raw) {
        String[] split = raw.split("\\{");
        String name = split[0];
        String stepString = split[1].substring(0, split[1].length() - 1);
        List<WorkflowStep> steps = Arrays.stream(stepString.split(","))
                .map(WorkflowStep::from)
                .toList();
        return new MachineWorkflow(name, steps);
    }

    void init(Map<String, MachineWorkflow> workflows) {
        steps.forEach(step -> step.init(workflows));
    }

    String getName() {
        return name;
    }

    boolean classify(MachinePart machinePart) {
        for (WorkflowStep step : steps) {
            if (step.test(machinePart)) {
                return step.classify(machinePart);
            }
        }
        return false;
    }
}
