package ru.job4j.io;

import java.io.File;
import java.util.*;

/**
 * Класс поиска файлов в дереве каталогов.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Search {
    private List<File> res = new ArrayList<>();

    /**
     * Метод, возвращающий список файлов с требуемым расширением из дерева каталогов.
     * @param parent - корневая папка, с которой начинается поиск.
     * @param exts - список искомых расширений.
     * @return список найденных файлов или null, если файлов с искомым расширением нет.
     */
    public List<File> files(String parent, List<String> exts) {
        Queue<File> entries = new LinkedList<>(Arrays.asList(new File(parent).listFiles()));
        while (!entries.isEmpty()) {
            File entry = entries.poll();
            if (entry.isDirectory()) {
                entries.addAll(Arrays.asList(entry.listFiles()));
            } else {
                if (checkExtension(entry.getName(), exts)) {
                    res.add(entry);
                }
            }
        }
        return res;
    }

    private boolean checkExtension(String extension, List<String> list) {
        boolean res = false;
        for (String ext : list) {
            if (extension.endsWith(ext)) {
                res = true;
                break;
            }
        }
        return res;
    }
}