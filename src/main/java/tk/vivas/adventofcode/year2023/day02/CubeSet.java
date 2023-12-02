package tk.vivas.adventofcode.year2023.day02;

import java.util.Arrays;
import java.util.List;

class CubeSet {

    int redCubeCount = 0;
    int greenCubeCount = 0;
    int blueCubeCount = 0;

    CubeSet(String raw) {
        List<Cubes> cubesList = Arrays.stream(raw.split(", "))
                .map(Cubes::new)
                .toList();
        for (Cubes cubes : cubesList) {
            switch (cubes.getColor()) {
                case red -> redCubeCount = cubes.getAmount();
                case green -> greenCubeCount = cubes.getAmount();
                case blue -> blueCubeCount = cubes.getAmount();
            }
        }
    }

    public boolean isPossible() {
        return redCubeCount <= 12 && greenCubeCount <= 13 && blueCubeCount <= 14;
    }
}
