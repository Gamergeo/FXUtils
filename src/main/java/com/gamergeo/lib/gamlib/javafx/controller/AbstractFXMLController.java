package com.gamergeo.lib.gamlib.javafx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;

public class AbstractFXMLController {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object getBean(Class beanClass) {
		return applicationContext.getBean(beanClass);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected FXMLController getController(Class controllerClass) {
//		if applicationContext.isPrototype(controllerClass);
		return (FXMLController) applicationContext.getBean(controllerClass);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected AbstractFXMLView getView(Class viewClass) {
		return (AbstractFXMLView) applicationContext.getBean(viewClass);
	}

	/**
	 * Load fxml file associated to view class
	 */
	@SuppressWarnings({ "rawtypes" })
	protected AbstractFXMLView loadView(Class viewClass) {
		AbstractFXMLView view = getView(viewClass);
		return view.load();
	}
}
