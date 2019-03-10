package ru.job4j.students;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student implements Comparable<Student> {

    private String name;
    private int scope;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public static List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable).sorted()
                .takeWhile(student -> student.scope > bound)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", scope=" + scope
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return scope == student.scope && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, scope);
    }

    @Override
    public int compareTo(Student student) {
        int k = Integer.compare(student.scope, this.scope);
        return (k != 0) ? k : this.name.compareTo(student.name);
    }
}
