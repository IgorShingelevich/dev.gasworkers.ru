package ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.BaseProfileSelfEmployedComponent;

import static com.codeborne.selenide.Condition.*;

public class NavNotificationsTabProfilePageComponent extends BaseProfileSelfEmployedComponent {
    public NavNotificationsTabProfilePageComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
            notificationsItemCollection = driver.$$("div.gas-checkbox").as("Элементы уведомлений");
    SelenideElement
            pushNotificationCheckboxLocator = notificationsItemCollection.get(0).$(" .p-default").as("Чекбокс 'Push-уведомления'"),
            emailNotificationCheckboxLocator = notificationsItemCollection.get(1).$(" .p-default").as("Чекбокс 'Email-уведомления'"),
            smsNotificationCheckboxLocator = notificationsItemCollection.get(2).$(" .p-default").as("Чекбокс 'SMS-уведомления'"),
            saveButtonLocator = driver.$(".footer button.mb-3.btn.btn-primary").as("Кнопка Сохранить");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что вкладка Уведомления загружена", () -> {
            pushNotificationCheckboxLocator.shouldBe(visible);
            notificationsItemCollection.get(0).shouldHave(text("PUSH-уведомление в браузере"));
            emailNotificationCheckboxLocator.shouldBe(visible);
            notificationsItemCollection.get(1).shouldHave(text("Электронная почта"));
            smsNotificationCheckboxLocator.shouldBe(visible);
            notificationsItemCollection.get(2).shouldHave(text("SMS уведомления"));
            saveButtonLocator.shouldHave(text("Сохранить"));
        });
        stepWithRole("Убедиться, что кнопка Сохранить неактивна", () -> {
            saveButtonLocator.shouldBe(disabled);
        });
    }

    public void checkInitialState() {
        stepWithRole("Убедиться, что все чекбоксы выбраны на вкладке Уведомления ", () -> {
            pushNotificationCheckboxLocator.$("input").shouldHave(attribute("value", "true"));
            emailNotificationCheckboxLocator.$("input").shouldHave(attribute("value", "true"));
            smsNotificationCheckboxLocator.$("input").shouldHave(attribute("value", "true"));
        });
        toOrderContextButtons.checkNoToOrderContextButtonsPresenceState();
    }

    public void checkFirsOfferEvaluatedSEInitialState() {
        stepWithRole("Убедиться, что все чекбоксы выбраны на вкладке Уведомления ", () -> {
            pushNotificationCheckboxLocator.$("input").shouldHave(attribute("value", "true"));
            emailNotificationCheckboxLocator.$("input").shouldHave(attribute("value", "true"));
            smsNotificationCheckboxLocator.$("input").shouldHave(attribute("value", "true"));
        });
        toOrderContextButtons.checkToOrderContextButtonsPresenceState();
    }


}
