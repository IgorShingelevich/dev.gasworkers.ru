package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    SelenideElement
            popupLocator = $(".notice-list-fixed button.btn.btn-secondary");

    public void popUpClose() {
        try {
            popupLocator.should(appear, Duration.ofSeconds(5));
            popupLocator.click();
        } catch (Exception e) {
            System.out.println("No pop-up");
        }

        /*if (popupLocator.should(appear, Duration.ofSeconds(2)).isDisplayed()) {
            popupLocator.click();
        }
        // else proceed the test
        else {
            System.out.println("No pop-up");
        }*/
    }





}
