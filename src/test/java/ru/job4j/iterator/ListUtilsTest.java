package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, x -> x == 3);
        assertThat(input).hasSize(1).containsSequence(1);
        List<Integer> input2 = new ArrayList<>(Arrays.asList(1, 3, 5, 2));
        ListUtils.removeIf(input2, x -> x >= 3);
        assertThat(input2).hasSize(2).containsSequence(1, 2);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(input, x -> x > 1, 5);
        assertThat(input).hasSize(2).containsSequence(1, 5);
        ListUtils.addAfter(input, 0, 8);
        ListUtils.replaceIf(input, x -> x > 4, 3);
        assertThat(input).hasSize(3).containsSequence(1, 3, 3);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = Arrays.asList(2, 4);
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(3).containsSequence(1, 3, 5);
    }
}