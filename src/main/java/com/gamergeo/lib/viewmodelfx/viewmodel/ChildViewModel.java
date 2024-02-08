package com.gamergeo.lib.viewmodelfx.viewmodel;

/**
 * JavaFX ViewModel class With a parent view model class
 * @param <VM> class of parent view model
 */
public interface ChildViewModel<VM extends ViewModel> extends ViewModel {

	void setParent(VM parent);
	
	/**
	 * @return true if view model has parent
	 */
	@Override
	default boolean isChild() {
		return true;
	}

}
