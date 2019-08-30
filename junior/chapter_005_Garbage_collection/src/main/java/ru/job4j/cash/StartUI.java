package ru.job4j.cash;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Программа, эмулирующая поведение абстрактного кеша.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class StartUI {

    /**
     * Запуск программы.
     * @param folder - адрес папки, из которой будут выгружаться текстовые файлы.
     */
    public static void start(String folder) {
        FolderDownloader downloader = new FolderDownloader();
        Cash cash = new Cash();
        try (Scanner in = new Scanner(System.in)) {
            List<String> filesList = downloader.getFilesList(folder);
            if (filesList == null) {
                System.out.printf("Папка %s не существует или пуста%s", folder, System.lineSeparator());
            } else {
                System.out.println("Список файлов в папке " + folder);
                filesList.stream().sorted().forEach(System.out::println);
                do {
                    System.out.print("Выберете имя файла для просмотра: ");
                    String received = cash.receive(folder + "/" + in.next());
                    System.out.println(Objects.requireNonNullElse(received, "Файла с таким именем не существует"));
                    System.out.print("Хотите просмотреть ещё один файл? (y/n): ");
                } while ("y".equals(in.next()));
            }
        }
    }

    public static void main(String[] args) {
        StartUI.start("textfiles");
    }

}
