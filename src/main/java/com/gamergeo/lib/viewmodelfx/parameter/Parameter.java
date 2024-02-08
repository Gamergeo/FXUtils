package com.gamergeo.lib.viewmodelfx.parameter;

public class Parameter<T> {
    private T value;
    
    public Parameter(T value) {
		this.value = value;
	}

	public T get() {
        return this.value;
    }
    
	public Class<?> getParemeterClass() {
    	return value.getClass();
    }

    public void set(T value) {
        this.value = value;
    }
}
