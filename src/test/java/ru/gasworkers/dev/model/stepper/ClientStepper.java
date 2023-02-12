package ru.gasworkers.dev.model.stepper;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public enum ClientStepper {
    PUBLISHED("Заказ опубликован"),
    CHECKOUT("Оформление заказа"),
    MASTER_DISPATCHING_PAYMENT("Оплата выезда мастера"),
    SIGN_AGREEMENT("Подпись договора ТО ВДГО"),
    SCHEDULE_VISIT("Согласование времени работ"),
    MASTER_DISPATCHED("Ожидание приезда мастера"),
    START_WORK("Мастер приступил к ремонту"),
    PAY_WORK("Оплата счета"),
    ISSUED_COMPLETION_ACT("Выставлен акт выполненных работ"),
    SIGN_COMPLETION_ACT("Подпись акта"),
    COMPLETED("Оставить отзыв"); //TODO before review and after review




    private final String title;

    @Override
    public String toString() {
        return title;
    }

}
