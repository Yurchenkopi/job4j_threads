package ru.job4j.concurrent.pool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {

    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[] {array[from]};
        }
        int middle = (from + to) / 2;
        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, middle);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, middle + 1, to);
        leftSort.fork();
        rightSort.fork();
        int[] left = leftSort.join();
        int[] right = rightSort.join();
        return MergeSort.merge(left, right);
    }

    public static int[] sort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }

    public static void main(String[] args) {
        int[] data = new int[] {
                23, 142, 432, 112, 123, 12, 324, 123, 1, 123,
                567, 1234, 452, 12, 567, 890, 900, 232, 123
        };
        Arrays.stream(ParallelMergeSort.sort(data)).forEach(System.out::println);
    }
}
