package pages.profilePages.dispatcherPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderCardDispatcherPage extends BaseDispatcherPage{

    private final String PAGE_TITLE = "Заказ";

    SelenideElement
        pageTitleLocator = $(".page-title .h3.mb-2"),
        orderBlockLocator = $(".page-content #order"),
        orderNumberLocator = $(".order-number"),
        acceptButtonLocator = $(".btn.btn-primary"),
        alreadyAcceptedButtonLocator = $(".global-btn-wrapper.justify-content-end"),
        declineButtonLocator = $(".btn.btn-outline-primary");

    ElementsCollection
        orderNavigationCollection = $$("#navigation-block li");

    public OrderCardDispatcherPage isOpened() {
        pageTitleLocator.shouldHave(text(PAGE_TITLE));
        orderBlockLocator.shouldBe(visible);
        return this;
    }

    public OrderCardDispatcherPage acceptOrder() {
        acceptButtonLocator.shouldBe(visible).scrollTo().click();
//        alreadyAcceptedButtonLocator.should(appear);
//        acceptButtonLocator.should(disappear);
//        declineButtonLocator.should(disappear);
        return this;
    }

    public OrderCardDispatcherPage declineOrder() {
        declineButtonLocator.shouldBe(visible).shouldHave(text("Отказаться")).click();
        return this;
    }

    public OrderCardDispatcherPage orderDescription() {
        orderNavigationCollection.get(0).click();
        return this;
    }

    public OrderCardDispatcherPage orderInfo() {
        orderNavigationCollection.get(1).click();
        return this;
    }

    public OrderCardDispatcherPage orderDocuments() {
        orderNavigationCollection.get(2).click();
        return this;
    }




}
