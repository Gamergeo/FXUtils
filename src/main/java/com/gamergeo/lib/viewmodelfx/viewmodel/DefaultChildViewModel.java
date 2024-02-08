package com.gamergeo.lib.viewmodelfx.viewmodel;

/**
 * Default child view model implementation
 * Should be inherited by application view model
 */
public abstract class DefaultChildViewModel<VM extends ViewModel> extends DefaultViewModel implements ChildViewModel<VM> {
	protected VM parent;

	@Override
	public void setParent(VM parent) {
		this.parent = parent;
	}
}
