package ru.job4j.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Класс-загрузчик файлов с выбранной скоростью.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Downloader {
    /**
     * Скачивает файл с ограничением скорости.
     * @param add URL-адрес.
     * @param name имя загружаемого файла.
     * @param kbytes скорость в килобайтах.
     * @throws IOException
     */
    public void downloadFile(String add, String name, int kbytes) throws IOException {
        URL url = new URL(add);
        URLConnection con = url.openConnection();
        try (InputStream inputStream = con.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(name)) {
            int bytesRead;
            int downloaded = 0;
            long time = System.currentTimeMillis();
            byte[] buffer = new byte[1024 * kbytes];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                downloaded += bytesRead;
                if (downloaded >= (kbytes * 1024)) {
                    long current = System.currentTimeMillis();
                    if (current - time < 1000) {
                        Thread.sleep(1000 - (current - time));
                        downloaded = 0;
                        time = System.currentTimeMillis();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Downloader downloader = new Downloader();
        try {
            long time = System.currentTimeMillis();
            downloader.downloadFile(args[0], args[1], Integer.parseInt(args[2]));
            System.out.println(System.currentTimeMillis() - time);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong parameters!");
            System.out.println("Expected: String URL-address, String outputfile, int kbytes");
        }
    }
}
