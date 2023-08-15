package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.pages.client.SelectServicePageClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;

@AllArgsConstructor
public enum StateRepair {

    PUBLISHED("Опубликован"),
    HAS_OFFER("Есть предложения"),
    SCHEDULE_TIME("Согласование времени"),
    WAIT_MASTER("Ожидание мастера"),
    MASTER_START_WORK("Мастер приступил к работе"),
    MATERIAL_INVOICE_ISSUED("Выставлен счет на материалы"),
    MATERIAL_INVOICE_PAID("Оплачен счет на материалы"),
    ACTIONS_INVOICE_ISSUED("Выставлен счет на работы"),
    ACTIONS_INVOICE_PAID("Оплачен счет на работы"),
    MASTER_CREATE_ACT("Мастер создал акт"),
    CLIENT_SIGN_ACT("Клиент подписал акт");

    private final String state;


    public void checkLastOrderComponent(LastOrderProfileClientComponent page, LastOrderInfoResponseDto currentOrderInfoDto) {
        String
                orderNumber = currentOrderInfoDto.getData().getNumber(),
                serviceType = "Ремонт",
                address = currentOrderInfoDto.getData().getAddress(),
                equipments0 = currentOrderInfoDto.getData().getEquipments().get(0).getComputedTitle();
//        todo time and stepper
        Integer offersCount = currentOrderInfoDto.getData().getOffersCount();

        switch (this) {
            case PUBLISHED:
                page.checkOrderNumber(orderNumber);
                page.checkServiceType(serviceType);
                page.checkAddress(address);
                page.checkEquipment(equipments0);
                page.offersCounter.noOffers();
//        todo time and stepper
                break;
            case HAS_OFFER:
                page.checkOrderNumber(orderNumber);
                page.checkServiceType(serviceType);
                page.checkAddress(address);
                page.checkEquipment(equipments0);
                page.offersCounter.amount(offersCount);
//        todo time and stepper
                break;
            // ... other cases ...
        }
    }

    public void checkSelectServicePage(SelectServicePageClientPage selectServicePageClientPage, OrdersInfoResponseDto hasOfferOrderInfo) {
        switch (this) {
            case PUBLISHED:
                selectServicePageClientPage.checkAmountOfferBox(0);
                break;
            case HAS_OFFER:
                selectServicePageClientPage.checkAmountOfferBox(hasOfferOrderInfo.getData().getOffersCount());
                break;
            // ... other cases ...
        }
    }

    @Override
    public String toString() {
        return state;
    }
}
