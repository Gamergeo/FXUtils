package com.gamergeo.lib.viewmodelfx.viewmodel;

import com.gamergeo.lib.viewmodelfx.parameter.Parameter;

/**
 * JavaFX ViewModel class
 */
public interface ViewModel {
	
	/**
	 * @return true if view model has parent
	 */
	default boolean isChild() {
		return false;
	}

	/**
	 * initialization of view model, after load of view
	 */
   default void init() {}

   /**
    * Set initialization parameters
    */
   void setParameters(Parameter<?>... parameters);

}
