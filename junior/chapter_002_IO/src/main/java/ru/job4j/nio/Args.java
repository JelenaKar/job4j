package ru.job4j.nio;

import com.beust.jcommander.*;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс обработки и валидации параметров.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Args {

    @Parameter(description = "-f - искать по полному имени файла; -m - искать по маске", required = true)
    private String type;

    @Parameter(names = "-d", description = "Директория начала поиска", required = true,
            converter = PathConverter.class, validateWith = PathExists.class)
    private Path root;

    @Parameter(names = "-n", description = "Имя файла или маска", required = true)
    private String name;

    @Parameter(names = "-o", converter = PathConverter.class, validateWith = PathExists.class,
            description = "Файл, куда записывается результаты поиска." + " По умолчанию пишет в консоль.")
    private Path target;

    private JCommander jc = new JCommander();

    public Args(String[] args) {
        jc.addObject(this);
        jc.setProgramName("Поиск файлов");
    }

    public void parse(String[] args) {
        jc.parse(args);
    }

    public void usage() {
        this.jc.usage();
    }

    public String getType() {
        return type;
    }

    public Path getRoot() {
        return root;
    }

    public String getName() {
        return name;
    }

    public Path getTarget() {
        return target;
    }

    public static class PathExists implements IParameterValidator {
        public void validate(String name, String value) throws ParameterException {
            Path path = Path.of(value);
            if ("-o".equals(name)) {
                path = path.getParent();
            }
            if (!(Files.exists(path) && Files.isDirectory(path))) {
                throw new ParameterException("Папки с именем " + path + " не существует!");
            }
        }
    }

    private class PathConverter implements IStringConverter<Path> {
        @Override
        public Path convert(String value) {
            return Path.of(value);
        }
    }
}
