package com.gamergeo.lib.viewmodelfx.parameter;

public interface ApplicationParameters {
	
    <T> void addApplicationParameter(String name, Class<T> parameterType);

	Parameter<?> getApplicationParameter(String name);

}
