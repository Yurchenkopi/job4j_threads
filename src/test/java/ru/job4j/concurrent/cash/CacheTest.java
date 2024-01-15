package ru.job4j.concurrent.cash;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {
    @Test
    public void whenAddFind() {
        var base = new Base(1,  "Base", 1);
        var cache = new Cache();
        cache.add(base);
        var find = cache.findById(base.id());
        assertThat(find.get().name())
                .isEqualTo("Base");
    }

    @Test
    public void whenAddUpdateFind() throws OptimisticException {
        var base = new Base(1, "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(new Base(1, "Base updated", 1));
        var find = cache.findById(base.id());
        assertThat(find.get().name())
                .isEqualTo("Base updated");
    }

    @Test
    public void whenAddUpdateThenCheckVersion() throws OptimisticException {
        var base = new Base(1, "Base", 3);
        var cache = new Cache();
        cache.add(base);
        cache.update(new Base(1, "Base updated", 3));
        int expected = 4;
        var find = cache.findById(base.id()).get().version();
        assertThat(find)
                .isEqualTo(expected);
    }

    @Test
    public void whenAddDeleteFind() {
        var base = new Base(1,   "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.delete(1);
        var find = cache.findById(base.id());
        assertThat(find.isEmpty()).isTrue();
    }

    @Test
    public void whenMultiUpdateThrowException() throws OptimisticException {
        var base = new Base(1,  "Base", 1);
        var cache = new Cache();
        cache.add(base);
        cache.update(base);
        assertThatThrownBy(() -> cache.update(base))
                .isInstanceOf(OptimisticException.class);
    }
}