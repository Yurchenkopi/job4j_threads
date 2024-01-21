package ru.job4j.concurrent.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ArrayIndexSearchTest {

    @Test
    public void whenIntegerArrayAndLinearSearchThenFoundIndex() {
        Integer[] data = new Integer[] {23, 142, 432, 112, 123, 12, 324, 123, 1};
        int rsl1 = new ArrayIndexSearch<>(data, 432, 0, data.length - 1).findIndex();
        int rsl2 = new ArrayIndexSearch<>(data, 324, 0, data.length - 1).findIndex();
        assertThat(rsl1).isEqualTo(2);
        assertThat(rsl2).isEqualTo(6);
    }

    @Test
    public void whenStringArrayAndLinearSearchThenFoundIndex() {
        String[] data = new String[] {"one", "two", "three", "four", "five"};
        int rsl1 = new ArrayIndexSearch<>(data, "three", 0, data.length - 1).findIndex();
        int rsl2 = new ArrayIndexSearch<>(data, "four", 0, data.length - 1).findIndex();
        assertThat(rsl1).isEqualTo(2);
        assertThat(rsl2).isEqualTo(3);
    }

    @Test
    public void whenIntegerArrayAndLinearSearchThenNotFoundIndex() {
        Integer[] data = new Integer[] {23, 142, 432, 112, 123, 12, 324, 123, 1};
        int rsl = new ArrayIndexSearch<>(data, 500, 0, data.length - 1).findIndex();
        assertThat(rsl).isEqualTo(-1);
    }

    @Test
    public void whenIntegerArrayAndRecursiveSearchThenFoundIndex() {
        Integer[] data = new Integer[] {
                23, 142, 432, 112, 123, 12, 324, 123, 1, 123,
                567, 1234, 452, 12, 567, 890, 900 ,232, 123
        };
        int rsl1 = new ArrayIndexSearch<>(data, 1234, 0, data.length - 1).findIndex();
        int rsl2 = new ArrayIndexSearch<>(data, 900, 0, data.length - 1).findIndex();
        assertThat(rsl1).isEqualTo(11);
        assertThat(rsl2).isEqualTo(16);
    }

    @Test
    public void whenStringArrayAndRecursiveSearchThenFoundIndex() {
        String[] data = new String[] {
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen"
        };
        int rsl1 = new ArrayIndexSearch<>(data, "seven", 0, data.length - 1).findIndex();
        int rsl2 = new ArrayIndexSearch<>(data, "twelve", 0, data.length - 1).findIndex();
        assertThat(rsl1).isEqualTo(6);
        assertThat(rsl2).isEqualTo(11);
    }

    @Test
    public void whenIntegerArrayAndRecursiveSearchThenNotFoundIndex() {
        Integer[] data = new Integer[] {
                23, 142, 432, 112, 123, 12, 324, 123, 1, 123,
                567, 1234, 452, 12, 567, 890, 900 ,232, 123
        };
        int rsl = new ArrayIndexSearch<>(data, 1222, 0, data.length - 1).findIndex();
        assertThat(rsl).isEqualTo(-1);
    }

}