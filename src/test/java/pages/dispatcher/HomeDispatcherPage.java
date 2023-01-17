package pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockDispatcherComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;

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
        orderCardsCollection = $$("div.order-card"),

        ordersNumberLinkCollection = $$(".h5.link-blue.pointer"),
        orderActionDropdownCollection = $$("button.actions__btn"),
        actionsOpenOrderLinkCollection = $$x("//a[@class='actions__slot--link']"),
        actionsArchiveOrderLinkCollection = $$x("(//button[contains(@class,'actions__slot--btn')])");




    public HomeDispatcherPage isOpened() {
        dispatcherHomePageTitleLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage switchToMapView() {
        mapViewButtonLocator.shouldBe(visible).click();
        map.shouldBe(visible);


        return this;
    }

    public HomeDispatcherPage switchToCardView() {
        cardViewButtonLocator.shouldBe(visible).click();
        return this;
    }

    public HomeDispatcherPage switchToListView() {
        listViewButtonLocator.shouldBe(visible).click();
        orderCardFirstLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage openOrderByNumber(String orderNumber) {
        ordersNumberLinkCollection.findBy(text(orderNumber)).click();
        orderCardTitleLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage openFirstOrderByTitleIndex() {
//        switchToListView();
        ordersNumberLinkCollection.get(0).shouldBe(visible).click();
        orderCardTitleLocator.shouldBe(visible);
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