package vivas.tk.adventofcode.day07;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vivas.tk.adventofcode.GeneralUtils.readFile;

class FileSystemTest {

    @Test
    void countSizeNaiveApproach() {
        List<String> input = readFile("/07.txt");

        FileSystem fileSystem = new FileSystem(input);

        assertThat(fileSystem.countSizeNaiveApproach()).isEqualTo(95437);
    }


    @Test
    void findSmallestToDeleteFolder() {
        List<String> input = readFile("/07.txt");

        FileSystem fileSystem = new FileSystem(input);

        assertThat(fileSystem.findSmallestToDeleteFolder()).hasValueSatisfying(folder -> {
            assertThat(folder.name()).isEqualTo("d");
            assertThat(folder.size()).isEqualTo(24933642L);
        });
    }
}