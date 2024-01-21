package com.gamergeo.lib.gamlib.gui.mapper;

import java.util.List;

import com.gamergeo.lib.gamlib.gui.viewmodel.ViewModel;
import com.gamergeo.lib.gamlib.model.Model;

public interface Mapper<M extends Model, V extends ViewModel> {
	public List<V> getViewModels(List<M> models);
	
	public V toViewModel(M model);
}
