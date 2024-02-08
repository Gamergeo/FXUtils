package com.gamergeo.lib.viewmodelfx.parameter;

public class Parameter<T> {
    private T value;

    public T get() {
        return this.value;
    }

    public void set(T value) {
        this.value = value;
    }
}
