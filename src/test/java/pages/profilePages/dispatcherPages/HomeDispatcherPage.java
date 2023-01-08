package pages.profilePages.dispatcherPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomeDispatcherPage extends BaseDispatcherPage {

    SelenideElement
        dispatcherHomePageTitleLocator = $(".page-title .h3.mb-2"),
        mapViewButtonLocator = $(".action-btn.map-type"),
        cardViewButtonLocator = $(".action-btn.card-type"),
        listViewButtonLocator = $(".action-btn.list-type"),
        orderCardTitleLocator = $(".page-title .h3.mb-2"),
        map=$(".map-wrap .ymap-container .map-into"),
        orderCardListLocator =$(".order-cards.list"),
        newOrderStatusButtons= $$("div.top-filter__status--btn").get(0),
        inProgressOrderStatusButton = $$("div.top-filter__status--btn").get(1),
        completedOrderStatusButton =  $$("div.top-filter__status--btn").get(2),
        archivedOrderStatusButton = $$("div.top-filter__status--btn").get(3);

    ElementsCollection

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
//        mapViewButtonLocator.$(".active").shouldBe(visible);
        map.shouldBe(visible);
        newOrderStatusButtons.shouldBe(visible).click();
        inProgressOrderStatusButton.shouldBe(visible).click();
        completedOrderStatusButton.shouldNotBe(visible);
        archivedOrderStatusButton.shouldNotBe(visible);
        listViewButtonLocator.shouldBe(visible).click();
        completedOrderStatusButton.shouldBe(visible).click();
        archivedOrderStatusButton.shouldBe(visible).click();

        return this;
    }

    public HomeDispatcherPage switchToCardView() {
        cardViewButtonLocator.shouldBe(visible).click();
        return this;
    }

    public HomeDispatcherPage switchToListView() {
        listViewButtonLocator.shouldBe(visible).click();
        orderCardListLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage openFirstOrderByNumber() {
        switchToListView();
        ordersNumberLinkCollection.get(0).shouldBe(visible).click();
        orderCardTitleLocator.shouldBe(visible);
        return this;
    }

    public HomeDispatcherPage openFirstOrder() {
        switchToListView();
        orderActionDropdownCollection.get(0).shouldBe(visible).hover().click();
        actionsOpenOrderLinkCollection.get(0).shouldBe(visible).click();
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
