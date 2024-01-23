package com.gamergeo.lib.gamlib.mapper;

import java.util.List;

public interface MVMMapper<M extends MappedModel, V extends MappedViewModel> {
	public List<V> getViewModels(List<M> models);
	
	public V toViewModel(M model);
}
