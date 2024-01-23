package com.gamergeo.lib.gamlib.mapper;

import java.util.List;

public interface MVMMapper<M, V> {
	public List<V> getViewModels(List<M> models);
	
	public V getViewModel(M model);
	
	public List<M> getModels(List<V> viewModels);
	
	public abstract M getModel(V model);
}
