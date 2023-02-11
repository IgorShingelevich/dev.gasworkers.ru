package ru.gasworkers.dev.browser;

import ru.gasworkers.dev.model.Role;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(BrowserExtension.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Browser {
    Role role();
    String browserSize() default "1000x1000";
    String browserPosition() default "0x0";
//    String zoom() default "0.6";

}
