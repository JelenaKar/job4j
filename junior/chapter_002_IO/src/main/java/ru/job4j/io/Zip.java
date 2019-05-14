package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void pack(File source, File target, boolean exclude) {
        List<File> packing = seekBy(source, ".*");

        if (exclude) {
            packing.removeAll(seekBy(source, "iml"));
        }
        if (!packing.isEmpty()) {
            String prefix = source.getParent();
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (File file : packing) {
                    String path = file.getPath().substring(prefix.length() + 1);
                    path = (!file.isDirectory()) ? path : path + "/";
                    zip.putNextEntry(new ZipEntry(path));
                    if (!file.isDirectory()) {
                        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                            zip.write(out.readAllBytes());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<File> seekBy(File parent, String ext) {
        Queue<File> entries = new LinkedList<>(Arrays.asList(parent.listFiles()));
        List<File> res = new ArrayList<>();
        while (!entries.isEmpty()) {
            File entry = entries.poll();
            if (entry.isDirectory()) {
                if (entry.list().length == 0 && ext.equals(".*")) {
                    res.add(entry);
                } else {
                    entries.addAll(Arrays.asList(entry.listFiles()));
                }
            } else {
                if (ext.equals(".*") || entry.getName().endsWith(ext)) {
                    res.add(entry);
                }
            }
        }
        return res;
    }
}
