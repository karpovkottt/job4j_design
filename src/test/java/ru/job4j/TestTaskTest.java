package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TestTaskTest {
    @Test
    void whenStr1EqualsStr2ThenYes() {
        TestTask test = new TestTask();
        String str1 = "hellochild";
        String str2 = "helto<left><bspace>l<delete>ochilds<bspace>";
        String result = test.checkStrings(str1, str2);
        assertThat(result).isEqualTo("Yes");
    }

    @Test
    void whenStr1NotEqualsStr2ThenNo() {
        TestTask test = new TestTask();
        String str1 = "programming";
        String str2 = "programming<left><left><right><delete>";
        String result = test.checkStrings(str1, str2);
        assertThat(result).isEqualTo("No");
    }

    @Test
    void whenOutOfBoundsAndStr1EqualsStr2ThenYes() {
        TestTask test = new TestTask();
        String str1 = "abcde";
        String str2 = "<left><bspace>abcde<right><delete>";
        String result = test.checkStrings(str1, str2);
        assertThat(result).isEqualTo("Yes");
    }

    @Test
    void whenOutOfBoundsAndStr1NotEqualsStr2ThenNo() {
        TestTask test = new TestTask();
        String str1 = "abcde";
        String str2 = "<left><left><right><delete>";
        String result = test.checkStrings(str1, str2);
        assertThat(result).isEqualTo("No");
    }

}