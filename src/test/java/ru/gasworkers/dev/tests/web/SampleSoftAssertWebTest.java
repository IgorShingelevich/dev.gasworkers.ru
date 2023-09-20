package ru.gasworkers.dev.tests.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.tests.SoftAssert;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.util.Arrays;
import java.util.function.Consumer;

public class SampleSoftAssertWebTest extends BaseTest {
    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    @Test
    @DisplayName("sample softAssert tests")
    public void softAssertWebTests() {

        Consumer<SoftAssert> case1 = softAssert -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().wrongButton();
        };

        Consumer<SoftAssert> case2 = softAssert -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
        };

        Consumer<SoftAssert> case3 = softAssert -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().checkFinishLoading();
        };

        Consumer<SoftAssert> case4 = softAssert -> {
            clientPages.getLandingPage().open();
            clientPages.getLandingPage().wrongButton();
        };
        assertAll(Arrays.asList(case1, case2, case3, case4));
    }
}
