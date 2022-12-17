package vivas.tk.adventofcode.day16;

import java.util.HashSet;
import java.util.Set;

class Strategy {
    private int score;
    private final Set<Valve> openedValves;
    private final Valve currentValve;

    public Strategy(Valve valve) {
        score = 0;
        currentValve = valve;
        openedValves = new HashSet<>();
    }

    public Strategy(Strategy strategy, Valve valve) {
        this.score = strategy.score;
        this.currentValve = valve;
        this.openedValves = new HashSet<>(strategy.openedValves);
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
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void writeScoreToValve() {
        currentValve.updateBestScore(score);
    }
}
