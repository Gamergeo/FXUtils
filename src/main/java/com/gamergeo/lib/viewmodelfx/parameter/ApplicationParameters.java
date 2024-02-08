package com.gamergeo.lib.viewmodelfx.parameter;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public interface ApplicationParameters {
	
    Map<String, Parameter<?>> applicationsParameters = new HashMap<String, Parameter<?>>();

    default <T> void addApplicationParameter(String name, Class<T> parameterType) {
        applicationsParameters.put(name, new Parameter<T>());
    }

    @SuppressWarnings({ "rawtypes" })
	default Parameter<?> getApplicationParameter(String name) {
        return (Parameter) applicationsParameters.get(name);
    }

    @PostConstruct
    void initParameters();
}
