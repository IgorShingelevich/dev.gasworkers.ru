package ru.gasworkers.dev.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Doc {
    AGREEMENT("Договор ТО"),
    COMPLETION_ACT("Акт выполненных работ"),
    INSURANCE("Страховой полис");
    private final String title;

    @Override
    public String toString() {
        return title;
    }

}
