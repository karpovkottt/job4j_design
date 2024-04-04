package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("five")
                .doesNotContain("six", "seven")
                .startsWith("first")
                .endsWith("five")
                .allMatch(e -> e.length() > 3);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "first" , "three", "four", "four", "five");
        assertThat(set).containsExactlyInAnyOrder("first", "second", "three", "four", "five")
                .doesNotContain("ten", "one")
                .doesNotHaveDuplicates()
                .anySatisfy(e -> assertThat(e).hasSizeLessThan(7));
        assertThat(set).filteredOn(e -> e.length() < 5).containsExactlyInAnyOrder("four", "five");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("zero", "first", "second", "three", "four", "five");
        assertThat(map).hasSize(6)
                .containsEntry("three", 3)
                .doesNotContainKey("ten")
                .doesNotContainValue(10)
                .containsKeys("second", "five");
        assertThat(map).isNotEmpty()
                .allSatisfy((s, n) -> {
                    assertThat(s.length()).isLessThan(7).isGreaterThan(3);
                    assertThat(n).isBetween(0, 6);
                });
    }
}