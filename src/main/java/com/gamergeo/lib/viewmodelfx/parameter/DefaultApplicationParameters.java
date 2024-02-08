package com.gamergeo.lib.viewmodelfx.parameter;

import java.util.HashMap;
import java.util.Map;

public abstract class DefaultApplicationParameters {
    private Map<String, Parameter<?>> applicationsParameters = new HashMap<String, Parameter<?>>();

    public DefaultApplicationParameters() {
        this.initParameters();
    }

    protected <T> void addApplicationParameter(String name, Class<T> parameterType) {
        this.applicationsParameters.put(name, new Parameter<T>());
    }

    protected Parameter<?> getApplicationParameter(String name) {
        return (Parameter<?>) this.applicationsParameters.get(name);
    }

    public abstract void initParameters();
}
