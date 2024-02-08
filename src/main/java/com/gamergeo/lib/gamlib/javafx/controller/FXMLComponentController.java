²package com.gamergeo.lib.gamlib.javafx.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public @interface FXMLComponentController {

}a
