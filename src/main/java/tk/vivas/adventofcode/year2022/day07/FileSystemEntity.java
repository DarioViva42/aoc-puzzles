package tk.vivas.adventofcode.year2022.day07;

sealed interface FileSystemEntity permits Folder, File {
    String name();

    int size();

    Folder parent();
}
