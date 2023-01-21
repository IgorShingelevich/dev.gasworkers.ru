package pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;

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
        titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
        onVideoModeLocator = driver.$("button.btn-sm.mt-3.btn").shouldHave(text("Готов к работе")),
        offVideoModeLocator = driver.$("button.btn-sm.mt-3.btn").shouldHave(text("Завершить смену")),
        HomeLinkLocator = sidebarElementsCollection.get(0).shouldHave(text("Главная")),
        ordersHistoryDropdownLocator = sidebarElementsCollection.get(1).as("История заказов"),
        allNewOrdersLinkLocator = sidebarElementsCollection.get(2).as("Заказы новые"),
        allAssignedOrdersLinkLocator = sidebarElementsCollection.get(3).as("Заказы принятые"),
        allCompletedOrdersLinkLocator = sidebarElementsCollection.get(4).as("Заказы выполненные "),
        recruitingDropdownLocator = sidebarElementsCollection.get(5).as("Рекрутинг"),
        resumeLinkLocator = sidebarElementsCollection.get(6).as("Резюме"),
        invitationsLinkLocator = sidebarElementsCollection.get(7).as("Приглашения"),
        profileLinkLocator = sidebarElementsCollection.get(8).as("Профиль"),
        supportServiceTitleLocator = driver.$(".support-service").shouldHave(text("Служба поддержки")).as("Служба поддержки"),
        supportServicePhoneLocator = driver.$(".support-service__phone").shouldHave(text("8 800 302 89 04")).as("8 800 302 89 04"),
        supportServiceEmailLocator = driver.$(".support-service .link-dark-blue").shouldHave(text("info@gasworkers.ru")).as("mail");


        public void home () {
            stepWithRole("Переход на домашнюю страницу", () -> {
                HomeLinkLocator.click();

            });
        }

        public void allNewOrders() {
            stepWithRole("Переход на страницу Заказы новые", () -> {
                allNewOrdersLinkLocator.shouldHave(text("Заказы новые")).click();
                titleLocator.shouldHave(text("Заказы новые"));
                orderNumberLinkCollection.should(sizeGreaterThan(0));
            });
        }

        public void allAssignedOrders() {
            stepWithRole("Переход на страницу Заказы принятые", () -> {
                allAssignedOrdersLinkLocator.shouldHave(text("Заказы принятые")).click();
                titleLocator.shouldHave(text("Заказы принятые"));
                orderNumberLinkCollection.should(sizeGreaterThan(0));

            });
        }

        public void allCompletedOrders() {
            stepWithRole("Переход на страницу Заказы выполненные", () -> {
                allCompletedOrdersLinkLocator.shouldHave(text("Заказы выполненные ")).click();
                titleLocator.shouldHave(text("Заказы выполненные"));
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
