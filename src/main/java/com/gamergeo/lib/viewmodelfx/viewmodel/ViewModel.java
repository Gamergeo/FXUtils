package com.gamergeo.lib.viewmodelfx.viewmodel;

import com.gamergeo.lib.viewmodelfx.parameter.Parameter;

public interface ViewModel {
   default boolean hasParent() {
      return false;
   }

   default void init() {
   }

   void setParameters(Parameter<?>... parameters);
}
