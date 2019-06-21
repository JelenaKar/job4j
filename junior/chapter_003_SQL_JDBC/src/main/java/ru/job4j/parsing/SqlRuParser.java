package ru.job4j.parsing;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Класс парсера вакансий.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class SqlRuParser implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(SqlRuParser.class.getName());

    private Connection connection;

    public SqlRuParser(String fileconfig) {
        this.connection = this.init(fileconfig);
    }

    public SqlRuParser(Connection connection) {
        this.connection = connection;
    }

    public Connection init(String link) {
        try (InputStream in = new FileInputStream(link)) {
            Properties config = new Properties();
            config.load(in);
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void addToDB(List<Vacancy> vacancies) throws SQLException {
        try (PreparedStatement prepared = connection.prepareStatement(
                "INSERT INTO vacancy (name, link, text, created) VALUES (?, ?, ?, ?) ON CONFLICT (name) DO NOTHING;")
        ) {
            connection.setAutoCommit(false);
            int count = 0;
            for (int i = 0; i < vacancies.size(); i++) {
                prepared.setString(1, vacancies.get(i).getName());
                prepared.setString(2, vacancies.get(i).getLink());
                prepared.setString(3, vacancies.get(i).getText());
                prepared.setLong(4, vacancies.get(i).getCreated());
                prepared.addBatch();
                count++;
                if (count % 100 == 0 || count == vacancies.size()) {
                    prepared.executeBatch();
                    connection.commit();
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            LOG.error(e.getMessage(), e);
        }
    }

    private boolean isEmpty() throws SQLException {
        boolean res = true;
        try (Statement st = this.connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM vacancy LIMIT 1")) {
            if (rs.next()) {
                res = false;
            }
        }
        return res;
    }

    public void parse(long bound) throws IOException, ParseException, SQLException {
        boolean finish = false;
        int i = 1;
        do {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i++).get();
            Elements rows = doc.select(".forumTable tr");
            String name, link, text;
            long created;
            List<Vacancy> vacancies = new ArrayList<>();
            for (Element el : rows) {
                Elements tmp = el.select("a").eq(0);
                name = tmp.text();
                if (!el.select(".closedTopic").text().equals("[закрыт]")
                        && name.toLowerCase().contains("java")
                        && !name.toLowerCase().contains("javascript")
                        && !name.toLowerCase().contains("java script")) {

                    link = tmp.attr("href");
                    text = Jsoup.connect(link).get().select(".msgBody:nth-child(2)").eq(0).text();
                    created = this.parseDate(el.select(".altCol:last-child").text()).getTime();
                    if (created >= bound) {
                        Vacancy vacancy = new Vacancy(name, text, link, created);
                        vacancies.add(vacancy);
                    } else {
                        finish = true;
                    }
                }
            }

            this.addToDB(vacancies);

        } while (!finish);
    }

    public void parse() throws SQLException, IOException, ParseException {
        Calendar cal = Calendar.getInstance();
        if (this.isEmpty()) {
            cal.set(cal.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        } else {
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - 1, 12, 0, 0);
        }
        this.parse(cal.getTimeInMillis());
    }

    private Date parseDate(String string) throws ParseException {
        String[] tmp = string.split(", ");
        DateFormat date = new SimpleDateFormat("d MMM yy", Locale.forLanguageTag("ru"));
        Calendar cal = Calendar.getInstance();
        if (tmp[0].toLowerCase().contains("сегодня")) {
            tmp[0] = date.format(new Date());
        } else if (string.toLowerCase().contains("вчера")) {
            cal.add(Calendar.DAY_OF_YEAR, -1);
            tmp[0] = date.format(new Date(cal.getTimeInMillis()));
        } else {
            String[] dTemp = tmp[0].split(" ");
            switch (dTemp[1]) {
                case "фев":
                    dTemp[1] = "февр.";
                    break;
                case "май":
                    dTemp[1] = "мая";
                    break;
                case "сен":
                    dTemp[1] = "сент.";
                    break;
                case "ноя":
                    dTemp[1] = "нояб.";
                    break;
                default:
                    dTemp[1] = dTemp[1] + ".";
            }
            tmp[0] = String.join(" ", dTemp);
        }
        String res = String.join(", ", tmp);
        DateFormat datetime = new SimpleDateFormat("d MMM yy, HH:mm", Locale.forLanguageTag("ru"));
        return datetime.parse(res);
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
