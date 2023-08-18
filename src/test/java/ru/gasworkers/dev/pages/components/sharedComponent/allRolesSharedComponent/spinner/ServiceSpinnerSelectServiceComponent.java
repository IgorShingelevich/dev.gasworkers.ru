package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.spinner;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.AllRolesSharedComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class ServiceSpinnerSelectServiceComponent extends AllRolesSharedComponent {

    public ServiceSpinnerSelectServiceComponent(RoleBrowser browser) {
            super(browser);
        }

        SelenideElement
                spinnerServicesContainerLocator = driver.$(".scrollbar.mb-3.col-lg-5 .d-flex.justify-content-center.pb-5").as("Спиннер загрузки контейнера с Сервисными компаниями");


        public void checkPresence() {
            stepWithRole("Убедиться, что если спиннер появидся, то  исчез", () -> {
                try {
                    if (spinnerServicesContainerLocator.is(visible)) {
                        spinnerServicesContainerLocator.shouldNotBe(visible, Duration.ofSeconds(40));
                        System.out.println("Spinner disappeared");
                    } else {
                        System.out.println("No spinner");
                    }
                } catch (ElementShould e) {
                    // Log the exception and proceed with the test
                    System.out.println("Element spinner was not found or not enabled or not visible.");
                    e.printStackTrace();
                }
            });
        }

}
