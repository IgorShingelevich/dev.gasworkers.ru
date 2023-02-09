package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderState;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.text;

public class OrderStateCardComponent extends BaseTabOrderCardComponent{
    public OrderStateCardComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            orderStateLocator = driver.$(".item-flex p.text").as("Статус заказа");

    public void currentState(OrderState orderState) {
        stepWithRole("Убедиться, что статус заказа " + orderState, () -> {
            switch (orderState) {
                case NEW_TENDER:
                    orderStateLocator.shouldHave(text(OrderState.NEW_TENDER.toString()));
                    break;
                case PARTICIPATE_TENDER:
                    orderStateLocator.shouldHave(text(OrderState.PARTICIPATE_TENDER.toString()));
                    break;
                case NEW_ORDER:
                    orderStateLocator.shouldHave(text(OrderState.NEW_ORDER.toString()));
                    break;
                case SCHEDULE_VISIT:
                    orderStateLocator.shouldHave(text(OrderState.SCHEDULE_VISIT.toString()));
                    break;
                case SELECT_MASTER:
                    orderStateLocator.shouldHave(text(OrderState.SELECT_MASTER.toString()));
                    break;
                case MASTER_DISPATCHED:
                    orderStateLocator.shouldHave(text(OrderState.MASTER_DISPATCHED.toString()));
                    break;
                case PAY_PRIMARY_VISIT:
                    orderStateLocator.shouldHave(text(OrderState.PAY_PRIMARY_VISIT.toString()));
                    break;
                case SIGN_MAINTENANCE_ARGEEMENT_SMS:
                    orderStateLocator.shouldHave(text(OrderState.SIGN_MAINTENANCE_ARGEEMENT_SMS.toString()));
                    break;
                case START_MAINTENANCE_WORK:
                    orderStateLocator.shouldHave(text(OrderState.START_MAINTENANCE_WORK.toString()));
                    break;
                case PAY_MAINTENANCE_WORK:
                    orderStateLocator.shouldHave(text(OrderState.PAY_MAINTENANCE_WORK.toString()));
                    break;
                case SIGN_COMPLETION_ACT:
                    orderStateLocator.shouldHave(text(OrderState.SIGN_COMPLETION_ACT.toString()));
                    break;
                case COMPLETE:
                    orderStateLocator.shouldHave(text(OrderState.COMPLETE.toString()));
                    break;
            }
        });
    }
    //TODO - nav tabs for all roles



}
