package ru.job4j.concurrent.pool;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int size = matrix.length;
        Sums[] sums = new Sums[size];
        for (int i = 0; i < size; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < size; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            sums[i] = new Sums(rowSum, colSum);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int size = matrix.length;
        Sums[] data = new Sums[size];
        for (int i = 0; i < size; i++) {
            data[i] = getTask(matrix, i).get();
        }
        return data;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int cursor) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int size = matrix.length;
                    int rowSum = 0;
                    int colSum = 0;
                    for (int i = 0; i < size; i++) {
                        rowSum += matrix[cursor][i];
                        colSum += matrix[i][cursor];
                    }
                    return new Sums(rowSum, colSum);
                }
        );
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] data = new int[][]{
                {7, 3, 10, 10, 4},
                {5, 8, 3, 2, 34},
                {4, 11, 6, 1, 6},
                {8, 1, 7, 43, 12},
                {2, 61, 62, 16, 73},
        };
        long startTime = System.currentTimeMillis();
        Arrays.stream(sum(data)).forEach(System.out::println);
        long currentTime = System.currentTimeMillis();
        System.out.println("Simple sum operation time: " + (currentTime - startTime) + " ms");
        System.out.println("----------------");
        startTime = System.currentTimeMillis();
        Arrays.stream(asyncSum(data)).forEach(System.out::println);
        currentTime = System.currentTimeMillis();
        System.out.println("Async sum operation time: " + (currentTime - startTime) + " ms");
    }
}
