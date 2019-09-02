package ru.job4j.cash;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс абстрактного кеша.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Cash {
    private Map<SoftRefKey<String>, String> storage = new HashMap<>();

    /**
     * Выгружает файл из кеша. Если файл отсутствовал, то предварительно его туда загружает.
     * @param file - имя или адрес файла в виде строки.
     * @return содержание текстового файла.
     */
    public String receive(String file) {
        return this.storage.computeIfAbsent(new SoftRefKey<>(file),
                refKey -> {
                    URL url = Cash.class.getClassLoader().getResource(refKey.get());
                    return (url == null) ? null : this.readFileContent(URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8));
                });
    }

    private String readFileContent(String name) {
        try (FileChannel file = (FileChannel) Files.newByteChannel(Path.of(name))) {
            long size = file.size();
            MappedByteBuffer buffer = file.map(FileChannel.MapMode.READ_ONLY, 0, size);
            return StandardCharsets.UTF_8.decode(buffer).toString();
        } catch (IOException e) {
            return null;
        }
    }

    Map<SoftRefKey<String>, String> getStorage() {
        return this.storage;
    }
}
