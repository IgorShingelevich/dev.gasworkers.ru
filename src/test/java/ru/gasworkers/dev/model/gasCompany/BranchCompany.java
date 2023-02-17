package ru.gasworkers.dev.model.gasCompany;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BranchCompany {
    NORTH("Север"),
    SOUTH("Юг"),
    EAST("Восток"),
    WEST("Запад"),
    NORTH_WEST("Северо-Запад"),
    SOUTH_EAST("Юго-Восток");
    private final String name;
    @Override
    public String toString() {
        return name;
    }
}
