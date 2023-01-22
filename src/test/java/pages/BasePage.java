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

import javax.lang.model.type.ArrayType;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public abstract class BasePage extends BaseComponent {

    public BasePage(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        popUpCloseButtonLocator = driver.$(byTagAndText("button", "Прочитать все")),
        popUpContainerLocator =  driver.$(".notice-list-fixed-content.gas-scrollbar-inline");

    public void popUpClose()  {
        stepWithRole("Закрыть всплывающие уведомления", () -> {
            try {
                    popUpCloseButtonLocator.as("Close popup button")
                            .should(appear, Duration.ofSeconds(20)).hover()
                            .click();

            } catch (NoSuchElementException e) {
                System.out.println("No pop-up");
            }
        /*    try{
                popUpCloseButtonLocator.as("Close popup button").should(disappear, Duration.ofSeconds(20)).click();
            } catch (NoSuchElementException e) {
                System.out.println("No pop-up");
            }
            try{
                popUpCloseButtonLocator.wait(6000l);
            }catch (Exception e){
                System.out.println("No pop-up");
            }*/
        });
    }

    public void popUpClose2()  throws TimeoutException {  {
        stepWithRole("Закрыть всплывающие уведомления", () -> {
            try {
                popUpCloseButtonLocator.as("Close popup button").waitUntil(appears, 10000);
                popUpCloseButtonLocator.click();
            } catch (TimeoutException e) {
                System.out.println("No pop-up");
            }
        });
    }



}
