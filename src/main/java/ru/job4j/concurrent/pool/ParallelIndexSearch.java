package ru.job4j.concurrent.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T object;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T[] array, T object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (from == to) {
            if (object.equals(array[from])) {
                return from;
            }
        } else {
            int middle = (from + to) / 2;
            ParallelIndexSearch<T> leftSearch = new ParallelIndexSearch<>(array, object, from, middle);
            ParallelIndexSearch<T> rightSearch = new ParallelIndexSearch<>(array, object, middle + 1, to);
            leftSearch.fork();
            rightSearch.fork();
            return leftSearch.join() != -1 ? leftSearch.join() : rightSearch.join();
        }
        return -1;
    }

    public int findIndex() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, object, 0, array.length - 1));
    }

}
