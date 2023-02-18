package ru.gasworkers.dev.helpers;

import org.junit.jupiter.api.extension.ExtensionContext;

public final class TestIdentifierHelper {

    public static String getTestIdentifier(ExtensionContext extensionContext) {
        return extensionContext.getRequiredTestClass().getName()
                + ":"
                + extensionContext.getRequiredTestMethod().getName();
    }

}
