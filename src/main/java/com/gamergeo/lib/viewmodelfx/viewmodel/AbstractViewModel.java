package com.gamergeo.lib.viewmodelfx.viewmodel;

import java.util.Arrays;
import java.util.List;

import com.gamergeo.lib.viewmodelfx.listener.EmptyChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleListChangeListener;
import com.gamergeo.lib.viewmodelfx.parameter.Parameter;
import com.gamergeo.lib.viewmodelfx.view.ViewUtils;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;

public abstract class AbstractViewModel implements ViewModel {
   protected List<Parameter<?>> parameters;

   public void setParameters(Parameter<?>... parameters) {
      this.parameters = Arrays.asList(parameters);
   }

   protected <T> void addSimpleChangeListener(ReadOnlyObjectProperty<T> property, SimpleChangeListener<T> listener) {
      ViewUtils.addSimpleChangeListener(property, listener);
   }

   protected <T> void addEmptyChangeListener(ReadOnlyProperty<T> property, EmptyChangeListener<T> listener) {
      ViewUtils.addEmptyChangeListener(property, listener);
   }

   protected <T> void addSimpleListChangeListener(ObservableList<T> list, SimpleListChangeListener<T> listener) {
      ViewUtils.addSimpleListChangeListener(list, listener);
   }
}
