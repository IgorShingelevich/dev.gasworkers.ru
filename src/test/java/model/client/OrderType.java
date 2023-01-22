package model.client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderType {
    MAINTENANCE("Техническое обслуживание"),
    REPAIR("Ремонт"),
    VIDEO("Видеоконсультация");

    private final String description;

    @Override
    public String toString() {
        return description;
    }


}
