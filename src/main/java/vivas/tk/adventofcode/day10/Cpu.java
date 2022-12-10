package vivas.tk.adventofcode.day10;

class Cpu {
    int cycle;
    int x;

    public Cpu() {
        cycle = 1;
        x = 1;
    }

    public int tick() {
        int signalStrength = signalStrength();
        cycle++;
        return signalStrength;
    }

    private int signalStrength() {
        if ((cycle + 20) % 40 == 0) {
            return cycle * x;
        }
        return 0;
    }

    public void addToRegister(int x) {
        this.x += x;
    }
}
