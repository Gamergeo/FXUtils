package com.gamergeo.lib.gamlib.javafx.view;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.lib.gamlib.javafx.loader.ApplicationLoader;

import javafx.scene.Node;

public abstract class AbstractFXMLView implements FXMLView {
	
	@Autowired
	ApplicationLoader applicationLoader;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	FXMLController controller;
	
	Node root;

	@Override
	public URL getURL() throws IOException {
		return getResource().getURL();
	}

	@Override
	public Resource getResource() {
		return resourceLoader.getResource("classpath:" + getClasspath());
	}

	@Override
	public FXMLController getController() {
		return controller;
	}

	@Override
	public void setController(FXMLController controller) {
		this.controller = controller;
	}

	@Override
	public Node getRoot() {
		return root;
	}

	@Override
	public void setRoot(Node root) {
		this.root = root;
	}
}
