package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode.SidebarScrollNotificationsSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SupportServiceSidebarComponent;

import static com.codeborne.selenide.Condition.*;

public class MasterModeSelfEmployedSidebarComponent extends BaseComponent {
    public final SidebarScrollNotificationsSelfEmployedComponent sidebarNotification;
    public final SupportServiceSidebarComponent support;

    public MasterModeSelfEmployedSidebarComponent(RoleBrowser browser) {
        super(browser);
        sidebarNotification = new SidebarScrollNotificationsSelfEmployedComponent(browser);
        support = new SupportServiceSidebarComponent(browser);
    }

    private final String
            homeLinkText = "Главная",
            ordersHistoryDropdownText = "История заказов",
            newOrdersLinkText = "Заказы новые",
            scheduledOrdersLinkText = "Заказы принятые",
            completedOrdersLinkText = "Заказы выполненные",
            certificatesAndEquipmentLinkText = "Сертификаты и оборудование",
            profileLinkText = "Профиль";

    SelenideElement
            self = driver.$("div.sidebar-content").as("Бокс с боковой панелью"),
            ordersHistoryDropdownLocator = self.$("a.link").as("Дропдаун история заказов");

    ElementsCollection
            linkCollection = self.$$("div.link").as("Ссылки в боковой панели");

    SelenideElement
            homeLinkLocator = linkCollection.get(0).as("Ссылка на домашнюю мастера с расписанием"),
            allNewOrdersLinkLocator = linkCollection.get(1).as("Ссылка на новые заказы"),
            allScheduledOrdersLinkLocator = linkCollection.get(2).as("Ссылка на заказы принятые"),
            allCompletedOrdersLinkLocator = linkCollection.get(3).as("Ссылка на заказы выполненные"),
            certificatesAndEquipmentLocator = linkCollection.get(4).as("Ссылка на сертификаты и оборудование"),
            profileLinkLocator = linkCollection.get(5).as("Ссылка на профиль");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что боковая панель загрузилась", () -> {
            stepWithRole("Убедиться, что в сайдбаре 6 ссылок", () -> {
                linkCollection.shouldHave(CollectionCondition.size(6));
            });
            //todo service and scroll notifications block
            self.shouldBe(visible);
            homeLinkLocator.shouldHave(text(homeLinkText));
            ordersHistoryDropdownLocator.shouldHave(text(ordersHistoryDropdownText));
            checkDropdownClosedState();
            expandHistoryDropdown();
            closeHistoryDropdown();
            checkDropdownClosedState();
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

    public void expandHistoryDropdown() {
        stepWithRole("Развернуть дропдаун история заказов", () -> {
            ordersHistoryDropdownLocator.click();
            checkDropdownExpandedState();
        });
    }

    public void closeHistoryDropdown() {
        stepWithRole("Закрыть дропдаун история заказов", () -> {
            ordersHistoryDropdownLocator.click();
            checkDropdownClosedState();
        });
    }

    private void checkDropdownExpandedState() {
        stepWithRole("Убедиться, что дропдаун история заказов развернут", () -> {
            driver.$$("ul.sidebar-nav li").get(1).shouldHave(cssClass("active"));
            allNewOrdersLinkLocator.shouldHave(text(newOrdersLinkText));
            allScheduledOrdersLinkLocator.shouldHave(text(scheduledOrdersLinkText));
            allCompletedOrdersLinkLocator.shouldHave(text(completedOrdersLinkText));
        });
    }

    private void checkDropdownClosedState() {
        stepWithRole("Убедиться, что дропдаун история заказов закрыт", () -> {
            driver.$$("ul.sidebar-nav li").get(1).shouldNotHave(cssClass("active"));
            allNewOrdersLinkLocator.shouldNotBe(visible);
            allScheduledOrdersLinkLocator.shouldNotBe(visible);
            allCompletedOrdersLinkLocator.shouldNotBe(visible);
        });
    }

}

//todo sidebar links unification - if not extract base SelfEmployedSidebarComponent
