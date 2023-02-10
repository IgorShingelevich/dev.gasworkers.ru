package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.text;

public class OrderStatusCardComponent extends BaseTabOrderCardComponent{
    public OrderStatusCardComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            orderStateLocator = driver.$(".item-flex p.text").as("Статус заказа");

    public void currentState(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа " + orderStatus, () -> {
            switch (orderStatus) {
                case NEW_TENDER:
                    orderStateLocator.shouldHave(text(OrderStatus.NEW_TENDER.toString()));
                    break;
                case PARTICIPATE_TENDER:
                    orderStateLocator.shouldHave(text(OrderStatus.PARTICIPATE_TENDER.toString()));
                    break;
                case NEW_ORDER:
                    orderStateLocator.shouldHave(text(OrderStatus.NEW_ORDER.toString()));
                    break;
                case SCHEDULE_VISIT:
                    orderStateLocator.shouldHave(text(OrderStatus.SCHEDULE_VISIT.toString()));
                    break;
                case SELECT_MASTER:
                    orderStateLocator.shouldHave(text(OrderStatus.SELECT_MASTER.toString()));
                    break;
                case MASTER_DISPATCHED:
                    orderStateLocator.shouldHave(text(OrderStatus.MASTER_DISPATCHED.toString()));
                    break;
                case PAY_PRIMARY_VISIT:
                    orderStateLocator.shouldHave(text(OrderStatus.PAY_PRIMARY_VISIT.toString()));
                    break;
                case SIGN_MAINTENANCE_ARGEEMENT_SMS:
                    orderStateLocator.shouldHave(text(OrderStatus.SIGN_MAINTENANCE_ARGEEMENT_SMS.toString()));
                    break;
                case START_MAINTENANCE_WORK:
                    orderStateLocator.shouldHave(text(OrderStatus.START_MAINTENANCE_WORK.toString()));
                    break;
                case PAY_MAINTENANCE_WORK:
                    orderStateLocator.shouldHave(text(OrderStatus.PAY_MAINTENANCE_WORK.toString()));
                    break;
                case SIGN_COMPLETION_ACT:
                    orderStateLocator.shouldHave(text(OrderStatus.SIGN_COMPLETION_ACT.toString()));
                    break;
                case COMPLETED:
                    orderStateLocator.shouldHave(text(OrderStatus.COMPLETED.toString()));
                    break;
            }
        });
    }
    //TODO - nav tabs for all roles



}
