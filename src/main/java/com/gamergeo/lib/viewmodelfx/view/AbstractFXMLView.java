package com.gamergeo.lib.viewmodelfx.view;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public abstract class AbstractFXMLView<VM extends ViewModel> {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractFXMLView.class);
    private final ApplicationContext applicationContext;
    private Parent root;
    private final URL resource;
    private FXMLView annotation;
    protected final VM viewModel;

    @SuppressWarnings("unchecked")
	public AbstractFXMLView(ApplicationContext applicationContext, VM viewModel) {
        this.applicationContext = applicationContext;
        this.viewModel = viewModel;
        Class<? extends AbstractFXMLView<VM>> theClass = (Class<? extends AbstractFXMLView<VM>>) this.getClass();
        this.annotation = (FXMLView)theClass.getAnnotation(FXMLView.class);
        this.resource = this.getURLResource(false);
    }

    public AbstractFXMLView<VM> load() {
        log.info("Load FXML View: " + String.valueOf(this.getClass()));
        FXMLLoader loader = new FXMLLoader(this.resource);
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public AbstractFXMLView<VM> load(ViewModel parentViewModel) {
        this.load();
        if (parentViewModel != null) {
            ChildViewModel childViewModel = (ChildViewModel)this.viewModel;
            if (!childViewModel.hasParent()) {
                throw new ClassCastException("ViewModel " + String.valueOf(this.getClass()) + "is not a ChildViewModel !");
            }

            ((ChildViewModel)this.viewModel).setParent(parentViewModel);
        }

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
        String file;
        if (!this.annotation.fxml().isEmpty() & !css) {
            file = this.annotation.fxml();
        } else if (!this.annotation.css().isEmpty() & css) {
            file = this.annotation.css();
        } else {
            file = stripEnding(this.getClass().getSimpleName().toLowerCase());
        }

        file = css ? "/css/" + file + ".css" : "/fxml/" + file + ".fxml";
        return this.getClass().getResource(file);
    }

    private static String stripEnding(final String clazz) {
        return !clazz.endsWith("view") ? clazz : clazz.substring(0, clazz.lastIndexOf("view"));
    }

    public Parent getRoot() {
        if (this.root == null) {
            String errorMessage = "Root not set, load view first !";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        } else {
            return this.root;
        }
    }

    public <V extends AbstractFXMLView<? extends ChildViewModel<? extends ViewModel>>> V load(Class<V> viewClass, Parameter<?>... parameters) {
        return ViewLoader.load(this.applicationContext, viewClass, this.viewModel, parameters);
    }

    public <V extends AbstractFXMLView<? extends ChildViewModel<? extends ViewModel>>> Parent getRoot(Class<V> viewClass, Parameter<?>... parameters) {
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

    public VM getViewModel() {
        return this.viewModel;
    }
}
