package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .isNotNull()
                .contains("Sph");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotEmpty()
                .containsIgnoringCase("cub")
                .endsWith("be");
    }

    @Test
    void whenVertexUnknownThanUnknown() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        String expected = "Unknown object";
        assertThat(name).isEqualTo(expected)
                .isNotEmpty()
                .startsWith("Unknown")
                .endsWith("object");
    }

    @Test
    void whenEdgeNullThanUnknown() {
        Box box = new Box(0, 0);
        String name = box.whatsThis();
        String expected = "Unknown object";
        assertThat(name).isEqualTo(expected)
                .isNotNull()
                .startsWithIgnoringCase("unkn")
                .endsWithIgnoringCase("Object");
    }

    @Test
    void whenTetrahedronThanFourVertices() {
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(4)
                .isGreaterThan(3)
                .isLessThan(5)
                .isEven();
    }

    @Test
    void whenEdgeLessNullThanVertexMinusOne() {
        Box box = new Box(4, -2);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1)
                .isLessThan(0)
                .isNegative();
    }

    @Test
    void whenTypeUnknownThanVertexMinusOne() {
        Box box = new Box(1, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1)
                .isNotZero()
                .isGreaterThan(-2)
                .isNegative();
    }

    @Test
    void whenCubeIsExist() {
        Box box = new Box(8, 10);
        boolean exist = box.isExist();
        assertThat(exist).isNotNull()
                .isTrue();
    }

    @Test
    void whenSphereIsNotExist() {
        Box box = new Box(0, 0);
        boolean exist = box.isExist();
        assertThat(exist).isNotNull()
                .isFalse();
    }

    @Test
    void whenTypeUnknownThanArea0() {
        Box box = new Box(1, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(0)
                .isLessThan(1)
                .isNotNull()
                .isNotNegative();
    }

    @Test
    void whenTetrahedronThanArea173Dot205() {
        Box box = new Box(4, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(173.205, withPrecision(0.001d))
                .isCloseTo(173.20d, Percentage.withPercentage(0.005d))
                .isLessThan(174.0d);
    }

    @Test
    void whenCubeThanArea600() {
        Box box = new Box(8, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(600, withPrecision(0.001d))
                .isLessThan(601)
                .isGreaterThan(599)
                .isCloseTo(599.9d, Percentage.withPercentage(0.1d));
    }
}