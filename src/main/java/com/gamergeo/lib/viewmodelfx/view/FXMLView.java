package com.gamergeo.lib.viewmodelfx.view;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Retention(RetentionPolicy.RUNTIME)
public @interface FXMLView {
   String value() default "";

   String fxml() default "";

   String css() default "";
}
