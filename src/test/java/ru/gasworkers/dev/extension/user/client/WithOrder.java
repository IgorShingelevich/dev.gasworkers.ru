package ru.gasworkers.dev.extension.user.client;

import lombok.AllArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithOrder {

    OrderType value();

    @AllArgsConstructor
    enum OrderType {
        REPAIR("repair"),
        MAINTENANCE("maintenance"),
        CONSULTATION("consultation");

        private final String description;

        @Override
        public String toString() {
            return description;
        }
    }

}
