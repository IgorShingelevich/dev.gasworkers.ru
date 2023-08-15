package ru.gasworkers.dev.tests;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.gasworkers.dev.extension.browser.ScreenshotExtension;

import java.util.List;
import java.util.function.Consumer;

@ExtendWith(ScreenshotExtension.class)
public abstract class BaseTest {

    public void assertAll(List<Consumer<SoftAssert>> executables) {
        SoftAssert softAssert = new SoftAssert();
        executables.forEach(executable -> softAssert.assertAll(executable));
        softAssert.assertAll();
    }
}
