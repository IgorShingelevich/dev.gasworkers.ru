package ru.gasworkers.dev.extension.browser;

import ru.gasworkers.dev.model.Role;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.gasworkers.dev.model.browser.BrowserSize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(BrowserExtension.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Browser {
    Role role();
    BrowserSize browserSize() default BrowserSize.DEFAULT;
    String browserPosition() default "0x0";

}
