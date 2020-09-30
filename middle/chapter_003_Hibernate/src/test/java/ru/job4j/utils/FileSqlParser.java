package ru.job4j.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FileSqlParser {
    public static void readSqlFile(File file, Connection connection) throws FileNotFoundException, SQLException {
        try (Scanner read = new Scanner(file)) {
            read.useDelimiter(";");
            while (read.hasNext()) {
                try (Statement statement = connection.createStatement()) {
                    statement.execute(read.next());
                }
            }
        }
    }
}
