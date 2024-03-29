package ru.gasworkers.dev.extension.user;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.gasworkers.dev.model.apiModel.UserType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(WithThroughUserExtension.class)
public @interface WithThroughUser {
    UserType type() default UserType.CLIENT;
    WithOrderType[] withOrderType() default {};
}
