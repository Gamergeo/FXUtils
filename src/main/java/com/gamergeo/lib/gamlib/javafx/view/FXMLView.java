package com.gamergeo.lib.gamlib.javafx.view;

import java.io.IOException;
import java.net.URL;

import org.springframework.core.io.Resource;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;

import javafx.scene.Node;

public interface FXMLView {

	public Resource getResource();
	
	public URL getURL() throws IOException;

	void setController(FXMLController controller);

	FXMLController getController();

	Node getRoot();

	void setRoot(Node root);
	
	String getClasspath();
	
}
