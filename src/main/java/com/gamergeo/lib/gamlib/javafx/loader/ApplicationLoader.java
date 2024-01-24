package com.gamergeo.lib.gamlib.javafx.loader;

import java.io.IOException;

import com.gamergeo.lib.gamlib.javafx.view.FXMLView;

public interface ApplicationLoader {

	FXMLView createView(Class viewClass) throws IOException;

//	Node load(String name);
//
//	FXMLLoader getLoader(String name);
//
//	Node load(FXMLLoader loader);
//
//	Node load(FXMLView view);
//
//	Node load(Class<FXMLView> view);
//
//	FXMLView getView(Class<FXMLView> viewClass);
}
