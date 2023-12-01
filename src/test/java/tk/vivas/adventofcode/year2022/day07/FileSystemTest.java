package tk.vivas.adventofcode.year2022.day07;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static tk.vivas.adventofcode.AocUtils.readPuzzleInput;

class FileSystemTest {

    @Test
    void countSizeNaiveApproach() {
        String input = readPuzzleInput();
        FileSystem fileSystem = new FileSystem(input);

        assertThat(fileSystem.countSizeNaiveApproach())
                .isEqualTo(95437);
    }


    @Test
    void findSmallestToDeleteFolder() {
        String input = readPuzzleInput();
        FileSystem fileSystem = new FileSystem(input);

        assertThat(fileSystem.findSmallestToDeleteFolder())
                .hasValueSatisfying(folder -> {
                    assertThat(folder.name()).isEqualTo("d");
                    assertThat(folder.size()).isEqualTo(24933642L);
                });
    }
}