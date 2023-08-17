package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.pages.client.SelectServicePageClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster.InfoMasterTabOrderCardClientComponent;

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

    private static final StateRepairBuilder helper = new StateRepairBuilder();
    private final String state;

    public void checkLastOrderComponent(LastOrderProfileClientComponent page, LastOrderInfoResponseDto dto) {
        StateRepairBuilder.LastOrderInfoData data = helper.extractLastOrderInfoData(dto);

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

    public void checkCommonTab(CommonTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        StateRepairBuilder.OrderIdData data = helper.extractOrdersIdData(dto);
        switch (this) {
            case SCHEDULE_TIME:
                tab.status.checkCurrentStatus(OrderStatus.SCHEDULE_VISIT);
                tab.status.checkActivationStatusIsPaid(data.getActivationIsPaid());
                tab.status.checkActivationPricePayment(data.getActivationPrice());
                tab.status.checkActivationDatePayment(data.getActivationDate());
                tab.status.checkNoMaterialsStagePayment();
                tab.status.checkNoActionsStagePayment();
                tab.details.checkServiceType(ServiceType.REPAIR);
                tab.details.checkNoServiceCompany();
                tab.details.checkPersonalData(data.getClientFullName());
                tab.details.checkAddress(data.getAddress());
                tab.details.checkPhone(data.getPhone());
                tab.details.checkEquipment(data.getEquipments0());
                tab.details.checkDesiredDate(data.getDesiredDate());
                tab.details.checkDesiredTime(data.getDesiredTime());
                tab.details.checkDescription(data.getDescription());
                // todo time and stepper buttons
                break;
        }
    }

    public void checkInfoMasterTab(InfoMasterTabOrderCardClientComponent tab, OrdersIdResponseDto dto) {
        StateRepairBuilder.OrderIdData data = helper.extractOrdersIdData(dto);
        switch (this) {
            case SCHEDULE_TIME:
                tab.status.checkCurrentStatus(OrderStatus.SCHEDULE_VISIT);
                tab.status.checkActivationStatusIsPaid(data.getActivationIsPaid());
                tab.status.checkActivationPricePayment(data.getActivationPrice());
                tab.status.checkActivationDatePayment(data.getActivationDate());
                tab.status.checkNoMaterialsStagePayment();
                tab.status.checkNoActionsStagePayment();
                tab.masterCard.checkFinishLoading();
                tab.masterCard.checkMasterFullName(data.getMasterFullName());
                tab.masterCard.checkMasterAvatar(data.getMasterAvatar());
                tab.masterCard.checkRegisterDate(data.getMasterRegisterDate());
                tab.masterCard.checkReviews(data.getMasterReviewCount());
                tab.masterCard.checkRating(data.getMasterRating());
                tab.repairDetails.checkFinishLoading();
                tab.repairDetails.checkMaterialsTotalPrice("0");
                tab.repairDetails.checkActionsTotalPrice("0");

                // todo time and stepper buttons
                break;
        }
    }

    public void checkSelectServicePage(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
        int offerIndex = 0;
        int ratingCompany = helper.getCalculateRatingCompany(dto);
        int ratingMaster = helper.getCalculateRatingMaster(dto);
        String visitPrice = helper.getVisitPrice(dto, offerIndex),
                offeredMasterFullName = helper.getOfferedMasterFullName(dto, offerIndex),
                offeredMasterAvatar = helper.getOfferedMasterAvatar(dto, offerIndex),
                offeredMasterReviewCount = helper.getOfferedMasterReviewCount(dto, offerIndex),
                offeredMasterCompletedOrders = helper.getOfferedMasterCompletedOrders(dto, offerIndex);


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
                page.companyBoxRepair.checkFullNameMaster(offerIndex, offeredMasterFullName);
                page.companyBoxRepair.checkAvatarMaster(offerIndex, offeredMasterAvatar);
                page.companyBoxRepair.checkRatingMaster(offerIndex, ratingMaster);
                page.companyBoxRepair.checkMasterReviewCount(offerIndex, offeredMasterReviewCount);
                page.companyBoxRepair.checkMasterCompletedOrders(offerIndex, offeredMasterCompletedOrders);
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
