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

    ElementsCollection
        navButtonsCollection = driver.$$("div.top-filter__status--btn"),
        orderCardsCollection = driver.$$("div.order-card"),
        orderNumberLinkCollection = driver.$$("p.h5.link-blue.pointer"),
        orderActionDropdownCollection = driver.$$("button.actions__btn"),
        actionsOpenOrderLinkCollection = driver.$$x("//a[@class='actions__slot--link']"),
        actionsArchiveOrderLinkCollection = driver.$$x("(//button[contains(@class,'actions__slot--btn')])");

    SelenideElement
        dispatcherHomePageTitleLocator = driver.$(".page-title .h3.mb-2").as("Заголовок страницы"),
        navNewButtonLocator = navButtonsCollection.get(0).as("Новый"),
        navInProgressButtonLocator = navButtonsCollection.get(1).as("В работе"),
        navCompletedButtonLocator = navButtonsCollection.get(2).as("Выполнен"),
        navArchiveButtonLocator = navButtonsCollection.get(3).as("В архиве"),
        mapViewButtonLocator = driver.$("div.action-btn.map-type"),
        cardViewButtonLocator = driver.$("div.action-btn.card-type"),
        listViewButtonLocator = driver.$("div.action-btn.list-type"),
        orderCardTitleLocator = driver.$(".page-title .h3.mb-2"),
        mapContainerLocator = driver.$("div.map-wrap"),
        mapLocator = driver.$(".map-wrap .ymap-container .map-into");


    public HomeDispatcherPage checkFinishLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            dispatcherHomePageTitleLocator.shouldBe(visible, Duration.ofSeconds(10));
            mapLocator.shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    public void navNew () {
        stepWithRole("Нажать на кнопку Новый", () -> {
            navNewButtonLocator.shouldHave(text("Новый")).click();
            orderNumberLinkCollection.last().scrollIntoView(true);
            orderNumberLinkCollection.first().scrollIntoView(true);
        });
    }

    public void navInProgress () {
        stepWithRole("Нажать на кнопку В работе", () -> {
            navInProgressButtonLocator.shouldHave(text("В работе")).click();
            //scrollTo( ordersNumberLinkCollection.last() );
            orderNumberLinkCollection.last().scrollIntoView(true);
            orderNumberLinkCollection.first().scrollIntoView(true);
        });
    }

    public void navCompleted () {
        stepWithRole("Нажать на кнопку Завершен", () -> {
            navCompletedButtonLocator.shouldHave(text("Выполнен")).click();
            orderNumberLinkCollection.last().scrollIntoView(true);
            orderNumberLinkCollection.first().scrollIntoView(true);
        });
    }
    public void navArchived () {
        stepWithRole("Нажать на кнопку В архиве", () -> {
            navArchiveButtonLocator.shouldHave(text("В архиве")).click();
            orderNumberLinkCollection.last().scrollIntoView(true);
            orderNumberLinkCollection.first().scrollIntoView(true);
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
        stepWithRole("Переключиться на вид карточек", () -> {
            cardViewButtonLocator.click();
            orderCardsCollection.last().shouldBe(visible, Duration.ofSeconds(20));
            //visibility of the last element in the collection
        });
        return this;
    }

    public HomeDispatcherPage switchToListView() {
        stepWithRole("Переключиться на вид списка", () -> {
            listViewButtonLocator.click();
            orderNumberLinkCollection.first().shouldBe(visible, Duration.ofSeconds(20));
        });
//        listViewButtonLocator.shouldBe(visible).click();
//        orderCardFirstLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage openOrderByNumber(String orderNumber) {
        stepWithRole("Открыть заказ по номеру: " + orderNumber, () -> {
            orderNumberLinkCollection.findBy(text(orderNumber)).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(orderNumber));
            System.out.println("givenOrderNumber: " + orderNumber);
        });
        return this;
    }

    public HomeDispatcherPage openOrderByIndex(int index) {
        stepWithRole("Открыть  заказ по индексу: "+ index + ", номер: " + orderNumberLinkCollection.get(index).getText(), () -> {
            orderNumberLinkCollection.get(index).click();
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
        int randomOrderNumber = (int) (Math.random() * orderNumberLinkCollection.size()+1);
        orderNumberLinkCollection.get(randomOrderNumber).shouldBe(visible).click();
        orderCardTitleLocator.shouldBe(visible);
        return this;
    }



}
