package ru.gasworkers.dev.model.apiModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
