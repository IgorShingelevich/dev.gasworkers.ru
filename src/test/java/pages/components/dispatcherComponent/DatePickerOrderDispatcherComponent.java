package pages.components.dispatcherComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class DatePickerOrderDispatcherComponent extends BaseComponent {

    public  DatePickerOrderDispatcherComponent (RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        setButtonLocator = driver.$(byTagAndText("button", "Назначить"));


    ElementsCollection
        activeDateRangeCollection = driver.$$(".cell:not(.disabled)"),
        activeHourRangeCollection = driver.$$("ul[data-type='hour'] > li:not(.disabled)"),
        activeMinuteRangeCollection = driver.$$("ul[data-type='minute'] > li:not(.disabled)");

    public void selectNowDateAndTime() {
        stepWithRole("Установить настоящюю дату и время", () -> {
            activeDateRangeCollection.get(0).click();
            setButtonLocator.click();
            activeHourRangeCollection.get(0).click();
            activeMinuteRangeCollection.get(0).click();
            setButtonLocator.click();
        });
        String setDataAndTimeDispatcher =  driver.$$("div.order-details__text").get(6).getText();
        System.out.println("setDataAndTimeDispatcher = " + setDataAndTimeDispatcher);
    }

    public void setDateHourMinute(String date, String hour, String minute) {
        stepWithRole("Установить дату, час и минуту", () -> {
            activeDateRangeCollection.findBy(text(date)).click();
            setButtonLocator.click();
            activeHourRangeCollection.findBy(text(hour)).click();
            activeMinuteRangeCollection.findBy(text(minute)).click();
            setButtonLocator.click();
        });
        String setDataAndTimeDispatcher =  driver.$$("div.order-details__text").get(6).getText();
        System.out.println("setDataAndTimeDispatcher = " + setDataAndTimeDispatcher);
    }






}
