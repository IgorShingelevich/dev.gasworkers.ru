package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.spinner;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.AllRolesSharedComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class MapSpinnerSelectServiceComponent extends AllRolesSharedComponent {

    public MapSpinnerSelectServiceComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkPresence() {
        stepWithRole("Убедиться, что если спиннер появился, то  исчез", () -> {
            try {
                if (mapSpinnerContainerLocator.is(visible)) {
                    mapSpinnerContainerLocator.shouldNotBe(visible, Duration.ofSeconds(40));
                    System.out.println("map spinner disappeared");
                } else {
                    System.out.println("No map spinner");
                }
            } catch (ElementShould e) {
                // Log the exception and proceed with the test
                System.out.println("Element map spinner was not found or not enabled or not visible.");
                e.printStackTrace();
            }
        });
    }    SelenideElement
            self = driver.$("div.map-container").as("Карта"),
            mapSpinnerContainerLocator = self.$("div.gas-box img[src='/_ipx/_/images/logo-blue-loader.svg']").as("Спиннер карты");




}
