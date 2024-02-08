package com.gamergeo.lib.viewmodelfx.view;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.viewmodelfx.parameter.Parameter;
import com.gamergeo.lib.viewmodelfx.viewmodel.ChildViewModel;
import com.gamergeo.lib.viewmodelfx.viewmodel.ViewModel;

import javafx.scene.Parent;

/**
 * Helper method to load view / viewmodel
 */
public class ViewLoader {
	
	/**
	 * Load View (fxml) and init view model
	 * @param viewClass view class needed
	 * @param parameters view model parameters
	 */
    @SuppressWarnings({ "unchecked" })
	public static <V extends View<? extends ViewModel>> V load(ApplicationContext applicationContext, Class<V> viewClass, Parameter<?>... parameters) {
        V view = (V) ((V) applicationContext.getBean(viewClass)).load();
        view.getViewModel().setParameters(parameters);
        view.getViewModel().init();
        return view;
    }

	/**
	 * Load View (fxml) and init view model and getRoot
	 * @param viewClass view class needed
	 * @param parameters view model parameters
	 */
    public static <V extends View<? extends ViewModel>> Parent getRoot(ApplicationContext applicationContext, Class<V> viewClass, Parameter<?>... parameters) {
        return load(applicationContext, viewClass, parameters).getRoot();
    }

	/**
	 * Load View (fxml) and init Child view model
	 * parent view model is associated with model
	 * @param viewClass view class needed
	 * @param parameters view model parameters
	 */
	@SuppressWarnings("unchecked")
	public static <V extends View<? extends ChildViewModel<? extends ViewModel>>> V load(ApplicationContext applicationContext, Class<V> viewClass, ViewModel parentViewModel, Parameter<?>... parameters) {
        V view = (V) ((V) applicationContext.getBean(viewClass)).load();
        view.getViewModel().setParameters(parameters);
        
        if (parentViewModel != null) {
        	ChildViewModel<ViewModel> childViewModel = (ChildViewModel<ViewModel>) view.getViewModel();
        	
        	if (!childViewModel.isChild()) {
        		throw new ClassCastException("ViewModel " + viewClass + "is not a ChildViewModel !");
        	}
        	childViewModel.setParent(parentViewModel);
        }
        view.getViewModel().init();
        return view;
    }
	/**
	 * 
	 * Load View (fxml) and init Child view model and getroot
	 * parent view model is associated with model
	 * @param viewClass view class needed
	 * @param parameters view model parameters
	 */
	public static <V extends View<? extends ChildViewModel<? extends ViewModel>>> Parent getRoot(ApplicationContext applicationContext, Class<V> viewClass, ViewModel parentViewModel, Parameter<?>... parameters) {
        return load(applicationContext, viewClass, parentViewModel, parameters).getRoot();
    }

}
