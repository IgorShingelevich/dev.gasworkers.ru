package ru.gasworkers.dev.model.browser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BrowserSize {
    FIRST_ROLE("1920x1080"),
    SECOND_ROLE("360x640"),
    THIRD_ROLE("360x640"),
    FOURTH_ROLE("360x640"),
    DEFAULT("1000x800");

    private final String size;

    @Override
    public String toString() {
        return size;
    }
}
