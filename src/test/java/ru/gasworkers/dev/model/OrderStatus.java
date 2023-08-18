package ru.gasworkers.dev.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    PUBLISHED("Новый заказ", "client"),
    NEW_TENDER("Тендер", "dispatcher"),
    PARTICIPATE_TENDER("Тендер", "dispatcher"),
    NEW_ORDER("Новый заказ", "dispatcher, client"),
    SCHEDULE_DATE("Согласование даты заказа", "dispatcher"),
    SELECT_MASTER("Выбор мастера", "dispatcher"),
    WAIT_MASTER("Мастер в пути", "dispatcher, master"),
    PAY_PRIMARY_VISIT("Оплата первичного выезда", "client"),
    SIGN_MAINTENANCE_ARGEEMENT_SMS("Подписание договора ТО смс", "client"),
    START_MAINTENANCE_WORK("Приезд мастера и проведение ТО", "master"),
    PAY_MAINTENANCE_WORK("Оплата ТО", "client"),
    SIGN_COMPLETION_ACT("Подписание Акта выполненных работ", "client"),
    COMPLETED("Завершен", "all");

    private final String description;

    private final String role;


    @Override
    public String toString() {
        return description;
    }

}
