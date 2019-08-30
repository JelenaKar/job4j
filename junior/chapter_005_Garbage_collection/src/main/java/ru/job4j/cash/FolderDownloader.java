package ru.job4j.cash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, работающий с содержимым папки.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class FolderDownloader {
    private List<String> files = new ArrayList<>();

    /**
     * Выгружает список файлов из папки.
     * @param folder - адрес папки в виде строки.
     * @return список имён файлов.
     */
    public List<String> getFilesList(String folder) {
        try {
            Files.newDirectoryStream(Path.of(Cash.class.getClassLoader().getResource(folder).getPath())).forEach(x -> this.files.add(x.getFileName().toString()));
        } catch (IOException | NullPointerException ex) {
            this.files = null;
        }
        return this.files;
    }
}
