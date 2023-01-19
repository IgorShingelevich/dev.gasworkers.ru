package pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import model.client.OrderStatus;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class OrderCardDispatcherPage extends BaseDispatcherPage {

    public OrderCardDispatcherPage(RoleBrowser browser) {
        super(browser);
    }

    private final String PAGE_TITLE = "Заказ";

    SelenideElement
        pageTitleLocator = driver.$(".page-title .h3.mb-2"),
        orderBlockLocator = driver.$(".page-content #order"),
        orderNumberLocator = driver.$(".order-number"),
        orderStatusLocator = driver.$(".item-flex p.text"),
        primaryButtonLocator = driver.$(".btn.btn-primary"),


        alreadyAcceptedButtonLocator = driver.$(".global-btn-wrapper.justify-content-end"),
        outlineButtonLocator = driver.$(".btn.btn-outline-primary");

    ElementsCollection
        orderNavigationCollection = driver.$$("#navigation-block li");

    public void checkFinishLoading() {
        String factualOrderNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + factualOrderNumber + " загружена", () -> {
            pageTitleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
            orderBlockLocator.shouldBe(visible);
            System.out.println("Factual Dispatcher Order number: " + factualOrderNumber);
        });


    }

    public OrderCardDispatcherPage acceptOrder() {
        String factualOrderNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Принять заказ:" + factualOrderNumber , () -> {
            primaryButtonLocator.as("Принять").scrollTo().click();
        });

        return this;
    }

    public void checkOrderStatus( OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatusLocator.getText(), () ->

                    orderStatusLocator.shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что при участии в Тендере  представлена неактивная кнопка Уже участвуете ", () -> {
                //        acceptButtonLocator.should(disappear);
                //        declineButtonLocator.should(disappear);
                alreadyAcceptedButtonLocator.should(appear, Duration.ofSeconds(40));
            });
        });
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
