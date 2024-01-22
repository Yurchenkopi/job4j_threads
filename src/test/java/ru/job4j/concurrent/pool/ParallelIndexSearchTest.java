package ru.job4j.concurrent.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ParallelIndexSearchTest {

    @Test
    public void whenIntegerArrayAndLinearSearchThenFoundIndex() {
        Integer[] data = new Integer[] {23, 142, 432, 112, 123, 12, 324, 123, 1};
        int rsl1 = ParallelIndexSearch.findIndex(data, 432);
        int rsl2 = ParallelIndexSearch.findIndex(data, 324);
        assertThat(rsl1).isEqualTo(2);
        assertThat(rsl2).isEqualTo(6);
    }

    @Test
    public void whenStringArrayAndLinearSearchThenFoundIndex() {
        String[] data = new String[] {"one", "two", "three", "four", "five"};
        int rsl1 = ParallelIndexSearch.findIndex(data, "three");
        int rsl2 = ParallelIndexSearch.findIndex(data, "four");
        assertThat(rsl1).isEqualTo(2);
        assertThat(rsl2).isEqualTo(3);
    }

    @Test
    public void whenIntegerArrayAndLinearSearchThenNotFoundIndex() {
        Integer[] data = new Integer[] {23, 142, 432, 112, 123, 12, 324, 123, 1};
        int rsl = ParallelIndexSearch.findIndex(data, 500);
        assertThat(rsl).isEqualTo(-1);
    }

    @Test
    public void whenIntegerArrayAndRecursiveSearchThenFoundIndex() {
        Integer[] data = new Integer[] {
                23, 142, 432, 112, 123, 12, 324, 123, 1, 123,
                567, 1234, 452, 12, 567, 890, 900, 232, 123
        };
        int rsl1 = ParallelIndexSearch.findIndex(data, 1234);
        int rsl2 = ParallelIndexSearch.findIndex(data, 900);
        assertThat(rsl1).isEqualTo(11);
        assertThat(rsl2).isEqualTo(16);
    }

    @Test
    public void whenStringArrayAndRecursiveSearchThenFoundIndex() {
        String[] data = new String[] {
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "fifteen"
        };
        int rsl1 = ParallelIndexSearch.findIndex(data, "seven");
        int rsl2 = ParallelIndexSearch.findIndex(data, "twelve");
        assertThat(rsl1).isEqualTo(6);
        assertThat(rsl2).isEqualTo(11);
    }

    @Test
    public void whenIntegerArrayAndRecursiveSearchThenNotFoundIndex() {
        Integer[] data = new Integer[] {
                23, 142, 432, 112, 123, 12, 324, 123, 1, 123,
                567, 1234, 452, 12, 567, 890, 900, 232, 123
        };
        int rsl = ParallelIndexSearch.findIndex(data, 1222);
        assertThat(rsl).isEqualTo(-1);
    }

}