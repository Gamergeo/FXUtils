package com.gamergeo.lib.viewmodelfx.view;

import com.gamergeo.lib.viewmodelfx.viewmodel.ViewModel;

import javafx.scene.Parent;

/**
 * Java FX MVVM View
 * @param VM assiocated vm class
 */
public interface View<VM extends ViewModel> {

	/**
	 * load fxml file associated to view
	 */
	View<VM> load();

	/**
	 * @return Root node
	 */
	Parent getRoot();

	VM getViewModel();
	void setViewModel(VM viewModel);
}
