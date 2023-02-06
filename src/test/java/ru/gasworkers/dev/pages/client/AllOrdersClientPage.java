package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class AllOrdersClientPage extends BaseClientPage {


    public final ActionsBlockClientComponent actionsBlock;
    public final  SidebarClientComponent sidebar;
    public  AllOrdersClientPage (RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        actionsBlock = new ActionsBlockClientComponent(browser);
    }

//    public ClientBreadcrumbsComponent breadcrumbs = new ClientBreadcrumbsComponent();


private final String ORDER_PAGE_TITLE = "Список заказов";

    SelenideElement
    orderPageTitle = driver.$(".page-title .h3.mb-2");

    ElementsCollection
    listNumberLinkCollection = driver.$$("p.h5.link-blue.pointer"),
    dropdownActionButtonCollection = driver.$$x("(//button[contains(@type,'button')])"),
        openActionLinkCollection = driver.$$x("(//a[contains(@class,'actions__slot--link')])");


    public void checkInitialState() {
        stepWithRole("Убедиться, что страница в  начальном состоянии", () -> {
            orderPageTitle.shouldHave(text(ORDER_PAGE_TITLE));
            stepWithRole("Убедиться, что отсутствуют ранее созданные Заказы", () -> {
                listNumberLinkCollection.shouldHave(size(0));
            });
        });
    }

    public AllOrdersClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что загружена страница Список заказов ", () -> {
            orderPageTitle.shouldHave(text(ORDER_PAGE_TITLE));
            listNumberLinkCollection.last().shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    public AllOrdersClientPage orderByNumber(String orderNumber) {
        stepWithRole("Открыть заказ № " + orderNumber, () -> {
            listNumberLinkCollection.findBy(text(orderNumber)).click();
        });
        return this;
    }

    public AllOrdersClientPage dropdownAction(int listNumber) {
        dropdownActionButtonCollection.get(listNumber+1).click();
        return this;
    }

    public AllOrdersClientPage openAction(int listNumber) {
        openActionLinkCollection.get(listNumber-1).click();
        return this;
    }
}
