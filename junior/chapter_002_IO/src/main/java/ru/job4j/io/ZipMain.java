package ru.job4j.io;

public class ZipMain {
    public static void main(String[] args) {
        Args arguments = new Args(args);
        Zip archive = new Zip();
        archive.pack(arguments.directory(), arguments.output(), arguments.exclude());
    }
}
