package pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

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
        mapLocator = driver.$(".map-wrap .ymap-container .map-into");

    ElementsCollection
        navButtonsCollection = driver.$$("div.top-filter__status--btn"),
        orderCardsCollection = driver.$$("div.order-card"),
        ordersNumberLinkCollection = driver.$$("p.h5.link-blue.pointer"),
        orderActionDropdownCollection = driver.$$("button.actions__btn"),
        actionsOpenOrderLinkCollection = driver.$$x("//a[@class='actions__slot--link']"),
        actionsArchiveOrderLinkCollection = driver.$$x("(//button[contains(@class,'actions__slot--btn')])");

    public HomeDispatcherPage checkFinishLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            dispatcherHomePageTitleLocator.shouldBe(visible, Duration.ofSeconds(10));
            mapLocator.shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    public void navNew () {
        stepWithRole("Нажать на кнопку Новый", () -> {
            navButtonsCollection.get(0).shouldHave(text("Новый")).click();
            ordersNumberLinkCollection.last().scrollIntoView(true);
            ordersNumberLinkCollection.first().scrollIntoView(true);
        });
    }

    public void navInProgress () {
        stepWithRole("Нажать на кнопку В работе", () -> {
            navButtonsCollection.get(1).shouldHave(text("В работе")).click();
            //scrollTo( ordersNumberLinkCollection.last() );
            ordersNumberLinkCollection.last().scrollIntoView(true);
            ordersNumberLinkCollection.first().scrollIntoView(true);
        });
    }

    public void navCompleted () {
        stepWithRole("Нажать на кнопку Завершен", () -> {
            navButtonsCollection.get(2).shouldHave(text("Завершен")).click();
            ordersNumberLinkCollection.last().scrollIntoView(true);
            ordersNumberLinkCollection.first().scrollIntoView(true);
        });
    }

    public void navArchived () {
        stepWithRole("Нажать на кнопку В архиве", () -> {
            navButtonsCollection.get(3).shouldHave(text("В архиве")).click();
            ordersNumberLinkCollection.last().scrollIntoView(true);
            ordersNumberLinkCollection.first().scrollIntoView(true);
        });
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
            ordersNumberLinkCollection.first().shouldBe(visible, Duration.ofSeconds(20));
        });
//        listViewButtonLocator.shouldBe(visible).click();
//        orderCardFirstLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage openOrderByNumber(Integer orderNumber) {
        stepWithRole("Открыть заказ по номеру: " + orderNumber, () -> {
            ordersNumberLinkCollection.findBy(text(orderNumber.toString())).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(orderNumber.toString()));
            System.out.println("givenOrderNumber: " + orderNumber);
        });
        return this;
    }

    public HomeDispatcherPage openOrderByIndex(int index) {
        stepWithRole("Открыть  заказ по индексу: {index} номер: " + ordersNumberLinkCollection.get(index).getText(), () -> {
            ordersNumberLinkCollection.get(index).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10));
            System.out.println("orderNumber:  " + orderCardTitleLocator.getText() + " index: " + index);
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
