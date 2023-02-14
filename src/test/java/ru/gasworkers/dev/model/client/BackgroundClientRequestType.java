package ru.gasworkers.dev.model.client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BackgroundClientRequestType {
    MAINTENANCE("Договор технического обслуживания"),
    REPAIR("Вызвать мастера для ремонта"),
    VIDEO("Получить видеоконсультацию");

    private final String type;
    @Override
    public String toString() {
        return type;
    }
}
