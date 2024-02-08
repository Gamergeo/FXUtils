package com.gamergeo.lib.viewmodelfx.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Simple change listener when only end value matters 
 */
public interface SimpleChangeListener<T> extends ChangeListener<T> {
	
    default void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
        this.changed(newValue);
    }

    void changed(T newValue);
}
