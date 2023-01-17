package tests;

import com.codeborne.selenide.Configuration;
import model.browser.RoleBrowser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class TestBase {




    @BeforeAll
    @DisplayName("Setup browser")
    static void setup() {

        Configuration.baseUrl = "https://dev.gasworkers.ru";
        Configuration.headless = false;
    }

    @DisplayName("Close browser")
    @AfterAll
    static void tearDown() {
        Configuration.holdBrowserOpen = true;

    }







}
