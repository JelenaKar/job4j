package ru.job4j.students;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class StudentTest {

    /**
     * Проверка получения ранжированного очищенного списка студентов.
     */
    @Test
    public void whenRangeStudentsThanReceiveSortedCleanedList() {

        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Zujev", 20),
                new Student("Petrov", 20),
                null,
                new Student("Petrov", 15),
                new Student("Ivanov", 15),
                null,
                new Student("Afanasjev", 12),
                null, null,
                new Student("Kotov"),
                new Student("Sidorov", 13),
                null
            )
        );

        List<Student> expected = new ArrayList<>(Arrays.asList(
                new Student("Petrov", 20),
                new Student("Zujev", 20),
                new Student("Ivanov", 15),
                new Student("Petrov", 15),
                new Student("Sidorov", 13)
            )
        );


        assertThat(Student.levelOf(students, 12), is(expected));
    }
}
