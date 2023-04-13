package ru.gasworkers.dev.model.apiModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Employed_status {
    PENDING("pending"),
    ACCEPTED("accepted"),
    SELF_EMPLOYED("self-employed");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
