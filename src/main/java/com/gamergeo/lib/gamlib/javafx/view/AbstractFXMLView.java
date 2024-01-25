package com.gamergeo.lib.gamlib.javafx.view;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractFXMLView<T> {
	
	@Autowired
	ApplicationContext applicationContext;
	
	T controller;
	
	Node root;
	
	private final URL resource;
	
	/**
	 * Load FXML File
	 */
	public AbstractFXMLView<T> load() {
		log.info("Load FXML View: " + this.getClass());
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory(applicationContext::getBean);
		try {
	        setRoot(loader.load());
		} catch (IOException e) {
			log.error("Error FXML View not found: " + getClass());
			throw new RuntimeException(e);
		}
        
        setController(loader.getController());
        
        return this;
	}
	
	/**
	 * Instantiates a new abstract fxml view.
	 */
	@SuppressWarnings("unchecked")
	public AbstractFXMLView() {
		final Class<? extends AbstractFXMLView<T>> theClass = (Class<? extends AbstractFXMLView<T>>) this.getClass();
		
		// On récupère la valeur de l'annotation, soit view soit component
		FXMLView annotationView = theClass.getAnnotation(FXMLView.class);
		FXMLComponent annotationComponent = theClass.getAnnotation(FXMLComponent.class);
		String annotationValue = annotationView != null ? annotationView.value() : annotationComponent.value();
		
		resource = getURLResource(annotationValue);
	}
	
	/**
	 * Get resource URL
	 */
	private URL getURLResource(String path) {
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return getClass().getResource(path);
	}
	
	public T getController() {
		return controller;
	}

	public void setController(T controller) {
		this.controller = controller;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
}
