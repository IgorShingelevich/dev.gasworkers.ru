package ru.gasworkers.dev.tests.web;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

@Epic(AllureEpic.SAMPLE_TEST)
@Tag(AllureTag.SAMPLE_WEB_TEST)
public class SampleWebTest extends BaseTest {
    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = "0x0")
    ClientPages clientPages;
    RandomClient randomClient = new RandomClient();

    @Test
    @DisplayName("sample web test 1 - positive")
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

    @Test
    @DisplayName("sample web test 3 - positive")
    public void positiveWebTest3() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
    }

    @Test
    @DisplayName("sample web test 4  - positive")
    public void positiveWebTest4() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
    }

    @Test
    @DisplayName("sample web test 5 - positive")
    public void positiveWebTest5() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
    }

    @Test
    @DisplayName("sample web test 6  - positive")
    public void positiveWebTest6() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
    }

    @Test
    @DisplayName("sample web test 7 - positive")
    public void positiveWebTest7() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
    }

    @Test
    @DisplayName("sample web test 8  - positive")
    public void positiveWebTest8() {
        clientPages.getLandingPage().open();
        clientPages.getLandingPage().checkFinishLoading();
    }

}
