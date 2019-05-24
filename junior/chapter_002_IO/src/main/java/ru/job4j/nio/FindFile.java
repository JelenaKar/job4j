package ru.job4j.nio;

import com.beust.jcommander.ParameterException;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс поиска файлов.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class FindFile {

    private Path root;
    private String name;
    private String fnSearch;
    private Path target;
    private List<String> result = new ArrayList<>();

    public FindFile(Args args) {
        this.root = args.getRoot();
        this.name = args.getName();
        this.fnSearch = args.getType();
        this.target = args.getTarget();
    }

    /**
     * Метод, совершающий поиск файла по критериям и печатающий результат в консоль или в файл.
     * @param args - параметры командной строки.
     */
    public static void search(String[] args) throws IOException {
        Args myArgs = null;
        try {
            myArgs = new Args(args);
            myArgs.parse(args);
            FindFile ff = new FindFile(myArgs);
            Files.walkFileTree(ff.root, ff.new MyFileVisitor());
            ff.printResult();
        } catch (ParameterException pe) {
            System.out.println(pe.getMessage());
            myArgs.usage();
        }
    }

    private void printResult() throws IOException {
        PrintWriter br;
        if (this.target != null) {
            br = new PrintWriter(new FileWriter(this.target.toString()));
        } else {
            br = new PrintWriter(new OutputStreamWriter(System.out));
        }
        for (String line : this.result) {
            br.println(line);
        }
        br.close();
    }

    public static void main(String[] args) {
        try {
            FindFile.search(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyFileVisitor extends SimpleFileVisitor<Path> {
        private DirectoryStream.Filter<Path> fullname = path -> path.endsWith(name);

        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            DirectoryStream<Path> stream;
            if ("-f".equals(fnSearch)) {
                stream = Files.newDirectoryStream(dir, fullname);
            } else {
                stream = Files.newDirectoryStream(dir, name);
            }
            for (Path path : stream) {
                result.add(path.toString());
            }
            stream.close();
            return FileVisitResult.CONTINUE;
        }
    }
}