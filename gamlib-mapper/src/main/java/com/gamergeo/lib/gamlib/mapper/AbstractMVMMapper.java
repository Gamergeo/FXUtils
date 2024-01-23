package com.gamergeo.lib.gamlib.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMVMMapper<M extends MappedModel, V extends MappedViewModel> implements MVMMapper<M, V> {
	
	public List<V> getViewModels(List<M> models) {
    	List<V> viewModels =  new ArrayList<V>();
    	for (M model : models) {
    		viewModels.add(toViewModel(model));
    	}
    	
    	return viewModels;
	}
	
	public abstract V toViewModel(M model);
}
