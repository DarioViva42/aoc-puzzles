package tk.vivas.adventofcode.year2023.day19;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    long countAcceptedPossibilities(
            int startX, int endX,
            int startM, int endM,
            int startA, int endA,
            int startS, int endS,
            int stepNumber
    ) {
        WorkflowStep workflowStep = steps.get(stepNumber);
        return switch (workflowStep.getRating()) {
            case 'x' -> countTestX(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep, stepNumber);
            case 'm' -> countTestM(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep, stepNumber);
            case 'a' -> countTestA(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep, stepNumber);
            case 's' -> countTestS(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep, stepNumber);
            default -> countTestPassed(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep);
        };
    }

    private long countTestX(int startX, int endX,
                            int startM, int endM,
                            int startA, int endA,
                            int startS, int endS,
                            WorkflowStep workflowStep, int stepNumber) {
        if (workflowStep.getOperator() == '<') {
            return countTestSmallerX(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep, stepNumber
            );
        }
        return countTestLargerX(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep, stepNumber
        );
    }

    private long countTestLargerX(int startX, int endX,
                                  int startM, int endM,
                                  int startA, int endA,
                                  int startS, int endS,
                                  WorkflowStep workflowStep, int stepNumber) {
        int number = workflowStep.getNumber();
        if (number >= endX) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            );
        } else if (number >= startX) {
            return countAcceptedPossibilities(
                    startX, number,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            ) + countTestPassed(
                    number + 1, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep
            );
        }
        return countTestPassed(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep
        );
    }

    private long countTestSmallerX(int startX, int endX,
                                   int startM, int endM,
                                   int startA, int endA,
                                   int startS, int endS,
                                   WorkflowStep workflowStep, int stepNumber) {
        int number = workflowStep.getNumber();
        if (number <= startX) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            );
        } else if (number <= endX) {
            return countAcceptedPossibilities(
                    number, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            ) + countTestPassed(
                    startX, number - 1,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep
            );
        }
        return countTestPassed(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep
        );
    }

    private long countTestM(int startX, int endX,
                            int startM, int endM,
                            int startA, int endA,
                            int startS, int endS,
                            WorkflowStep workflowStep, int stepNumber) {
        if (workflowStep.getOperator() == '<') {
            return countTestSmallerM(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep, stepNumber
            );
        }
        return countTestLargerM(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep, stepNumber
        );
    }

    private long countTestLargerM(int startX, int endX,
                                  int startM, int endM,
                                  int startA, int endA,
                                  int startS, int endS,
                                  WorkflowStep workflowStep, int stepNumber) {
        int number = workflowStep.getNumber();
        if (number >= endM) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            );
        } else if (number >= startM) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, number,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            ) + countTestPassed(
                    startX, endX,
                    number + 1, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep
            );
        }
        return countTestPassed(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep
        );
    }

    private long countTestSmallerM(int startX, int endX,
                                   int startM, int endM,
                                   int startA, int endA,
                                   int startS, int endS,
                                   WorkflowStep workflowStep, int stepNumber) {
        int number = workflowStep.getNumber();
        if (number <= startM) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            );
        } else if (number <= endM) {
            return countAcceptedPossibilities(
                    startX, endX,
                    number, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            ) + countTestPassed(
                    startX, endX,
                    startM, number - 1,
                    startA, endA,
                    startS, endS,
                    workflowStep
            );
        }
        return countTestPassed(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep
        );
    }

    private long countTestA(int startX, int endX,
                            int startM, int endM,
                            int startA, int endA,
                            int startS, int endS,
                            WorkflowStep workflowStep, int stepNumber) {
        if (workflowStep.getOperator() == '<') {
            return countTestSmallerA(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep, stepNumber
            );
        }
        return countTestLargerA(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep, stepNumber
        );
    }

    private long countTestSmallerA(int startX, int endX,
                                   int startM, int endM,
                                   int startA, int endA,
                                   int startS, int endS,
                                   WorkflowStep workflowStep, int stepNumber) {
        int number = workflowStep.getNumber();
        if (number <= startA) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            );
        } else if (number <= endA) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    number, endA,
                    startS, endS,
                    stepNumber + 1
            ) + countTestPassed(
                    startX, endX,
                    startM, endM,
                    startA, number - 1,
                    startS, endS,
                    workflowStep
            );
        }
        return countTestPassed(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep
        );
    }

    private long countTestLargerA(int startX, int endX,
                                  int startM, int endM,
                                  int startA, int endA,
                                  int startS, int endS,
                                  WorkflowStep workflowStep, int stepNumber) {
        int number = workflowStep.getNumber();
        if (number >= endA) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            );
        } else if (number >= startA) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, number,
                    startS, endS,
                    stepNumber + 1
            ) + countTestPassed(
                    startX, endX,
                    startM, endM,
                    number + 1, endA,
                    startS, endS,
                    workflowStep
            );
        }
        return countTestPassed(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep
        );
    }

    private long countTestS(int startX, int endX,
                             int startM, int endM,
                             int startA, int endA,
                             int startS, int endS,
                             WorkflowStep workflowStep, int stepNumber) {
        if (workflowStep.getOperator() == '<') {
            return countTestSmallerS(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    workflowStep, stepNumber
            );
        }
        return countTestLargerS(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep, stepNumber
        );
    }

    private long countTestSmallerS(int startX, int endX,
                                   int startM, int endM,
                                   int startA, int endA,
                                   int startS, int endS,
                                   WorkflowStep workflowStep, int stepNumber) {
        int number = workflowStep.getNumber();
        if (number <= startS) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            );
        } else if (number <= endS) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    number, endS,
                    stepNumber + 1
            ) + countTestPassed(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, number - 1,
                    workflowStep
            );
        }
        return countTestPassed(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep
        );
    }

    private long countTestLargerS(int startX, int endX,
                                   int startM, int endM,
                                   int startA, int endA,
                                   int startS, int endS,
                                   WorkflowStep workflowStep, int stepNumber) {
        int number = workflowStep.getNumber();
        if (number >= endS) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    stepNumber + 1
            );
        } else if (number >= startS) {
            return countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, number,
                    stepNumber + 1
            ) + countTestPassed(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    number + 1, endS,
                    workflowStep
            );
        }
        return countTestPassed(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS,
                workflowStep
        );
    }

    private long countTestPassed(int startX, int endX,
                                 int startM, int endM,
                                 int startA, int endA,
                                 int startS, int endS,
                                 WorkflowStep workflowStep) {
        MachineWorkflow nextWorkflow = workflowStep.getNextWorkflow();
        if (nextWorkflow != null) {
            return nextWorkflow.countAcceptedPossibilities(
                    startX, endX,
                    startM, endM,
                    startA, endA,
                    startS, endS,
                    0
            );
        }
        String classificationString = workflowStep.getClassificationString();
        if ("R".equals(classificationString)) {
            return 0;
        }
        return countPossibilities(
                startX, endX,
                startM, endM,
                startA, endA,
                startS, endS);
    }

    private long countPossibilities(int startX, int endX,
                                    int startM, int endM,
                                    int startA, int endA,
                                    int startS, int endS) {
        int x = endX - startX + 1;
        int m = endM - startM + 1;
        int a = endA - startA + 1;
        int s = endS - startS + 1;
        return (long) x * m * a * s;
    }

    @Override
    public String toString() {
        String stepString = steps.stream()
                .map(WorkflowStep::toString)
                .collect(Collectors.joining(","));
        return "%s{%s}".formatted(name, stepString);
    }
}
