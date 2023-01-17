package pages;

import com.codeborne.selenide.SelenideDriver;
import io.qameta.allure.Allure;
import model.Role;
import model.browser.RoleBrowser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;

public abstract class BasePage extends BaseComponent {

    public BasePage(RoleBrowser browser) {
        super(browser);
    }

    @DisplayName("Close the popup notifications")

    public void popUpClose() {
        stepWithRole("Закрыть всплывающие уведомления", () -> {
            try {
                driver.$(".notice-list-fixed button.btn.btn-secondary")
                        .as("Close popup button")
                        .shouldBe(appear, Duration.ofSeconds(10))
                        .click();
            } catch (Exception e) {
                System.out.println("No pop-up");
            }
        });

    }

}
