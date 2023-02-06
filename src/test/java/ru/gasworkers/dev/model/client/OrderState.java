package ru.gasworkers.dev.model.client;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderState {
    NEW_TENDER("Тендер", "dispatcher"),
    PARTICIPATE_TENDER("Тендер", "dispatcher"),
    NEW_ORDER("Новый заказ", "dispatcher, client"),
    SCHEDULE_VISIT("Согласование даты заказа", "dispatcher"),
    SELECT_MASTER("Выбор мастера", "dispatcher"),
    MASTER_DISPATCHED("Мастер в пути", "dispatcher, master"),
    PAY_PRIMARY_VISIT("Оплата первичного выезда", "client"),
    SIGN_MAINTENANCE_ARGEEMENT_SMS("Подписание договора ТО смс", "client"),
    START_MAINTENANCE_WORK("Приезд мастера и проведение ТО", "master"),
    PAY_MAINTENANCE_WORK("Оплата ТО", "client"),
    SIGN_WORK_CERTIFICATE("Подписание договора ТО", "client"),
    TRANSFER_AGREEMENT_MOSOBLGAS("Передача договора в Мособлгаз", "client"),
    COMPLETE("Завершен", "all");

    private final String description;

    private final String role;


    @Override
    public String toString() {
        return description;
    }

}
