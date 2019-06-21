package ru.job4j.parsing;

/**
 * Класс вакансии.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class Vacancy {
    private String name;
    private String text;
    private String link;
    private long created;

    public Vacancy(String name, String text, String link, long created) {
        this.name = name;
        this.text = text;
        this.link = link;
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public long getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return "Vacancy{" + "name='" + name + '\'' + ", text='" + text + '\''
                + ", link='" + link + '\'' + ", created=" + created + '}';
    }
}
