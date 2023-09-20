package ru.gasworkers.dev.extension.browser;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.gasworkers.dev.model.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(BrowserExtension.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Browser {
    UserRole role();

    String browserSize() default "1920x1080";
    String browserPosition() default "0x0";

}
