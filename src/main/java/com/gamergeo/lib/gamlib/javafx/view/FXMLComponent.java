package com.gamergeo.lib.gamlib.javafx.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Non unique FXML View
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FXMLComponent {

	String value();
}
