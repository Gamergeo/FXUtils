package com.gamergeo.lib.gamlib.javafx.view;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractFXMLView {
	
	@Autowired
	ApplicationContext applicationContext;
	
	FXMLController controller;
	
	Node root;
	
	private final URL resource;
	
	/**
	 * Load FXML File
	 */
	public AbstractFXMLView load() {
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
	public AbstractFXMLView() {
		final Class<? extends AbstractFXMLView> theClass = this.getClass();
		
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
	
	public FXMLController getController() {
		return controller;
	}

	public void setController(FXMLController controller) {
		this.controller = controller;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
}
