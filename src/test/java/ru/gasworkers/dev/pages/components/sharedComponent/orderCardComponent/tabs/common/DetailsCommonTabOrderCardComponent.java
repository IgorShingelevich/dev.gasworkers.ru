package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class DetailsCommonTabOrderCardComponent extends BaseOrderCardComponent {

    ElementsCollection
            selfCollection = driver.$$("div.order-details-item").as("Информация о заказе");

    public DetailsCommonTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkServiceType(ServiceType serviceType) {
        stepWithRole("Убедиться, что тип заказа: " + serviceType.toString(), () -> {
            selfCollection.findBy(text("Тип заказа")).shouldHave(text(serviceType.toString()));
        });
    }

    public void checkCompanyFullName(String expectedServiceCompany) {
        stepWithRole("Убедиться, что сервисная компания: " + expectedServiceCompany + " соответствует ожидаемой", () -> {
            selfCollection.findBy(text("Сервисная компания:")).shouldHave(text(expectedServiceCompany));
        });
    }

    public void checkNoCompany() {
        stepWithRole("Убедиться, что сервисная компания отсутствует", () -> {
            selfCollection.findBy(text("Сервисная компания:")).shouldNotBe(visible);
        });
    }

    public void checkClientFullName(String expectedPersonalData) {
        stepWithRole("Убедиться, что личные данные " + expectedPersonalData + " соответствуют ожидаемым", () -> {
            selfCollection.findBy(text("Личные данные:")).shouldHave(text(expectedPersonalData));
        });
    }

    public void checkAddress(String expectedAddress) {
        stepWithRole("Убедиться, что адрес " + expectedAddress + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Адрес объекта:")).shouldHave(text(expectedAddress));
        });
    }

    public void checkClientPhone(String expectedPhoneNumber) {
        stepWithRole("Убедиться, что номер телефона " + expectedPhoneNumber + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Номер телефона:")).shouldHave(text(expectedPhoneNumber));
        });
    }

    public void checkEquipment(String expectedEquipment) {
        stepWithRole("Убедиться, что оборудование " + expectedEquipment + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Оборудование:")).shouldHave(text(expectedEquipment));
        });
    }

    public void checkDesiredDate(String expectedDate) {
        stepWithRole("Убедиться, что  желаемая дата " + expectedDate + " соответствует ожидаемой", () -> {
            selfCollection.findBy(text("Желаемая дата:")).shouldHave(text(expectedDate));
        });
    }

    public void checkDesiredTime(String expectedTime) {
        stepWithRole("Убедиться, что желаемое время " + expectedTime + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Желаемое время:")).shouldHave(text(expectedTime));
        });
    }

    public void checkDescription(String expectedDescription) {
        stepWithRole("Убедиться, что описание " + expectedDescription + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Описание проблемы:")).shouldHave(text(expectedDescription));
        });
    }
    
    
}
