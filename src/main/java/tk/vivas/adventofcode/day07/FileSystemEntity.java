package tk.vivas.adventofcode.day07;

sealed interface FileSystemEntity permits Folder, File {
    String name();

    int size();

    Folder parent();
}
