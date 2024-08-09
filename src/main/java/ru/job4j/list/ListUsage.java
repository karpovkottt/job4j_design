package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUsage {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<String> list2 = List.of("one", "two", "three");
        list.add("four");
        list.add("five");
        result.add("one");
        result.add("two");
        result.add("three");
        result.add(1, "four");
        result.addAll(2, list);
        result.set(2, "two and second");
        result.replaceAll(String::toUpperCase);
        for (String string : result) {
            System.out.println("Текущий элемент: " + string);
        }
        for (String string2 : list2) {
            System.out.println("Элемент в коллекции list2 : " + string2);
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Вывод элемент с помощью get : " + result.get(i));
        }
        Iterator<String> iterator = result.iterator();
        while (iterator.hasNext()) {
            System.out.println("Вывод с помощью итератора : " + iterator.next());
        }
    }
}
