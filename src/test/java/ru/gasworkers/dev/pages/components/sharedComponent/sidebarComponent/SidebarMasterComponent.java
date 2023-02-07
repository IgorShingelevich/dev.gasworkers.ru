package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class SidebarMasterComponent extends BaseComponent {

    public SidebarMasterComponent(RoleBrowser browser) {
            super(browser);
        }

    ElementsCollection
        orderNumberLinkCollection = driver.$$("p.h5.link-blue.pointer").as("orderNumberLinkCollection"),
        sidebarElementsCollection = driver.$$(".sidebar .link");

    SelenideElement
        titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы").as("Заголовок страницы"),
        toggleReadyToVideoStateButtonLocator = driver.$("button.btn-sm.mt-3.btn").as("Переключатель готовности к видео"),

        HomeLinkLocator = sidebarElementsCollection.get(0).as("Домашняя страница"),
        ordersHistoryDropdownLocator = sidebarElementsCollection.get(1).as("История заказов"),
        allNewOrdersLinkLocator = sidebarElementsCollection.get(2).as("Заказы новые"),
        allAssignedOrdersLinkLocator = sidebarElementsCollection.get(3).as("Заказы принятые"),
        allCompletedOrdersLinkLocator = sidebarElementsCollection.get(4).as("Заказы выполненные "),
        recruitingDropdownLocator = sidebarElementsCollection.get(5).as("Рекрутинг"),
        resumeLinkLocator = sidebarElementsCollection.get(6).as("Резюме"),
        invitationsLinkLocator = sidebarElementsCollection.get(7).as("Приглашения"),
        profileLinkLocator = sidebarElementsCollection.get(8).as("Профиль");



        public void home () {
            stepWithRole("Переход на домашнюю страницу", () -> {
                HomeLinkLocator.click();

            });
        }

        public void ordersHistoryDropdown () {
            stepWithRole("Выпадающий список История заказов", () -> {
                ordersHistoryDropdownLocator.click();
            });
        }

        public void newOrders() {
            stepWithRole("Переход на страницу Заказы новые", () -> {
                allNewOrdersLinkLocator.shouldHave(text("Заказы новые")).click();
                titleLocator.shouldHave(text("Список новых заказов"));
                orderNumberLinkCollection.should(sizeGreaterThan(0));
            });
        }

        public void allAssignedOrders() {
            stepWithRole("Переход на страницу Заказы принятые", () -> {
                allAssignedOrdersLinkLocator.shouldHave(text("Заказы принятые")).click();
                titleLocator.shouldHave(text("Список принятых заказов"));
                orderNumberLinkCollection.should(sizeGreaterThan(0));

            });
        }

        public void allCompletedOrders() {
            stepWithRole("Переход на страницу Заказы выполненные", () -> {
                allCompletedOrdersLinkLocator.shouldHave(text("Заказы выполненные ")).click();
                titleLocator.shouldHave(text("Список завершенных заказов"));
                orderNumberLinkCollection.should(sizeGreaterThan(0));

            });
        }

        public void resume() {
            stepWithRole("Переход на страницу Резюме", () -> {
                resumeLinkLocator.shouldHave(text("Резюме")).click();
                titleLocator.shouldHave(text("Резюме"));
            });
        }

        public void invitations() {
            stepWithRole("Переход на страницу Приглашения", () -> {
                invitationsLinkLocator.shouldHave(text("Приглашения")).click();
                titleLocator.shouldHave(text("Резюме"));
                driver.$("p.h3.mb-4").shouldHave(text("Приглашения"));
            });
        }

        public void profile() {
            stepWithRole("Переход на страницу Профиль", () -> {
                profileLinkLocator.shouldBe(text("Профиль")).click();
                titleLocator.shouldHave(text("Профиль"));
            });
        }







}
