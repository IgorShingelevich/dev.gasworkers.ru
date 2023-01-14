package pages.profilePages.dispatcherPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderCardDispatcherPage extends BaseDispatcherPage{

    private final String PAGE_TITLE = "Заказ";

    SelenideElement
        pageTitleLocator = $(".page-title .h3.mb-2"),
        orderBlockLocator = $(".page-content #order"),
        orderNumberLocator = $(".order-number"),

        primaryButtonLocator = $(".btn.btn-primary"),


        alreadyAcceptedButtonLocator = $(".global-btn-wrapper.justify-content-end"),
        outlineButtonLocator = $(".btn.btn-outline-primary");

    ElementsCollection
        orderNavigationCollection = $$("#navigation-block li");

    public OrderCardDispatcherPage isOpened() {

        // isolate  in pageTitleLocator.wait(6000L) in try-catch block
        try {
            pageTitleLocator.wait(6000L);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        pageTitleLocator.shouldHave(text(PAGE_TITLE));
        orderBlockLocator.shouldBe(visible);
        return this;
    }

    public OrderCardDispatcherPage acceptOrder() {
        primaryButtonLocator.shouldBe(visible).scrollTo().click();
//        alreadyAcceptedButtonLocator.should(appear);
//        acceptButtonLocator.should(disappear);
//        declineButtonLocator.should(disappear);
        return this;
    }

    public OrderCardDispatcherPage isSelectMasterStatus() {
        primaryButtonLocator.shouldHave(text("Выбрать мастера")).shouldBe(visible, Duration.ofSeconds(10));
        outlineButtonLocator.shouldHave(text("Отменить заказ")).shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

    public OrderCardDispatcherPage selectMaster() {
          outlineButtonLocator.shouldHave(text("Отменить заказ")).shouldBe(visible, Duration.ofSeconds(10));
        primaryButtonLocator.shouldHave(text("Выбрать мастера")).shouldBe(visible, Duration.ofSeconds(10)).click();

        return this;
    }




    public OrderCardDispatcherPage declineOrder() {
        outlineButtonLocator.shouldBe(visible).shouldHave(text("Отказаться")).click();
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
