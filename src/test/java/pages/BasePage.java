package pages;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Allure;
import model.Role;
import model.browser.RoleBrowser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public abstract class BasePage extends BaseComponent {

    public BasePage(RoleBrowser browser) {
        super(browser);
    }

//    @DisplayName("Close the popup notifications")

    public void popUpClose() {
        stepWithRole("Закрыть всплывающие уведомления", () -> {
            try {
                driver.refresh();
                driver.$(".d-flex.mb-2.justify-content-end button").shouldBe(visible, Duration.ofSeconds(20)).click();
                // $(".notice-list-fixed button.btn.btn-secondary")
                //$(byTagAndText("button", "Прочитать все"))
                driver.$(byTagAndText("button", "Прочитать все"))
                        .as("Close popup button")
                        .should(appear, Duration.ofSeconds(20))
                        .hover().click();
                driver.$(byTagAndText("button", "Прочитать все"))  // $(".notice-list-fixed button.btn.btn-secondary")
                        .as("Close popup button")
                        .shouldNotBe(visible, Duration.ofSeconds(20))
                        .click();
                driver.refresh();
            } catch (Exception e) {
                System.out.println("No pop-up");
            }
        });

    }

}
