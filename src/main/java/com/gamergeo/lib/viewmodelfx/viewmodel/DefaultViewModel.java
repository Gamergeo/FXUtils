package com.gamergeo.lib.viewmodelfx.viewmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.gamergeo.lib.viewmodelfx.listener.EmptyChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleChangeListener;
import com.gamergeo.lib.viewmodelfx.listener.SimpleListChangeListener;
import com.gamergeo.lib.viewmodelfx.parameter.Parameter;
import com.gamergeo.lib.viewmodelfx.view.ViewUtils;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;

/**
 * Default view model implementation
 * Should be inherited by application view model
 */
public abstract class DefaultViewModel implements ViewModel {
	
	/**
	 * Parameter are in a map Class, List<Parameter>
	 * Parameter are consumed. So each time a parameter is needed, it will be remove from map
	 * Order is important if several parameter of same class
	 */
	Map<Class<?>, List<Parameter<?>>> parametersMap = new HashMap<Class<?>, List<Parameter<?>>>();

     /**
      * Init parameter
      */
	public void setParameters(Parameter<?>... parameters) {
	    List<Parameter<?>> parametersList = Arrays.asList(parameters);
	    
	    parametersList.forEach((parameter) -> {
		    
		    Class<?> parameterClass = parameter.getParemeterClass();
		    if (this.parametersMap.containsKey(parameterClass)) {
			    this.parametersMap.get(parameterClass).add(parameter);
		    } else {
		    	List<Parameter<?>> entry = new ArrayList<Parameter<?>>();
		    	entry.add(parameter);
			    this.parametersMap.put(parameterClass, entry);
		    }
	    });
    }

    /**
     * {@link com.gamergeo.lib.viewmodelfx.view.ViewUtils#addSimpleChangeListener}
     */
    protected <T> void addSimpleChangeListener(ReadOnlyObjectProperty<T> property, SimpleChangeListener<T> listener) {
        ViewUtils.addSimpleChangeListener(property, listener);
    }

    /**
     * {@link com.gamergeo.lib.viewmodelfx.view.ViewUtils#addEmptyChangeListener}
     */
    protected <T> void addEmptyChangeListener(ReadOnlyProperty<T> property, EmptyChangeListener<T> listener) {
        ViewUtils.addEmptyChangeListener(property, listener);
    }

    /**
     * {@link com.gamergeo.lib.viewmodelfx.view.ViewUtils#addSimpleListChangeListener}
     */
    protected <T> void addSimpleListChangeListener(ObservableList<T> list, SimpleListChangeListener<T> listener) {
        ViewUtils.addSimpleListChangeListener(list, listener);
    }
    
    @SuppressWarnings("unchecked")
	protected <T> T getParameter(Class<T> parameterClass) {

	    if (!this.parametersMap.containsKey(parameterClass) || this.parametersMap.get(parameterClass).isEmpty()) {
	    	throw new NoSuchElementException("Parameter for class " + parameterClass + " not found");
	    }
	    
	    return (T) this.parametersMap.get(parameterClass).removeFirst().get();
    }
}
