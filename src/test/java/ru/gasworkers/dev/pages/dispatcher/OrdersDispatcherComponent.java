package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrdersDispatcherComponent extends BaseComponent {
    SelenideElement
            self = driver.$("div.row.space-3").as("все заказы");
    ElementsCollection ordersCollection = self.$$("div.col-xxl-6.mb-3").as("Коллекция заказов");

    public OrdersDispatcherComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что заказы отображаются", () -> {
            self.shouldBe(Condition.visible);
            ordersCollection.shouldHave(CollectionCondition.sizeGreaterThan(0));
        });
    }

    public void openByLink(String orderNumber) {
        ordersCollection.find(Condition.partialText(orderNumber))
                .click();
    }

    public void openByButton(String orderNumber) {
        ordersCollection.find(Condition.partialText(orderNumber))
                .$(byTagAndText("span", "Принять"))
                .click();
    }

    public void refuse(String orderNumber) {
        ordersCollection.find(Condition.partialText(orderNumber))
                .$(byTagAndText("span", "Отказаться"))
                .click();
    }

    public void checkOrderCard(String orderId, OrdersIdResponseDto dto) {
        //todo check all the fields
    }
}
