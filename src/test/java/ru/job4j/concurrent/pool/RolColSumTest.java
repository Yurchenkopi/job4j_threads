package ru.job4j.concurrent.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    public void whenSizeThreeAndSimpleSum() {
        int[][] data = new int[][]{
                {7, 3, 10},
                {5, 8, 3},
                {4, 11, 6}
        };
        RolColSum.Sums[] expected = new RolColSum.Sums[] {
                new RolColSum.Sums(20, 16),
                new RolColSum.Sums(16, 22),
                new RolColSum.Sums(21, 19)
        };
        assertThat(RolColSum.sum(data)).containsExactly(expected);
    }

    @Test
    public void whenSizeFiveAndSimpleSum() {
        int[][] data = new int[][]{
                {7, 3, 10, 10, 4},
                {5, 8, 3, 2, 34},
                {4, 11, 6, 1, 6},
                {8, 1, 7, 43, 12},
                {2, 61, 62, 16, 73},
        };
        RolColSum.Sums[] expected = new RolColSum.Sums[] {
                new RolColSum.Sums(34, 26),
                new RolColSum.Sums(52, 84),
                new RolColSum.Sums(28, 88),
                new RolColSum.Sums(71, 72),
                new RolColSum.Sums(214, 129)

        };
        assertThat(RolColSum.sum(data)).containsExactly(expected);
    }

    @Test
    public void whenSizeThreeAndAsyncSum() throws ExecutionException, InterruptedException {
        int[][] data = new int[][]{
                {7, 3, 10},
                {5, 8, 3},
                {4, 11, 6}
        };
        RolColSum.Sums[] expected = new RolColSum.Sums[] {
                new RolColSum.Sums(20, 16),
                new RolColSum.Sums(16, 22),
                new RolColSum.Sums(21, 19)
        };
        assertThat(RolColSum.asyncSum(data)).containsExactly(expected);
    }

    @Test
    public void whenSizeFiveAndAsyncSum() throws ExecutionException, InterruptedException {
        int[][] data = new int[][]{
                {7, 3, 10, 10, 4},
                {5, 8, 3, 2, 34},
                {4, 11, 6, 1, 6},
                {8, 1, 7, 43, 12},
                {2, 61, 62, 16, 73},
        };
        RolColSum.Sums[] expected = new RolColSum.Sums[] {
                new RolColSum.Sums(34, 26),
                new RolColSum.Sums(52, 84),
                new RolColSum.Sums(28, 88),
                new RolColSum.Sums(71, 72),
                new RolColSum.Sums(214, 129)

        };
        assertThat(RolColSum.asyncSum(data)).containsExactly(expected);
    }

}