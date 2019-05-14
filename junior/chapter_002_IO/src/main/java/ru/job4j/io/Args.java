package ru.job4j.io;

import java.io.File;

public class Args {

    private String directory;
    private String output;
    private boolean exclude = false;

    public File directory() {
        return new File(this.directory);
    }

    public boolean exclude() {
        return exclude;
    }

    public File output() {
        return new File(this.output);
    }

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    this.directory = args[++i];
                    break;
                case "-e":
                    this.exclude = true;
                    break;
                case "-o":
                    this.output = args[++i];
                    break;
                default:
            }
        }
    }
}
