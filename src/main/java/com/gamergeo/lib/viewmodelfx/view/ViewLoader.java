package com.gamergeo.lib.viewmodelfx.view;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.viewmodelfx.parameter.Parameter;
import com.gamergeo.lib.viewmodelfx.viewmodel.ChildViewModel;
import com.gamergeo.lib.viewmodelfx.viewmodel.ViewModel;

import javafx.scene.Parent;

public class ViewLoader {
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <V extends AbstractFXMLView<? extends ViewModel>> V load(ApplicationContext applicationContext, Class<V> viewClass, Parameter<?>... parameters) {
        V view = (V) ((AbstractFXMLView) applicationContext.getBean(viewClass)).load();
        view.getViewModel().setParameters(parameters);
        view.getViewModel().init();
        return view;
    }

    public static <V extends AbstractFXMLView<? extends ViewModel>> Parent getRoot(ApplicationContext applicationContext, Class<V> viewClass, Parameter<?>... parameters) {
        return load(applicationContext, viewClass, parameters).getRoot();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <V extends AbstractFXMLView<? extends ChildViewModel<? extends ViewModel>>> V load(ApplicationContext applicationContext, Class<V> viewClass, ViewModel parentViewModel, Parameter<?>... parameters) {
        V view = (V) ((AbstractFXMLView) applicationContext.getBean(viewClass)).load(parentViewModel);
        ((ChildViewModel) view.getViewModel()).setParameters(parameters);
        ((ChildViewModel) view.getViewModel()).init();
        return view;
    }

    public static <V extends AbstractFXMLView<? extends ChildViewModel<? extends ViewModel>>> Parent getRoot(ApplicationContext applicationContext, Class<V> viewClass, ViewModel parentViewModel, Parameter<?>... parameters) {
        return load(applicationContext, viewClass, parentViewModel, parameters).getRoot();
    }
}
