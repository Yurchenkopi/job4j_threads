package ru.job4j.concurrent.pool;

public class ArrayIndexSearch<T> {
    private final T[] array;
    private final T object;
    private final int from;
    private final int to;

    public ArrayIndexSearch(T[] array, T object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    public int findIndex() {
        int rsl;
        if (to - from < 10) {
            LinearIndexSearch<T> linearSearch = new LinearIndexSearch<>(array, object);
            rsl = linearSearch.findIndex();
        } else {
            ParallelIndexSearch<T> parallelSearch = new ParallelIndexSearch<>(array, object, from, to);
            rsl = parallelSearch.findIndex();
        }
        return rsl;
    }
}
