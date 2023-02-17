package ru.gasworkers.dev.model.gasCompany;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NameCompany {
    MOSOBLGAS("Мособлгаз"),
    LENOBLGAS("Леноблгаз"),
    PERMOBLGAS("ПермьОблгаз");
    private final String name;
    @Override
    public String toString() {
        return name;
    }

}

