package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
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
    private static final StateRepairHelper helper = new StateRepairHelper();


    public void checkLastOrderComponent(LastOrderProfileClientComponent page, LastOrderInfoResponseDto dto) {
        StateRepairHelper.OrderData orderData = helper.extractOrderData(dto);

        switch (this) {
            case PUBLISHED:
                page.checkOrderNumber(orderData.getOrderNumber());
                page.checkServiceType(orderData.getServiceType());
                page.checkAddress(orderData.getAddress());
                page.checkEquipment(orderData.getEquipments0());
                page.offersCounter.noOffers();
                // todo time and stepper
                break;
            case HAS_OFFER:
                page.checkOrderNumber(orderData.getOrderNumber());
                page.checkServiceType(orderData.getServiceType());
                page.checkAddress(orderData.getAddress());
                page.checkEquipment(orderData.getEquipments0());
                page.offersCounter.amount(orderData.getOffersCount());
                // todo time and stepper
                break;
            // ... other cases ...
        }
    }

    public void checkSelectServicePage(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
        int offerIndex = 0;
        int ratingCompany = helper.calculateRatingCompany(dto);
        int ratingMaster = helper.calculateRatingMaster(dto);
        String visitPrice = helper.getVisitPrice(dto, offerIndex),
                fullMasterName = helper.getMasterFullName(dto, offerIndex),
                masterAvatar = helper.getMasterAvatar(dto, offerIndex),
                masterReviewCount = helper.getMasterReviewCount(dto, offerIndex),
                masterCompletedOrders = helper.getMasterCompletedOrders(dto, offerIndex);


        //todo offerCount
        switch (this) {
            case PUBLISHED:
                page.offersCounter.noOffers();
                page.companyBoxRepair.checkNoOffers();
                page.checkPublishedBanner();
                break;
            case HAS_OFFER:
                page.offersCounter.amount(page.companyBoxRepair.getAmountOfferBox());
                page.checkHasOfferBanner();
                page.companyBoxRepair.checkBoxTitle(offerIndex);
                page.companyBoxRepair.checkGeoTag(offerIndex);
                page.companyBoxRepair.checkRatingCompany(offerIndex, ratingCompany);
                page.companyBoxRepair.checkAvatarCompany(offerIndex);
                page.companyBoxRepair.checkVisitPrice(offerIndex, visitPrice);
                page.companyBoxRepair.checkNotificationPaymentAfterArrival(offerIndex);
                page.companyBoxRepair.checkFullNameMaster(offerIndex, fullMasterName);
                page.companyBoxRepair.checkAvatarMaster(offerIndex, masterAvatar);
                page.companyBoxRepair.checkRatingMaster(offerIndex, ratingMaster);
                page.companyBoxRepair.checkMasterReviewCount(offerIndex, masterReviewCount);
                page.companyBoxRepair.checkMasterCompletedOrders(offerIndex, masterCompletedOrders);
                page.companyBoxRepair.checkButtonActive(offerIndex);
                break;
            // ... other cases ...
        }
    }

    @Override
    public String toString() {
        return state;
    }
}
