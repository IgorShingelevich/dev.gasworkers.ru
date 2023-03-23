package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode.SidebarScrollNotificationsSelfEmployedComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class SelfEmployedSidebarComponent extends BaseComponent {
    public final SidebarScrollNotificationsSelfEmployedComponent sidebarScroll;
    public final SupportServiceSidebarComponent support;

    public SelfEmployedSidebarComponent(RoleBrowser browser) {
        super(browser);
        sidebarScroll = new SidebarScrollNotificationsSelfEmployedComponent(browser);
        support = new SupportServiceSidebarComponent(browser);
    }

    private final String
            homeLinkText = "Главная",
            newOrdersLinkText = "Новые заказы",
            scheduledOrdersinLinkText = "Заказы принятые",
            completedOrdersLinkText = "Заказы выполненные",
            certificatesAndEquipmentLinkText = "Сертификаты и оборудование",
            profileLinkText = "Профиль",
            ordersHistoryDropdownText = "История заказов";

    SelenideElement
            linkBoxContainer = driver.$("div.sidebar-content").as("Контейнер ссылок"),
            ordersHistoryDropdownLocator = linkBoxContainer.$("a.link").as("Дропдаун история заказов");

    ElementsCollection
            linkCollection = linkBoxContainer.$$("div.link").as("Ссылки в боковой панели");

    SelenideElement
            homeLinkLocator = linkCollection.get(0).as("Ссылка на домашнюю страницу"),
        allNewOrdersLinkLocator = linkCollection.get(1).as("Ссылка на новые заказы"),
        allScheduledOrdersLinkLocator = linkCollection.get(2).as("Ссылка на заказы принятые"),
        allCompletedOrdersLinkLocator = linkCollection.get(3).as("Ссылка на заказы выполненные"),
        certificatesAndEquipmentLocator = linkCollection.get(4).as("Ссылка на сертификаты и оборудование"),
        profileLinkLocator = linkCollection.get(5).as("Ссылка на профиль");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что боковая панель загрузилась", () -> {
            //todo service and scroll notifications block
            linkBoxContainer.shouldBe(visible);
            ordersHistoryDropdownLocator.shouldHave(text(ordersHistoryDropdownText));
            homeLinkLocator.shouldHave(text(homeLinkText));
            allNewOrdersLinkLocator.shouldHave(text(newOrdersLinkText));
            allScheduledOrdersLinkLocator.shouldHave(text(scheduledOrdersinLinkText));
            allCompletedOrdersLinkLocator.shouldHave(text(completedOrdersLinkText));
            profileLinkLocator.shouldHave(text(profileLinkText));
        });
    }

    public void ordersHistoryDropdown() {
        stepWithRole("Кликнуть по дропдауну история заказов", () -> {
            ordersHistoryDropdownLocator.click();
        });
    }

    public void home() {
        stepWithRole("Кликнуть по ссылке на домашнюю страницу", () -> {
            homeLinkLocator.click();
        });
    }

    public void allNewOrders() {
        stepWithRole("Кликнуть по ссылке на новые заказы", () -> {
            allNewOrdersLinkLocator.click();
        });
    }

    public void allScheduledOrders() {
        stepWithRole("Кликнуть по ссылке на заказы принятые", () -> {
            allScheduledOrdersLinkLocator.click();
        });
    }

    public void allCompletedOrders() {
        stepWithRole("Кликнуть по ссылке на заказы выполненные", () -> {
            allCompletedOrdersLinkLocator.click();
        });
    }

    public void certificatesAndEquipment() {
        stepWithRole("Кликнуть по ссылке на сертификаты и оборудование", () -> {
            certificatesAndEquipmentLocator.click();
        });
    }

    public void profile() {
        stepWithRole("Кликнуть по ссылке на профиль", () -> {
            profileLinkLocator.click();
        });
    }





}
