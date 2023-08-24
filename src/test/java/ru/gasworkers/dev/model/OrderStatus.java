package ru.gasworkers.dev.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    PUBLISHED("Новый заказ", "client"),
    NEW_TENDER("Тендер", "dispatcher"),
    PARTICIPATE_TENDER("Тендер", "dispatcher"),
    NEW_ORDER("Новый заказ", "dispatcher, client"),
    HAS_OFFER("Новый заказ", "dispatcher, client"),
    SCHEDULE_DATE("Согласование даты заказа", Role.CLIENT + ", " + Role.DISPATCHER),
    SELECT_MASTER("Выбор мастера", "dispatcher"),
    WAIT_MASTER("Мастер в пути", Role.CLIENT + ", " + Role.DISPATCHER + ", " + Role.MASTER),
    PAY_PRIMARY_VISIT("Оплата первичного выезда", "client"),
    MASTER_START_WORK("Мастер приступил к работе", Role.CLIENT + ", " + Role.DISPATCHER + ", " + Role.MASTER),
    MATERIAL_INVOICE_ISSUED("Мастер приступил к работе", Role.CLIENT + ", " + Role.DISPATCHER + ", " + Role.MASTER),
    MATERIAL_INVOICE_PAID("Мастер приступил к работе", Role.CLIENT + ", " + Role.DISPATCHER + ", " + Role.MASTER),
    ACTIONS_INVOICE_ISSUED("Мастер приступил к работе", Role.CLIENT + ", " + Role.DISPATCHER + ", " + Role.MASTER),
    ACTIONS_INVOICE_PAID("Мастер приступил к работе", Role.CLIENT + ", " + Role.DISPATCHER + ", " + Role.MASTER),
    MASTER_SIGN_ACT("Мастер заполнил данные по заказу", Role.CLIENT + ", " + Role.DISPATCHER + ", " + Role.MASTER),
    SIGN_MAINTENANCE_AGREEMENT_SMS("Подписание договора ТО смс", "client"),
    START_MAINTENANCE_WORK("Приезд мастера и проведение ТО", "master"),
    PAY_MAINTENANCE_WORK("Оплата ТО", "client"),
    SIGN_COMPLETION_ACT("Подписание Акта выполненных работ", Role.CLIENT + ", " + Role.DISPATCHER + ", " + Role.MASTER),
    COMPLETED("Завершен", "all");

    private final String description;

    private final String role;


    @Override
    public String toString() {
        return description;
    }

}
