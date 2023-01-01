package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://dev.gasworkers.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    @AfterAll
    static void tearDown() {
//        Configuration.holdBrowserOpen = true;
//        Configuration.timeout = 6000;

    }




}
