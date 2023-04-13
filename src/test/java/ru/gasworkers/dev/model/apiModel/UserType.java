package ru.gasworkers.dev.model.apiModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserType {
    CLIENT("client"),
    MASTER("master"),
    SERVICE("service");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
