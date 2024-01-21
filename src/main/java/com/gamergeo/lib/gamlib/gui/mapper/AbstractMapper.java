package com.gamergeo.lib.gamlib.gui.mapper;

import java.util.ArrayList;
import java.util.List;

import com.gamergeo.lib.gamlib.gui.viewmodel.ViewModel;
import com.gamergeo.lib.gamlib.model.Model;

public abstract class AbstractMapper<M extends Model, V extends ViewModel> implements Mapper<M, V> {
	
	public List<V> getViewModels(List<M> models) {
    	List<V> viewModels =  new ArrayList<V>();
    	for (M model : models) {
    		viewModels.add(toViewModel(model));
    	}
    	
    	return viewModels;
	}
	
	public abstract V toViewModel(M model);
}
