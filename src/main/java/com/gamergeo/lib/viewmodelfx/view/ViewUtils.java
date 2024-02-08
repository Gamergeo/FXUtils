package com.gamergeo.lib.viewmodelfx.view;

import com.gamergeo.lib.viewmodelfx.listener.EmptyChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleListChangeListener;

import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;

public class ViewUtils {
   public static <T> void bindDirectional(Property<T> property1, Property<T> property2) {
      property2.setValue(property1.getValue());
      property1.addListener((observable, oldValue, newValue) -> {
         property2.setValue(property1.getValue());
      });
      property2.addListener((observable, oldValue, newValue) -> {
         property1.setValue(property2.getValue());
      });
   }

   public static <T> void addSimpleChangeListener(ReadOnlyProperty<T> property, SimpleChangeListener<T> listener) {
      property.addListener(listener);
   }

   public static <T> void addEmptyChangeListener(ReadOnlyProperty<T> property, EmptyChangeListener<T> listener) {
      property.addListener(listener);
   }

   public static <T> void addSimpleListChangeListener(ObservableList<T> list, SimpleListChangeListener<T> listener) {
      list.addListener(listener);
   }
}
