package ru.job4j.cash;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FolderDownloaderTest {

    /**
     * Тест выгрузки файлов из несуществующей папки.
     */
    @Test
    public void whenRequestWrongFolderAddressThenReturnNull() {
        FolderDownloader downloader = new FolderDownloader();
        List<String> expected = null;
        assertThat(downloader.getFilesList("wrongFolder"), is(expected));
    }

    /**
     * Тест выгрузки файлов из пустой папки.
     */
    @Test
    public void whenRequestEmptyFolderAddressThenReturnNull() {
        FolderDownloader downloader = new FolderDownloader();
        List<String> expected = null;
        assertThat(downloader.getFilesList("empty"), is(expected));
    }

    /**
     * Тест выгрузки файлов из существующей папки.
     */
    @Test
    public void whenRequestExistingFolderAddressThenReturnListOfFilenames() {
        FolderDownloader downloader = new FolderDownloader();
        List<String> textfiles = downloader.getFilesList("textfiles");
        assertTrue(textfiles.contains("lorum.txt"));
        assertTrue(textfiles.contains("test.txt"));
        assertTrue(textfiles.contains("Радуга.txt"));
    }

}