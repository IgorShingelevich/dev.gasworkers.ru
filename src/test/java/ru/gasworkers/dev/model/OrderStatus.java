package ru.gasworkers.dev.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    //repair
    PUBLISHED("Новый заказ", "client"),
    NEW_TENDER("Тендер", "dispatcher"),
    PARTICIPATE_TENDER("Тендер", "dispatcher"),
    NEW_ORDER("Новый заказ", "dispatcher, client"),
    HAS_OFFER("Новый заказ", "dispatcher, client"),
    SCHEDULE_DATE("Согласование даты заказа", UserRole.CLIENT + ", " + UserRole.DISPATCHER),
    SELECT_MASTER("Выбор мастера", "dispatcher"),
    WAIT_MASTER("Мастер в пути", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    PAY_PRIMARY_VISIT("Оплата первичного выезда", "client"),
    MASTER_START_WORK("Мастер приступил к работе", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    MATERIAL_INVOICE_ISSUED("Мастер приступил к работе", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    MATERIAL_INVOICE_PAID("Мастер приступил к работе", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    ACTIONS_INVOICE_ISSUED("Мастер приступил к работе", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    ACTIONS_INVOICE_PAID("Мастер приступил к работе", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    MASTER_SIGN_ACT("Мастер заполнил данные по заказу", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    SIGN_MAINTENANCE_AGREEMENT_SMS("Подписание договора ТО смс", "client"),
    START_MAINTENANCE_WORK("Приезд мастера и проведение ТО", "master"),
    PAY_MAINTENANCE_WORK("Оплата ТО", "client"),
    SIGN_COMPLETION_ACT("Подписание Акта выполненных работ", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    CLIENT_SIGN_ACT("Завершен", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER),
    //consultation
    DRAFT("Черновик", UserRole.CLIENT.toString()),
    CLIENT_WAIT_MASTER("Ожидает когда мастер начнет консультацию", UserRole.CLIENT + ", " + UserRole.MASTER),
    MASTER_START_CONSULTATION("Мастер приступил к работе", UserRole.CLIENT + ", " + UserRole.MASTER),
    MASTER_FILLING_CONCLUSION("Мастер заполнил данные по заказу", UserRole.CLIENT + ", " + UserRole.MASTER),
    ORDER_COMPLETED("Завершен", UserRole.CLIENT + ", " + UserRole.MASTER),
    CANCELED("Заказ отменен", UserRole.CLIENT + ", " + UserRole.DISPATCHER + ", " + UserRole.MASTER);

    private final String description;

    private final String role;


    @Override
    public String toString() {
        return description;
    }

}
