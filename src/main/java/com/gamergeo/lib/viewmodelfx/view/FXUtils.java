package com.gamergeo.lib.viewmodelfx.view;

import com.gamergeo.lib.viewmodelfx.listener.EmptyChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleListChangeListener;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

public class FXUtils {
    public static <T> void bindDirectional(Property<T> property1, Property<T> property2) {
        property2.setValue(property1.getValue());
        property1.addListener((observable, oldValue, newValue) -> {
            property2.setValue(property1.getValue());
        });
        property2.addListener((observable, oldValue, newValue) -> {
            property1.setValue(property2.getValue());
        });
    }

    public static <T> void addSimpleChangeListener(ReadOnlyProperty<T> property, SimpleChangeListener<T> listener) {
        property.addListener(listener);
    }

    public static <T> void addEmptyChangeListener(ReadOnlyProperty<T> property, EmptyChangeListener<T> listener) {
        property.addListener(listener);
    }

    public static <T> void addSimpleListChangeListener(ObservableList<T> list, SimpleListChangeListener<T> listener) {
        list.addListener(listener);
    }
    
    /**
     * Extends fxml view to add a specific css.
     * Use this method to get parent
     */
    public static <ViewType extends FxmlView<? extends ViewModelType>, ViewModelType extends ViewModel> Parent getRoot(Class<ViewType> viewClass) {
    	Parent parent = FluentViewLoader.fxmlView(viewClass).load().getView();
        String resourcePath = classPackageToResourcePath(viewClass);
        parent.getStylesheets().add(resourcePath);
        return parent;
    }
    
    public static String classPackageToResourcePath(Class<?> clazz) {
        String packageName = clazz.getPackage().getName();
        String resourcePath = packageName.replace('.', '/');
        return resourcePath + "/" + clazz.getSimpleName() + ".css";
    }
}
