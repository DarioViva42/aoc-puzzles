package tk.vivas.adventofcode.day10;

class CPU {
    int cycle;
    int x;

    public CPU() {
        cycle = 1;
        x = 1;
    }

    public void tick() {
        cycle++;
    }

    public int signalStrength() {
        if ((cycle + 20) % 40 == 0) {
            return cycle * x;
        }
        return 0;
    }

    public void drawTo(CRT crt) {
        if (spriteActive()) {
            crt.turnOn(cycle - 1);
        }
    }

    private boolean spriteActive() {
        int pointer = (cycle - 1) % 40;
        return pointer == x || pointer - x == -1 || pointer - x == 1;
    }

    public void addToRegister(int x) {
        this.x += x;
    }
}
