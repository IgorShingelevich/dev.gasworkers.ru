package ru.gasworkers.dev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ServiceType {
    MAINTENANCE("Техническое обслуживание", "maintenance"),
    REPAIR("Ремонт", "repair"),
    CONSULTATION("Видеоконсультация", "consultation"),
    URGENT("Видеоконсультация сейчас", "urgent");

     final String description;
     @Getter
     final String throughApi;

    @Override
    public String toString() {
        return description;
    }


}
