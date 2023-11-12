package ru.gasworkers.dev.tests;

import io.qameta.allure.Epic;
import ru.gasworkers.dev.allure.AllureEpic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Epic(AllureEpic.SAMPLE_TEST)

public class SoftAssert {
    private final List<Throwable> failures = new ArrayList<>();
    private int caseNumber = 0; // Counter to keep track of the case number

    public void assertAll(Consumer<SoftAssert> executable) {
        caseNumber++; // Increment case number
        try {
            executable.accept(this);
            System.out.println("Case#" + caseNumber + " is ok"); // Print success message
        } catch (Throwable t) {
            failures.add(t);
            System.out.println("Case#" + caseNumber + " failed: " + t.getMessage()); // Print failure message
        }
    }

    public void assertAll() {
        if (!failures.isEmpty()) {
            StringBuilder message = new StringBuilder("There were " + failures.size() + " failures:");
            int i = 0;
            for (Throwable failure : failures) {
                message.append("\n").append(++i).append(") ").append(failure.getMessage());
                for (StackTraceElement element : failure.getStackTrace()) {
                    message.append("\n\tat ").append(element);
                }
            }
            throw new AssertionError(message.toString());
        }
    }
}
