package model.client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    NEW_TENDER("Tендер"),
    NEW_ORDER("Новый заказ"),
    SCHEDULE_VISIT("Согласование даты заказа"),
    SELECT_MASTER("Выбор мастера"),
    MASTER_DISPATCHED("Мастер в пути"),
    PAY_PRIMARY_VISIT("Оплата первичного выезда"),
    SIGN_MAINTENANCE_ARGEEMENT_SMS("Подписание договора ТО смс"),
    START_MAINTENANCE_WORK("Приезд мастера и проведение ТО"),
    PAY_MAINTENANCE_WORK("Оплата ТО"),
    SIGN_WORK_CERTIFICATE("Подписание договора ТО"),
    TRANSFER_AGREEMENT_MOSOBLGAS("Передача договора d Мособлгаз"),
    COMPLETE("Завершен");


    private final String description;

    @Override
    public String toString() {
        return description;
    }


}
