package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SearchTest {

    /**
     * Тестирование ситуации, когда файлы с необходимым расширением присутствуют.
     */
    @Test
    public void whenFilesHaveBeenSearched() {
        String parent = System.getProperty("user.dir") + "/src/main/java/io/tmpdir/";
        Search src = new Search();
        List<File> res = src.files(parent, Arrays.asList("csv", "hrn"));
        assertThat(res.size(), is(3));
        assertThat(res.contains(new File(parent + "file2.hrn")), is(true));
        assertThat(res.contains(new File(parent + "dir1/file55.csv")), is(true));
        assertThat(res.contains(new File(parent + "dir1/dir3/test.csv")), is(true));
    }

    /**
     * Тестирование ситуации, когда файлы с необходимым расширением отсутствуют.
     */
    @Test
    public void whenFilesHaveNotBeenSearched() {
        String parent = System.getProperty("user.dir") + "/src/main/java/io/tmpdir/";
        Search src = new Search();
        List<File> res = src.files(parent, Arrays.asList("php", "docx"));
        assertThat(res.isEmpty(), is(true));
    }
}