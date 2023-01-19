package pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockDispatcherComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomeDispatcherPage extends BaseDispatcherPage {

    private final SidebarDispatcherComponent sidebarDispatcher;
    private final ActionsBlockDispatcherComponent actionBlockDispatcher;

    public HomeDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebarDispatcher = new SidebarDispatcherComponent(browser);
        actionBlockDispatcher = new ActionsBlockDispatcherComponent(browser);
    }

    SelenideElement
        dispatcherHomePageTitleLocator = driver.$(".page-title .h3.mb-2"),
        mapViewButtonLocator = driver.$("div.action-btn.map-type"),
        cardViewButtonLocator = driver.$("div.action-btn.card-type"),
        listViewButtonLocator = driver.$("div.action-btn.list-type"),
        orderCardTitleLocator = driver.$(".page-title .h3.mb-2"),
        orderCardFirstLocator = driver.$$("div.order-card").first(),
        mapContainerLocator = driver.$("div.map-wrap"),
        map=driver.$(".map-wrap .ymap-container .map-into"),
        newOrderStatusButtons= driver.$$("div.top-filter__status--btn").get(0),
        inProgressOrderStatusButton = driver.$$("div.top-filter__status--btn").get(1),
        completedOrderStatusButton =  driver.$$("div.top-filter__status--btn").get(2),
        archivedOrderStatusButton = driver.$$("div.top-filter__status--btn").get(3);

    ElementsCollection
        orderCardsCollection = driver.$$("div.order-card"),

        ordersNumberLinkCollection = driver.$$("p.h5.link-blue.pointer"),
        orderActionDropdownCollection = driver.$$("button.actions__btn"),
        actionsOpenOrderLinkCollection = driver.$$x("//a[@class='actions__slot--link']"),
        actionsArchiveOrderLinkCollection = driver.$$x("(//button[contains(@class,'actions__slot--btn')])");




    public HomeDispatcherPage checkFinishLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            dispatcherHomePageTitleLocator.shouldBe(visible, Duration.ofSeconds(10));
            map.shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    public HomeDispatcherPage switchToMapView() {
        stepWithRole("Переключиться на карту", () -> {
            mapViewButtonLocator.click();
            mapContainerLocator.shouldBe(visible, Duration.ofSeconds(10));
        });
        return this;
    }

    public HomeDispatcherPage switchToCardView() {
        stepWithRole("Переключиться на карточки", () -> {
            cardViewButtonLocator.click();
            orderCardsCollection.last().shouldBe(visible, Duration.ofSeconds(20));
            //visibility of the last element in the collection
        });
        return this;
    }

    public HomeDispatcherPage switchToListView() {
        stepWithRole("Переключиться на список", () -> {
            listViewButtonLocator.click();
            ordersNumberLinkCollection.last().shouldBe(visible, Duration.ofSeconds(20));
        });
//        listViewButtonLocator.shouldBe(visible).click();
//        orderCardFirstLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage openOrderByNumber(String orderNumber) {
        stepWithRole("Открыть заказ по номеру", () -> {
            ordersNumberLinkCollection.findBy(text(orderNumber)).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(orderNumber));
        });
        return this;
    }

    public HomeDispatcherPage openOrderByIndex(int index) {
        stepWithRole("Открыть  заказ по индексу ", () -> {
            ordersNumberLinkCollection.get(index).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10));
        });
        return this;
    }



    public HomeDispatcherPage openFirstOrderByAction() {
//        switchToListView();
        orderActionDropdownCollection.get(0).shouldBe(visible).hover().click();
        actionsOpenOrderLinkCollection.get(0).shouldBe(visible).click();
        orderCardTitleLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage openLastOrderByAction() {
        switchToListView();
        orderActionDropdownCollection.last().shouldBe(visible).hover().click();
        actionsOpenOrderLinkCollection.last().shouldBe(visible).click();
        orderCardTitleLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage archiveLastOrder() {
        switchToListView();
        orderActionDropdownCollection.get(0).shouldBe(visible).hover().click();
        actionsArchiveOrderLinkCollection.get(0).shouldBe(visible).click();
        //check that the order with this number is not visible in the list of orderNumberLinkCollection
        return this;
    }

    public HomeDispatcherPage openRandomOrder() {
        switchToListView();
        int randomOrderNumber = (int) (Math.random() * ordersNumberLinkCollection.size()+1);
        ordersNumberLinkCollection.get(randomOrderNumber).shouldBe(visible).click();
        orderCardTitleLocator.shouldBe(visible);
        return this;
    }



}
