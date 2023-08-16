package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.pages.client.OrderCardClientPage;
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

    private static final StateRepairHelper helper = new StateRepairHelper();
    private final String state;

    public void checkLastOrderComponent(LastOrderProfileClientComponent page, LastOrderInfoResponseDto dto) {
        StateRepairHelper.LastOrderInfoData data = helper.extractLastOrderInfoData(dto);

        switch (this) {
            case PUBLISHED:
                page.checkOrderNumber(data.getOrderNumber());
                page.checkServiceType(data.getServiceType());
                page.checkAddress(data.getAddress());
                page.checkEquipment(data.getEquipments0());
                page.offersCounter.noOffers();
                // todo time and stepper
                break;
            case HAS_OFFER:
                page.checkOrderNumber(data.getOrderNumber());
                page.checkServiceType(data.getServiceType());
                page.checkAddress(data.getAddress());
                page.checkEquipment(data.getEquipments0());
                page.offersCounter.amount(data.getOffersCount());
                // todo time and stepper
                break;
            case SCHEDULE_TIME:
                page.checkOrderNumber(data.getOrderNumber());
                page.checkServiceType(data.getServiceType());
                page.checkAddress(data.getAddress());
                page.checkEquipment(data.getEquipments0());
                page.offersCounter.checkNoSelf();
                // todo time and stepper
                break;
            // ... other cases ...
        }
    }

    public void checkOrderCardPage(OrderCardClientPage page, OrdersIdResponseDto scheduleTimeOrdersIdResponse) {
        StateRepairHelper.OrderIdData data = helper.extractOrdersIdData(scheduleTimeOrdersIdResponse);
        switch (this) {
            case SCHEDULE_TIME:
                page.checkOrderNumber(data.getOrderNumber());
                page.commonTab.status.checkCurrentStatus(OrderStatus.SCHEDULE_VISIT);
                page.commonTab.status.checkActivationStatusIsPaid(data.getActivationIsPaid());
                page.commonTab.status.checkActivationAmountPayment(data.getActivationAmount());
                page.commonTab.status.checkActivationDatePayment(data.getActivationDate());
                page.commonTab.status.checkNoMaterialsStagePayment();
                page.commonTab.status.checkNoActionsStagePayment();
                page.commonTab.details.checkServiceType(ServiceType.REPAIR);
                page.commonTab.details.checkNoServiceCompany();
                page.commonTab.details.checkPersonalData(data.getClientFullName());
                page.commonTab.details.checkAddress(data.getAddress());
                page.commonTab.details.checkPhone(data.getPhone());
                page.commonTab.details.checkEquipment(data.getEquipments0());
                page.commonTab.details.checkDesiredDate(data.getDesiredDate());
                page.commonTab.details.checkDesiredTime(data.getDesiredTime());
                page.commonTab.details.checkDescription(data.getDescription());
                // todo time and stepper
                break;
        }
    }

    public void checkSelectServicePage(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
        int offerIndex = 0;
        int ratingCompany = helper.getCalculateRatingCompany(dto);
        int ratingMaster = helper.getCalculateRatingMaster(dto);
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
