package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

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
        mapOrderTabsCollection = driver.$$("[id*=order-item]").as("Коллекция табов заказов на карте"),
        navTabsCollection = driver.$$("div.top-filter__status--btn").as("Коллекция кнопок навигации"),
        orderCardsCollection = driver.$$("div.order-card").as("Коллекция карточек заказов"),
        orderNumberLinkCollection = driver.$$("p.h5.link-blue.pointer").as("Коллекция номеров заказов"),
        orderActionDropdownCollection = driver.$$("button.actions__btn").as("Коллекция выападающих действий"),
        actionsOpenOrderLinkCollection = driver.$$x("//a[@class='actions__slot--link']").as("Коллекция  выпадающих ссылок открытия заказа"),
        actionsArchiveOrderLinkCollection = driver.$$x("(//button[contains(@class,'actions__slot--btn')])");

    SelenideElement
        dispatcherHomePageTitleLocator = driver.$(".page-title .h3.mb-2").as("Заголовок страницы"),
        navNewTabLocator = navTabsCollection.get(0).as("Новый"),
        navInProgressTabLocator = navTabsCollection.get(1).as("В работе"),
        navCompletedTabLocator = navTabsCollection.get(2).as("Выполнен"),
        navArchiveTabLocator = navTabsCollection.get(3).as("В архиве"),
        searchInputLocator = driver.$("input[placeholder*=номер]").as("Поле поиска"),
        searchButtonLocator = driver.$("button[type=button].btn-search").as("Кнопка поиска"),
        mapViewButtonLocator = driver.$("div.action-btn.map-type").as("Кнопка переключения вида карта"),
        cardViewButtonLocator = driver.$("div.action-btn.card-type").as("Кнопка переключения вида карточки"),
        listViewButtonLocator = driver.$("div.action-btn.list-type").as("Кнопка переключения вида список"),
        orderCardTitleLocator = driver.$(".page-title .h3.mb-2").as("Заголовок карточки заказа"),
        mapContainerLocator = driver.$("div.map-wrap").as("Контейнер карты"),
        mapElemetLocator =   driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты");


    public HomeDispatcherPage checkFinishLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            //CollectionCondition.sizeGreaterThan
            mapOrderTabsCollection.shouldHave(CollectionCondition.sizeGreaterThan(0 ), Duration.ofSeconds(10));
            mapElemetLocator.shouldBe(visible, Duration.ofSeconds(10));
        });
        return this;
    }

    public void navNew () {
        stepWithRole("Нажать на вкладку Новый", () -> {
            navNewTabLocator.shouldHave(text("Новый")).click();
            orderNumberLinkCollection.last().scrollIntoView(true);
            orderNumberLinkCollection.first().scrollIntoView(true);
        });
    }

    public void navInProgress () {
        stepWithRole("Нажать на вкладку В работе", () -> {
            navInProgressTabLocator.shouldHave(text("В работе")).click();
            //scrollTo( ordersNumberLinkCollection.last() );
            orderNumberLinkCollection.last().scrollIntoView(true);
            orderNumberLinkCollection.first().scrollIntoView(true);
        });
    }

    public void navCompleted () {
        stepWithRole("Нажать на вкладку Завершен", () -> {
            navCompletedTabLocator.shouldHave(text("Выполнен")).click();
            orderNumberLinkCollection.last().scrollIntoView(true);
            orderNumberLinkCollection.first().scrollIntoView(true);
        });
    }
    public void navArchived () {
        stepWithRole("Нажать на вкладку В архиве", () -> {
            navArchiveTabLocator.shouldHave(text("В архиве")).click();
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

    public HomeDispatcherPage searchByNumber(String orderNumber) {
        stepWithRole("Поиск заказа по номеру: " + orderNumber, () -> {
            searchInputLocator.setValue(orderNumber);
            searchButtonLocator.click();
            orderNumberLinkCollection.findBy(text(orderNumber)).shouldBe(visible, Duration.ofSeconds(20));
        });
        return this;
    }

    public HomeDispatcherPage openOrderByNumber(String orderNumber) {
        stepWithRole("Открыть заказ по номеру: " + orderNumber, () -> {
            orderNumberLinkCollection.findBy(text(orderNumber)).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10)).shouldHave(text(orderNumber));
            System.out.println("dispatcherOrderNumber: " + orderNumber);
        });
        return this;
    }

    public HomeDispatcherPage openOrderByIndex(int index) {
        stepWithRole("Открыть  заказ по индексу: "+ index + ", номер: " + orderNumberLinkCollection.get(index).getText(), () -> {
            orderNumberLinkCollection.get(index).click();
            orderCardTitleLocator.shouldBe(visible, Duration.ofSeconds(10));
            System.out.println("dispatcherOrderNumber:  " + orderCardTitleLocator.getText() + " index: " + index);
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

    public HomeDispatcherPage openRandomOrder() {
        switchToListView();
        int randomOrderNumber = (int) (Math.random() * orderNumberLinkCollection.size()+1);
        orderNumberLinkCollection.get(randomOrderNumber).shouldBe(visible).click();
        orderCardTitleLocator.shouldBe(visible);
        return this;
    }



}
