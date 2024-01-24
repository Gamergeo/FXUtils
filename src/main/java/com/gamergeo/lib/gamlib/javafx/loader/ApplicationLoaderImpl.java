package com.gamergeo.lib.gamlib.javafx.loader;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gamergeo.lib.gamlib.javafx.view.FXMLView;

import javafx.fxml.FXMLLoader;

@Service
public class ApplicationLoaderImpl implements ApplicationLoader {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FXMLView createView(Class viewClass) throws IOException {
		if (!FXMLView.class.isAssignableFrom(viewClass)) {
			throw new RuntimeException(viewClass + " is not an instance of FXMLView");
		}
		FXMLView fxmlView = (FXMLView) applicationContext.getBean(viewClass);
        FXMLLoader loader = new FXMLLoader(fxmlView.getURL());
        loader.setControllerFactory(applicationContext::getBean);
        
        fxmlView.setRoot(loader.load());
        fxmlView.setController(loader.getController());
        
        return fxmlView;
	}
	
	
//	@Override
//	public FXMLView getView(Class<FXMLView> viewClass) {
//		FXMLView fxmlView = applicationContext.getBean(viewClass);
//        FXMLLoader loader = getLoader(fxmlView.getFXMLPath());
//		return load(loader);
//	}
//	
//	@Override
//	public Node load(FXMLView view) {
//		log.info("Load FXML for: " + view.getFXMLPath());
//        FXMLLoader loader = getLoader(view.getFXMLPath());
//		return load(loader);
//	}
//	
//	@Override
//	public Node load(String name) {
//		log.info("Load FXML for: " + name);
//        FXMLLoader loader = getLoader(name);
//		return load(loader);
//	}
//	
//	@Override
//	public Node load(FXMLLoader loader) {
//        Node node;
//		try {
//			node = loader.load();
//		} catch(IOException e) {
//			log.error("Can't parse FXML File:" + loader.getLocation().toString());
//			throw new RuntimeException(e);
//		}
//		
//		return node;
//	}
//	
//	@Override
//	public FXMLLoader getLoader(String name) {
//		log.info("Require FXML Loader for: " + name);
//		FXMLLoader loader;
//		
//		try {
//			loader =  new FXMLLoader(resourceLoader.getResource("classpath:fxml/" + name + ".fxml").getURL());
//		} catch(IOException e) {
//			log.error("FXML file not found:" + name);
//			throw new RuntimeException(e);
//		}
//		
//        loader.setControllerFactory(applicationContext::getBean);
//        return loader;
//	}

}
