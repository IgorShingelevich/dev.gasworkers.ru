package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.profilePages.clientPages.HomeClientPage;

import static com.codeborne.selenide.Selenide.open;

public class ClientDispatcherInteractionTest {

    LoginPage loginPage = new LoginPage();
    HomeClientPage homeClientPage = new HomeClientPage();

    String
            emailClient = "shingelevich@gmail.com",
            passwordClient = "123456";

    String
            emailDispatcher = "test_gas_disp9@rambler.ru",
            passwordDispatcher = "123456";

    String
            emailMaster = "test_gas_master1@rambler.ru",
            passwordMaster = "123456";

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://dev.gasworkers.ru";
    }

    @Test
    public void testLoginTwoRoles() {

        ChromeDriver driver1 = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver1);
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "0x0";
        open("/login");
        loginPage.login(emailClient, passwordClient);

        ChromeDriver driver2 = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver2);
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "1920x0";
        open("/login");
        loginPage.login(emailDispatcher, passwordDispatcher);

        ChromeDriver driver3 = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver3);
        open("/login");
        loginPage.login(emailMaster, passwordMaster);

        //set variables to all 3 drivers to  switch between them any time
        ChromeDriver driverClient = driver1;
        ChromeDriver driverDispatcher = driver2;
        ChromeDriver driverMaster = driver3;
         //switch to client driver from any other active driver
        WebDriverRunner.setWebDriver(driverClient);
        homeClientPage.actionBlock.logout();
        //switch to dispatcher driver from any other active driver
        WebDriverRunner.setWebDriver(driverDispatcher);
        //switch to Client driver and put the browser on top of Master and Dispatcher opened browsers








    }

}
