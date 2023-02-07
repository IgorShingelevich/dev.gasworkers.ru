package ru.gasworkers.dev.model.master;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReadyForVideoState {
    READY("Готов к работе"),
    NOT_READY("Завершить смену");

    private final String description;

    @Override
    public String toString() {
        return description;
    }
}
