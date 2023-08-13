package ru.gasworkers.dev.tests.api.story.repair.actions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersSaveActionsRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ActionsIssuedRepairCase {
    UI_CALCULATION("UI calculation"),
    NEGATIVE_PRICE("Negative price"),
    NEGATIVE_QTY("Negative qty"),
    ZERO_PRICE("Zero price"),
    ZERO_QTY("Zero qty"),
    NULL_PRICE("Null price"),
    NULL_QTY("Null qty"),
    NULL_ORDER_ID("Null order id"),
    WRONG_ORDER_ID("Wrong order id"),
    OTHER_USER_ORDER_ID("Other user order id"),
    NULL_ACTION("Null action"),
    EMPTY_ACTION("Empty action");

    private final String description;

    private static OrdersSaveActionsRequestDto actionRequest(Integer qty, Double price, Integer orderId) {
        return OrdersSaveActionsRequestDto.builder()
                .orderId(orderId)
                .paid(false)
                .actions(List.of(OrdersSaveActionsRequestDto.Actions.builder()
                        .title("Work name")
                        .qty(qty)
                        .price(price)
                        .searchQuery("")
                        .actionList(Collections.emptyList())
                        .searchActive(false)
                        .build()))
                .build();
    }

    public OrdersSaveActionsRequestDto getRequestDto(Integer orderId) {
        switch (this) {
            case UI_CALCULATION:
                return actionRequest(3, 3.11, orderId);
            case NEGATIVE_PRICE:
                return actionRequest(3, -3.11, orderId);
            case NEGATIVE_QTY:
                return actionRequest(-3, 3.11, orderId);
            case ZERO_PRICE:
                return actionRequest(3, 0.0, orderId);
            case ZERO_QTY:
                return actionRequest(0, 3.11, orderId);
            case NULL_PRICE:
                return actionRequest(3, null, orderId);
            case NULL_QTY:
                return actionRequest(null, 3.11, orderId);
            case NULL_ORDER_ID:
                return actionRequest(3, 3.11, null);
            case WRONG_ORDER_ID:
                return actionRequest(3, 3.11, 9999999);
            case OTHER_USER_ORDER_ID:
                return actionRequest(3, 3.11, 8000);
            case NULL_ACTION:
                return OrdersSaveActionsRequestDto.builder()
                        .orderId(orderId)
                        .paid(false)
                        .actions(null)
                        .build();
            case EMPTY_ACTION:
                return OrdersSaveActionsRequestDto.builder()
                        .orderId(orderId)
                        .paid(false)
                        .actions(Collections.emptyList())
                        .build();
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
