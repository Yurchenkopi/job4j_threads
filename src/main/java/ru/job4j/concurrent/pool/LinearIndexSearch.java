package ru.job4j.concurrent.pool;

public class LinearIndexSearch<T> {
    private final T[] array;
    private final T object;

    public LinearIndexSearch(T[] array, T object) {
        this.array = array;
        this.object = object;
    }

    public int findIndex() {
        int rsl = -1;
        for (int i = 0; i < array.length; i++) {
            if (object.equals(array[i])) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

}
