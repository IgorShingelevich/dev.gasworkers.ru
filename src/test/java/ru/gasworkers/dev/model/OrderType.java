package ru.gasworkers.dev.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderType {
    MAINTENANCE("Техническое обслуживание", "maintenance"),
    REPAIR("Ремонт", "repair"),
    VIDEO("Видеоконсультация","consultation");

    private final String description;
    private final String throughApi;

    @Override
    public String toString() {
        return description;
    }

    public String getThroughApi() {
        return throughApi;
    }


}
