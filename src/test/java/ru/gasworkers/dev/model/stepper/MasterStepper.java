package ru.gasworkers.dev.model.stepper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MasterStepper {
    DISPATCHED("master_working_title_master");

    private final String title;

    @Override
    public String toString() {
        return title;
    }
}
