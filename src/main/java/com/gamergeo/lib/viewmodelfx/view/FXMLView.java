package com.gamergeo.lib.viewmodelfx.view;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.viewmodelfx.listener.EmptyChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleListChangeListener;
import com.gamergeo.lib.viewmodelfx.parameter.Parameter;
import com.gamergeo.lib.viewmodelfx.viewmodel.ChildViewModel;
import com.gamergeo.lib.viewmodelfx.viewmodel.ViewModel;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Default implementation to load fxml file
 * FXML file should be in /fxml/ folder, css in /css/ folder
 * Add util method to load child view, {@link com.gamergeo.lib.viewmodelfx.view.ViewLoader}
 */
public class FXMLView<VM extends ViewModel> implements View<VM> {
	
	private static final Logger log = LoggerFactory.getLogger(FXMLView.class);
    private Parent root;
    
    @Autowired
    protected VM viewModel;
    
    @Autowired
    private ApplicationContext applicationContext;
    
	/**
	 * load fxml file associated to view
	 */
	@Override
    public View<VM> load() {
        log.info("Load FXML View: " + String.valueOf(this.getClass()));
        FXMLLoader loader = new FXMLLoader(getURLResource(false));
        loader.setController(this);

        try {
            this.root = (Parent)loader.load();
        } catch (IOException var3) {
            log.error("Error FXML View not found: " + String.valueOf(this.getClass()));
            throw new RuntimeException(var3);
        }

        this.addCSS();
        return this;
    }
	
    private void addCSS() {
        URL uri = this.getURLResource(true);
        if (uri != null) {
            String uriToCss = uri.toExternalForm();
            this.root.getStylesheets().add(uriToCss);
            log.debug("css file added to parent: {}", uriToCss.toString());
        } else {
            log.debug("no css file found for " + String.valueOf(this.getClass()));
        }

    }

    private URL getURLResource(boolean css) {
        String file = stripEnding(this.getClass().getSimpleName().toLowerCase());;
        file = css ? "/css/" + file + ".css" : "/fxml/" + file + ".fxml";
        return this.getClass().getResource(file);
    }

    private static String stripEnding(final String clazz) {
        return !clazz.endsWith("view") ? clazz : clazz.substring(0, clazz.lastIndexOf("view"));
    }

    @Override
    public Parent getRoot() {
        if (this.root == null) {
            String errorMessage = "Root not set, load view first !";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        } else {
            return this.root;
        }
    }

    /**
     * Load a childview
     */
    protected <V extends View<? extends ChildViewModel<? extends ViewModel>>> V load(Class<V> viewClass, Parameter<?>... parameters) {
        return ViewLoader.load(this.applicationContext, viewClass, this.viewModel, parameters);
    }

    /**
     * Load a childview and getroot
     */
    protected <V extends View<? extends ChildViewModel<? extends ViewModel>>> Parent getRoot(Class<V> viewClass, Parameter<?>... parameters) {
        return this.load(viewClass, parameters).getRoot();
    }

    protected <T> void addSimpleChangeListener(ReadOnlyProperty<T> property, SimpleChangeListener<T> listener) {
        ViewUtils.addSimpleChangeListener(property, listener);
    }

    protected <T> void addEmptyChangeListener(ReadOnlyProperty<T> property, EmptyChangeListener<T> listener) {
        ViewUtils.addEmptyChangeListener(property, listener);
    }

    protected <T> void addSimpleListChangeListener(ObservableList<T> list, SimpleListChangeListener<T> listener) {
        ViewUtils.addSimpleListChangeListener(list, listener);
    }

    @Override
    public VM getViewModel() {
        return this.viewModel;
    }
    
    @Override
    public void setViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }
}
