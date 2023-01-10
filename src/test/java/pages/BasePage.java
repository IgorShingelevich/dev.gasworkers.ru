package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    SelenideElement
            popupLocator = $(".notice-list-fixed button.btn.btn-secondary");

    public void popUpClose() {

        if (popupLocator.isDisplayed()) {
            popupLocator.click();
        }
        else
            try {
                popupLocator.wait( 2000);
                popupLocator.click();
            }
            catch (Exception e) {
                System.out.println("Popup is not displayed");
            }
    }





}
