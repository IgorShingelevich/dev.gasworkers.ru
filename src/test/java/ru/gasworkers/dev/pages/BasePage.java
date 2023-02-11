package ru.gasworkers.dev.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public abstract class BasePage extends BaseComponent {


    public BasePage(RoleBrowser browser) {
        super(browser);
    }
    SelenideElement popUpCloseButtonLocator = driver.$(byTagAndText("span", "Прочитать все")).as("Close popup button"),
    popUpContainerLocator = driver.$(".notice-list-fixed-content.gas-scrollbar-inline").as("Pop-up container");
//    public SelenideElement mainButtonLocator = driver.$("button.mb-3.btn.btn-primary").as("Main button"); - how to make th button available for all pages?

    public void popUpClose() {
        {
            stepWithRole("Закрыть всплывающие уведомления", () -> {
                try {
                    if (popUpCloseButtonLocator.exists()) {
                        popUpCloseButtonLocator.shouldBe(visible);
                        Selenide.sleep(500);
                        popUpCloseButtonLocator.click();
                        popUpContainerLocator.shouldNotBe(visible, Duration.ofSeconds(20));
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
