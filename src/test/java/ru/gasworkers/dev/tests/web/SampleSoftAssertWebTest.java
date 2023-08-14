package ru.gasworkers.dev.tests.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import static org.junit.jupiter.api.Assertions.assertAll;

public class SampleSoftAssertWebTest extends BaseTest {
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    Executable case1 = () -> {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().wrongButton();
        assert true : "sample web test 1 - negative";
    };

    Executable case2 = () -> {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        assert true : "sample web test 2 - positive";
    };

    Executable case3 = () -> {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
        assert true : "sample web test 3 - positive";
    };

    Executable case4 = () -> {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().wrongButton();
        assert true : "sample web test 4 - negative";
    };

    @Test
    @DisplayName("sample softAssert tests")
    public void allPositiveWebTests() {
        assertAll(case1, case2, case3, case4);
    }
}
