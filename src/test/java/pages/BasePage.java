package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import io.qameta.allure.Allure;
import model.Role;
import model.browser.RoleBrowser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import pages.client.SelectPaymentClientPage;
import pages.components.BaseComponent;

import javax.lang.model.type.ArrayType;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public abstract class BasePage extends BaseComponent {

    public BasePage(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement popUpCloseButtonLocator = driver.$(byTagAndText("button", "Прочитать все")).as("Close popup button"),
            popUpContainerLocator = driver.$(".notice-list-fixed-content.gas-scrollbar-inline").as("Pop-up container");

    public void popUpClose3() {
        stepWithRole("Закрыть всплывающие уведомления", () -> {
            try {
                popUpCloseButtonLocator.as("Close popup button").should(appear, Duration.ofSeconds(20)).hover().click();

            } catch (NoSuchElementException e) {
                System.out.println("No pop-up");
            }
        });
    }

    public void popUpClose2() {
        {
            stepWithRole("Закрыть всплывающие уведомления", () -> {
                try {
                    popUpCloseButtonLocator.as("Close popup button").shouldBe(visible, enabled).hover().click();
                } catch (ElementShould e) {
                    System.out.println("No pop-up");
                    e.printStackTrace();
                }
            });
        }
    }

    public void popUpClose() {
        {
            stepWithRole("Закрыть всплывающие уведомления", () -> {
                try {
                    if (popUpCloseButtonLocator.exists()) {
                        popUpCloseButtonLocator.shouldBe(enabled, visible);
                        Selenide.sleep(10000);
                        popUpCloseButtonLocator.click();
                    } else {
                        System.out.println("No pop-up");
                    }
                } catch (ElementShould e) {
                    // Log the exception and proceed with the test
                    System.out.println("Element pop-up was not found or not enabled or not visible.");
                    e.printStackTrace();
                }
            });
        }
    }




}
