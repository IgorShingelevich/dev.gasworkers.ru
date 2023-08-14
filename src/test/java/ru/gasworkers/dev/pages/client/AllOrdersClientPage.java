package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class AllOrdersClientPage extends BaseClientPage {


    public final ClientActionsBlockComponent actionsBlock;
    public final ClientSidebarComponent sidebar;

    public AllOrdersClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new ClientSidebarComponent(browser);
        actionsBlock = new ClientActionsBlockComponent(browser);
    }

//    public ClientBreadcrumbsComponent breadcrumbs = new ClientBreadcrumbsComponent();


    private final String ORDER_PAGE_TITLE = "Список заказов";

    SelenideElement
            orderPageTitle = driver.$(".page-title .h3.mb-2");

    ElementsCollection
            itemNumberCollection = driver.$$("p.h5.link-blue.pointer"),
            dropdownActionButtonCollection = driver.$$x("(//button[contains(@type,'button')])"),
            openActionLinkCollection = driver.$$x("(//a[contains(@class,'actions__slot--link')])");


    public void checkInitialState() {
        stepWithRole("Убедиться, что страница в  начальном состоянии", () -> {
            orderPageTitle.shouldHave(text(ORDER_PAGE_TITLE));
            stepWithRole("Убедиться, что отсутствуют ранее созданные Заказы", () -> {
                itemNumberCollection.shouldHave(size(0));
            });
        });
    }

    public AllOrdersClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что загружена страница Список заказов ", () -> {
            orderPageTitle.shouldHave(text(ORDER_PAGE_TITLE));
        });
        return this;
    }

    public AllOrdersClientPage orderByNumber(String orderNumber) {
        stepWithRole("Открыть заказ № " + orderNumber, () -> {
            itemNumberCollection.findBy(partialText(orderNumber)).click();
        });
        return this;
    }

    public AllOrdersClientPage dropdownAction(int listNumber) {
        dropdownActionButtonCollection.get(listNumber + 1).click();
        return this;
    }

    public AllOrdersClientPage openAction(int listNumber) {
        openActionLinkCollection.get(listNumber - 1).click();
        return this;
    }

    public void checkBGInitialState(String orderNumber) {
        stepWithRole("Убедиться, что страница в  состоянии после Фоновой регистрации", () -> {
            orderPageTitle.shouldHave(text(ORDER_PAGE_TITLE));
            stepWithRole("Убедиться, что присутствует ранее созданный Заказ № " + orderNumber, () -> {
                itemNumberCollection.shouldHave(size(1));
                itemNumberCollection.findBy(text(orderNumber)).shouldBe(visible);
            });
        });
    }

    public void checkItemsAmount(int itemsAmount) {
        stepWithRole("Убедиться, что количество заказов равно " + itemsAmount, () -> {
            itemNumberCollection.shouldHave(size(itemsAmount));
        });
    }
}
