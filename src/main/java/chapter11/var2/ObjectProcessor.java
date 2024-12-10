package chapter11.var2;

import java.util.*;
import java.util.stream.Collectors;

public class ObjectProcessor {

    // Метод обработки данных: сортировка и удаление дубликатов
    public static List<ObjectInfo> processObjects(List<ObjectInfo> objects) {
        return objects.stream()
                .sorted(Comparator.comparingInt(ObjectInfo::getCode)) // Сортировка по шифру
                .distinct() // Удаление дубликатов по наименованию
                .collect(Collectors.toList());
    }

    // Класс для хранения объекта с наименованием и шифром
    public static class ObjectInfo {
        private final String name;
        private final int code;

        public ObjectInfo(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ObjectInfo that = (ObjectInfo) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "ObjectInfo{" +
                    "name='" + name + '\'' +
                    ", code=" + code +
                    '}';
        }
    }
}

