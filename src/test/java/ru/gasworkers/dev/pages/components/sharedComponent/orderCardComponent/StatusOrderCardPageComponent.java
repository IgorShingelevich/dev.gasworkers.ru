package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.*;

public class StatusOrderCardPageComponent extends BaseOrderCardComponent {
    ElementsCollection
            paymentCollection = driver.$$("ul.order-details__prices li").as("Коллекция платежей");
    SelenideElement
            orderStatusLocator = driver.$(".item-flex p.text").as("Статус заказа"),
            activationStagePaymentLocatorBox = driver.$$("ul.order-details__prices li").findBy(text("Активация безопасной сделки")).parent(),
            materialsStagePaymentLocatorBox = driver.$$("ul.order-details__prices li").findBy(text("Оплата материалов")).parent(),
            actionsStagePaymentLocatorBox = driver.$$("ul.order-details__prices li").findBy(text("Оплата ремонтных работ")).parent(),
            activationStatusPaymentLocator = activationStagePaymentLocatorBox.find(".order-details__prices--status .status"),
            activationPricePaymentLocator = activationStagePaymentLocatorBox.find(".order-details__prices--price strong"),
            activationStatusDatePaymentLocator = activationStagePaymentLocatorBox.find(".payment-date"),
            materialsStatusPaymentLocator = materialsStagePaymentLocatorBox.find(".order-details__prices--status .status"),
            materialsPricePaymentLocator = materialsStagePaymentLocatorBox.find(".order-details__prices--price strong"),
            materialsDatePaymentLocator = materialsStagePaymentLocatorBox.find(".payment-date"),
            actionsStatusPaymentLocator = actionsStagePaymentLocatorBox.find(".order-details__prices--status .status"),
            actionsPricePaymentLocator = actionsStagePaymentLocatorBox.find(".order-details__prices--price strong"),
            actionsDatePaymentLocator = actionsStagePaymentLocatorBox.find(".payment-date");

    public StatusOrderCardPageComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkCurrentStatus(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа " + orderStatus.toString(), () -> {
            orderStatusLocator.shouldHave(text(orderStatus.toString()));
        });
    }

    public void checkActivationStatusIsPaid(Boolean expectedStatus) {
        isPaid(expectedStatus, activationStatusPaymentLocator);
    }

    public void checkNoActivationStagePayment() {
        stepWithRole("Убедиться, что нет платежа за активацию", () -> {
            activationStagePaymentLocatorBox.shouldNotBe(visible);
        });
    }

    public void checkActivationPricePayment(String expectedPrice) {
        stepWithRole("Убедиться, что сумма платежа за активацию " + expectedPrice + " соответствует ожидаемой", () -> {
            activationPricePaymentLocator.shouldHave(partialText(expectedPrice));
        });
    }

    public void checkActivationDatePayment(String expectedDate) {
        stepWithRole("Убедиться, что дата платежа за активацию " + expectedDate + " соответствует ожидаемой", () -> {
            activationStatusDatePaymentLocator.shouldHave(text(expectedDate));
        });
    }

    public void checkMaterialsStatusIsPaid(Boolean expectedStatus) {
        isPaid(expectedStatus, materialsStatusPaymentLocator);
    }

    public void checkNoMaterialsStagePayment() {
        stepWithRole("Убедиться, что нет платежа за материалы", () -> {
            materialsStagePaymentLocatorBox.shouldNotBe(visible);
        });
    }

    public void checkMaterialsPricePayment(String expectedPrice) {
        stepWithRole("Убедиться, что сумма платежа за материалы " + expectedPrice + " соответствует ожидаемой", () -> {
            materialsPricePaymentLocator.shouldHave(text(expectedPrice));
        });
    }

    public void checkMaterialsDatePayment(String expectedDate) {
        stepWithRole("Убедиться, что дата платежа за материалы " + expectedDate + " соответствует ожидаемой", () -> {
            materialsDatePaymentLocator.shouldHave(text(expectedDate));
        });
    }

    public void checkActionsStatusIsPaid(Boolean expectedStatus) {
        isPaid(expectedStatus, actionsStatusPaymentLocator);
    }

    public void checkNoActionsStagePayment() {
        stepWithRole("Убедиться, что нет платежа за работы", () -> {
            actionsStagePaymentLocatorBox.shouldNotBe(visible);
        });
    }

    public void checkActionsPricePayment(String expectedPrice) {
        stepWithRole("Убедиться, что сумма платежа за работы " + expectedPrice + " соответствует ожидаемой", () -> {
            actionsPricePaymentLocator.shouldHave(text(expectedPrice));
        });
    }

    public void checkActionsDatePayment(String expectedDate) {
        stepWithRole("Убедиться, что дата платежа за работы " + expectedDate + " соответствует ожидаемой", () -> {
            actionsDatePaymentLocator.shouldHave(text(expectedDate));
        });
    }

    private void isPaid(Boolean expectedStatus, SelenideElement statusPaymentLocator) {
        stepWithRole("Убедиться, что статус платежа " + expectedStatus.toString(), () -> {
            if (expectedStatus) {
                statusPaymentLocator.shouldHave(Condition.cssClass("green"));
            } else {
                statusPaymentLocator.shouldHave(Condition.cssClass("red"));
            }
        });
    }
}