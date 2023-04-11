package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class PopUpNotificationsSharedComponent extends AllRolesSharedComponent{
    public PopUpNotificationsSharedComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement popUpCloseButtonLocator = driver.$(byTagAndText("span", "Прочитать все")).as("Close popup button"),
            popUpContainerLocator = driver.$(".notice-list-fixed-content.gas-scrollbar-inline").as("Pop-up container");
//    public SelenideElement mainButtonLocator
//    //popUpClose

    public void close() {
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
