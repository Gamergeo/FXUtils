package com.gamergeo.lib.viewmodelfx.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Empty change listener when no values matter
 */
public interface EmptyChangeListener<T> extends ChangeListener<T> {
    
	default void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
        this.changed();
    }

    void changed();
}