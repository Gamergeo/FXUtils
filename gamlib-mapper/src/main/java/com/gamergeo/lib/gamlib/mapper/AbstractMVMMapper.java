package com.gamergeo.lib.gamlib.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMVMMapper<M, V> implements MVMMapper<M, V> {
	
	public List<V> getViewModels(List<M> models) {
    	List<V> viewModels =  new ArrayList<V>();
    	for (M model : models) {
    		viewModels.add(getViewModel(model));
    	}
    	
    	return viewModels;
	}
	
	public abstract V getViewModel(M model);
	
	public List<M> getModels(List<V> viewModels) {
    	List<M> models =  new ArrayList<M>();
    	for (V model : viewModels) {
    		models.add(getModel(model));
    	}
    	
    	return models;
	}
	
	public abstract M getModel(V model);
}
