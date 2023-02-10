package ru.gasworkers.dev.pages.master.ordersHystoryMasterPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;
import ru.gasworkers.dev.pages.master.BaseMasterPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class AllNewOrdersHistoryMasterPage extends BaseMasterPage {

    public final SidebarMasterComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;

    public AllNewOrdersHistoryMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
    }

    ElementsCollection orderNumberLinkCollection = driver.$$("p.h5.link-blue.pointer").as("Номер заказа");
    SelenideElement
        titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
        orderCardTitleLocator = driver.$(".page-title .h3.mb-2"),
        switchToListViewLocator = driver.$("div.action-btn.list-type"),
        switchToTabViewLocator = driver.$("div.action-btn.card-type");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Заказы новые загружена", () -> {
            titleLocator.shouldHave(text("Список новых заказов"));
            orderNumberLinkCollection.shouldHave(CollectionCondition.sizeGreaterThan(0));
        });
    }

    public void switchToListView() {
        stepWithRole("Переключиться на вид списка", () -> {
            switchToListViewLocator.click();
        });
    }

    public void switchToTabView() {
        stepWithRole("Переключиться на вид карточек", () -> {
            switchToTabViewLocator.click();
        });
    }

    public void openByNumber(String orderNumber) {
        stepWithRole("Открыть заказ по номеру: " + orderNumber, () -> {
            orderNumberLinkCollection.findBy(text(orderNumber)).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(orderNumber));
            System.out.println("master givenOrderNumber: " + orderNumber);
        });
    }

    public void openByIndex(int index) {
        stepWithRole("Открыть  заказ по индексу: "+ index + ", номер: " + orderNumberLinkCollection.get(index).getText(), () -> {
            orderNumberLinkCollection.get(index).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10));
            System.out.println("master orderNumber:  " + orderCardTitleLocator.getText() + " index: " + index);
        });
    }

}
