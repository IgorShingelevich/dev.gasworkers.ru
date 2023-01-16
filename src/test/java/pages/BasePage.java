package pages;

import model.browser.RoleBrowser;
import pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;

public abstract class BasePage extends BaseComponent {

    public BasePage(RoleBrowser browser) {
        super(browser);
    }

    public void popUpClose() {
        try {
            driver.$(".notice-list-fixed button.btn.btn-secondary")
                    .as("Close popup button")
                    .shouldBe(appear, Duration.ofSeconds(10))
                    .click();
        } catch (Exception e) {
            System.out.println("No pop-up");
        }
    }

}
