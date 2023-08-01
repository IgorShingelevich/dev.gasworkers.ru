package ru.gasworkers.dev.tests.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

@Tag(AllureTag.SAMPLE_WEB_TEST)
public class SampleWebTest extends BaseTest {
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    @Test
    @DisplayName("sample web test - positive")
    public void positiveWebTest() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
    }

    @Test
    @DisplayName("sample web test 2  - positive")
    public void positiveWebTest2() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
    }

}
