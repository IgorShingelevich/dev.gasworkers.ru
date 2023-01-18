package model.client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    NEW_ORDER("Новый заказ"),
    SELECT_MASTER("Выбор мастера"),
    PAY_PRIMARY_VISIT("Оплата первичного выезда"),
    SIGN_MAINTENANCE_ARGEEMENT_SMS("Подписание договора ТО смс"),
    SCHEDULE_VISIT("Согласование времени с диспетчером"),
    START_MAINTENANCE_WORK("Приезд мастера и проведение ТО"),
    PAY_MAINTENANCE_WORK("Оплата ТО"),
    SIGN_WORK_CERTIFICATE("Подписание договора ТО"),
    TRANSFER_AGREEMENT_MOSOBLGAS("Передача договора d Мособлгаз");


    private final String description;

    @Override
    public String toString() {
        return description;
    }


}
