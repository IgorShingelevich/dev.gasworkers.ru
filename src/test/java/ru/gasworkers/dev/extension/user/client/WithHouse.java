package ru.gasworkers.dev.extension.user.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithHouse {
    int addressId() default 2121;
    int companyId() default 1;
    String title() default "My house!";
    WithEquipment[] equipments() default {};
    WithOrder[] orders() default {};
}
