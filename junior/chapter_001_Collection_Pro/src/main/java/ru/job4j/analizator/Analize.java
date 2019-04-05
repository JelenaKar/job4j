package ru.job4j.analizator;

import java.util.List;
import java.util.Objects;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        previous.stream().filter(x -> !current.contains(x)).forEach(
                y -> current.stream().filter(x -> x.id == y.id).findAny()
                    .map(x -> result.changed++)
                    .orElseGet(() -> result.deleted++)
        );

        current.stream().filter(x -> !previous.contains(x)).forEach(
                y -> previous.stream()
                        .filter(x -> x.id == y.id).findAny().map(x -> 0)
                        .orElseGet(() -> result.added++)
        );
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
