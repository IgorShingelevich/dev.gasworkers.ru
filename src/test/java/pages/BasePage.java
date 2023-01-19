package pages;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import model.Role;
import model.browser.RoleBrowser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import pages.client.SelectPaymentClientPage;
import pages.components.BaseComponent;

import java.time.Duration;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public abstract class BasePage extends BaseComponent {

    public BasePage(RoleBrowser browser) {
        super(browser);
    }

//    @DisplayName("Close the popup notifications")

    SelenideElement
        popUpCloseButtonLocator = driver.$(byTagAndText("button", "Прочитать все")),
        popUpContainerLocator =  driver.$(".notice-list-fixed-content.gas-scrollbar-inline");

    public void popUpClose() {
        stepWithRole("Закрыть всплывающие уведомления", () -> {
            try {
                if ( popUpCloseButtonLocator.isDisplayed() && popUpCloseButtonLocator.isEnabled())
                {
                    popUpCloseButtonLocator.as("Close popup button").should(appear, Duration.ofSeconds(20)).hover().click();
//                    popUpContainerLocator.as("Popup body").shouldNotBe(visible, Duration.ofSeconds(20)).click();
                }

            } catch (NoSuchElementException e) {
                System.out.println("No pop-up");
            }
        });

    }

}
