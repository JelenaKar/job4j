package ru.job4j.analizator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();

        Map<Integer, User> total = previous.stream()
                .collect(Collectors.toMap(User::getId, user -> user));

        for (User cur : current) {
            User old = total.put(cur.id, cur);
            if (old == null) {
                result.added++;
            } else if (!previous.contains(cur)) {
                result.changed++;
            }
        }

        result.deleted = total.size() - current.size();
        return result;
    }

    public static class User {
        private int id;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return id == user.id && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
