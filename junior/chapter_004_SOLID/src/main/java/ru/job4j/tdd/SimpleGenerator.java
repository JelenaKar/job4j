package ru.job4j.tdd;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс-генератор произвольной строки по шаблону.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class SimpleGenerator {

    private final Pattern pattern = Pattern.compile("\\$\\{.+?}");
    private Set<String> keys;

    public SimpleGenerator(Set<String> keys) {
        this.keys = keys;
    }

    /**
     * Генерирует строку, подставляя в шаблон значения из карты.
     * @param template - шаблон.
     * @param map - карта подставляемых значений.
     * @return результирующую строку с подставленными значениями.
     */
    public String generate(String template, HashMap<String, String> map) throws KeyNotFoundException, RedundantKeyException {
        Set<String> allKeysInMap = new HashSet<>(map.keySet());
        Set<String> allKeysFromTemplate = this.getAllKeysFromTemplate(template);
        Set<String> patternKeys = new HashSet<>(this.keys);
        if (!patternKeys.containsAll(allKeysInMap)) {
            allKeysInMap.removeAll(patternKeys);
            throw new RedundantKeyException("Введённая карта содержит неизвестные ключи: " + allKeysInMap);
        } else if (!patternKeys.containsAll(allKeysFromTemplate)) {
            allKeysFromTemplate.removeAll(patternKeys);
            throw new RedundantKeyException("Шаблон содержит неизвестные ключи: " + allKeysFromTemplate);
        } else if (!allKeysFromTemplate.containsAll(patternKeys)) {
            patternKeys.removeAll(allKeysFromTemplate);
            throw new KeyNotFoundException("В шаблоне отсутстуют обязательные ключи:" + patternKeys);
        }
        for (String key : map.keySet()) {
            Matcher matcher = this.pattern.matcher(template);
            matcher.find();
            String found = template.substring(matcher.start(), matcher.end());
            template = matcher.replaceFirst(map.get(found.substring(2, found.length() - 1)));
        }
        return template;
    }

    private Set<String> getAllKeysFromTemplate(String template) {
        Set<String> result = new HashSet<>();
        Matcher matcher = this.pattern.matcher(template);
        while (matcher.find()) {
            String found = template.substring(matcher.start(), matcher.end());
            result.add(found.substring(2, found.length() - 1));
        }
        return result;
    }
}