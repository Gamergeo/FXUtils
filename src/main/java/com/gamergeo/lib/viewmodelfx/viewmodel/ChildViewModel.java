package com.gamergeo.lib.viewmodelfx.viewmodel;

public interface ChildViewModel<VM extends ViewModel> extends ViewModel {

	void setParent(VM parent);
}
