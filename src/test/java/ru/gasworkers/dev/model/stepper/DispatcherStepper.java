package ru.gasworkers.dev.model.stepper;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public enum DispatcherStepper {
    NEW_TENDER("tender_title_dispatcher");


    private final String title;
    @Override
    public String toString() {
        return title;
    }
}
