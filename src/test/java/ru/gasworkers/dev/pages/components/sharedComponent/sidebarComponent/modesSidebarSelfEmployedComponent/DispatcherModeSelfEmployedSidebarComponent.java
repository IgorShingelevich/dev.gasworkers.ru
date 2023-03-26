package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode.SidebarScrollNotificationsSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SupportServiceSidebarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class DispatcherModeSelfEmployedSidebarComponent extends BaseComponent {
    public final SidebarScrollNotificationsSelfEmployedComponent sidebarNotification;
    public final SupportServiceSidebarComponent support;

    public DispatcherModeSelfEmployedSidebarComponent(RoleBrowser browser) {
        super(browser);
        sidebarNotification = new SidebarScrollNotificationsSelfEmployedComponent(browser);
        support = new SupportServiceSidebarComponent(browser);
    }

    private final String
            ordersLinkText = "Заказы",
            certificatesAndEquipmentLinkText = "Сертификаты и оборудование",
            profileLinkText = "Профиль";

    SelenideElement
            self = driver.$("div.sidebar-content").as("Бокс с боковой панелью"),
            ordersHistoryDropdownLocator = self.$("a.link").as("Дропдаун история заказов");

    ElementsCollection
            linkCollection = self.$$("div.link").as("Ссылки в боковой панели");

    SelenideElement
            ordersLinkLocator = linkCollection.get(0).as("Ссылка на карту с заказами"),
            certificatesAndEquipmentLocator = linkCollection.get(1).as("Ссылка на сертификаты и оборудование"),
            profileLinkLocator = linkCollection.get(2).as("Ссылка на профиль");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что боковая панель загрузилась", () -> {
            stepWithRole("Убедиться, что в сайдбаре 3 ссылки", () -> {
                linkCollection.shouldHave(CollectionCondition.size(3));
            });
            //todo service and scroll notifications block
            self.shouldBe(visible);
            ordersLinkLocator.shouldHave(text(ordersLinkText));
            certificatesAndEquipmentLocator.shouldHave(text(certificatesAndEquipmentLinkText));
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
            ordersLinkLocator.click();
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
//todo sidebar links unification - if not extract base SelfEmployedSidebarComponent

