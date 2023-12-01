package tk.vivas.adventofcode.day16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Strategy {
    private int score;
    private final Set<Valve> openedValves;
    final List<Valve> openedValvesOrdered;
    private final Valve currentValve;

    public Strategy(Valve valve) {
        score = 0;
        currentValve = valve;
        openedValves = new HashSet<>();
        openedValvesOrdered = new ArrayList<>();
    }

    public Strategy(Strategy strategy, Valve valve) {
        this.score = strategy.score;
        this.currentValve = valve;
        this.openedValves = new HashSet<>(strategy.openedValves);
        this.openedValvesOrdered = new ArrayList<>(strategy.openedValvesOrdered);
    }

    public int getScore() {
        return score;
    }

    public boolean shouldOpenValve() {
        if (currentValve.flowRate() == 0) {
            return false;
        }
        return !openedValves.contains(currentValve);
    }

    public Valve getCurrentValve() {
        return currentValve;
    }

    public void openValve() {
        openedValves.add(currentValve);
        openedValvesOrdered.add(currentValve);
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void writeScoreToValve() {
        currentValve.updateBestScore(score);
    }
}
