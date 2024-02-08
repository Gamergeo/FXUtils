package com.gamergeo.lib.viewmodelfx.listener;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Simple list change listener when final list matters 
 */
public interface SimpleListChangeListener<T> extends ListChangeListener<T> {
	
     @SuppressWarnings("unchecked")
     default void onChanged(Change<? extends T> c) {
          ObservableList<T> list = (ObservableList<T>) c.getList();
          this.onChanged(list);
     }

     void onChanged(ObservableList<T> list);
}
