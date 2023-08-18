package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.pages.client.SelectServicePageClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.DocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster.InfoMasterTabOrderCardClientComponent;
import ru.gasworkers.dev.pages.context.ClientPages;

@AllArgsConstructor
public enum StateRepair {

    PUBLISHED("Опубликован"),
    HAS_OFFER("Есть предложения"),
    SCHEDULE_DATE("Согласование даты заказа"),
    WAIT_MASTER("Мастер в пути"),
    MASTER_START_WORK("Мастер приступил к работе"),
    MATERIAL_INVOICE_ISSUED("Выставлен счет на материалы"),
    MATERIAL_INVOICE_PAID("Оплачен счет на материалы"),
    ACTIONS_INVOICE_ISSUED("Выставлен счет на работы"),
    ACTIONS_INVOICE_PAID("Оплачен счет на работы"),
    MASTER_CREATE_ACT("Мастер создал акт"),
    CLIENT_SIGN_ACT("Клиент подписал акт");

    private static final StateRepairBuilder helper = new StateRepairBuilder();
    private final String state;

    public void checkButtons(ClientPages pages) {
        switch (this) {
            case PUBLISHED:
//                pages.getHomePage().buttons.
        }
    }

    public void checkLastOrderComponent(LastOrderProfileClientComponent component, LastOrderInfoResponseDto dto) {

        StateRepairBuilder.LastOrderInfoData data = helper.extractLastOrderInfoData(dto);
        component.checkOrderNumber(data.getOrderNumber());
        component.checkServiceType(data.getServiceType());
        component.checkAddress(data.getAddress());
        component.checkEquipment(data.getEquipments0());
        switch (this) {
            case PUBLISHED:
                component.offersCounter.noOffers();
                // todo time and stepper
                break;
            case HAS_OFFER:
                component.offersCounter.amount(data.getOffersCount());
                // todo time and stepper
                break;
            case SCHEDULE_DATE:
                component.offersCounter.noComponent();
                // todo time and stepper
                break;
            case WAIT_MASTER:
                component.offersCounter.noComponent();
                // todo assigned date
                // todo time and stepper
                break;

            // ... other cases ...
        }
    }

    public void checkCommonTab(CommonTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        StateRepairBuilder.OrderIdData data = helper.extractOrdersIdData(dto);
        tab.details.checkServiceType(ServiceType.REPAIR);
        tab.details.checkAddress(data.getAddress());
        tab.details.checkEquipment(data.getEquipments0());
        tab.details.checkDescription(data.getDescription());
        tab.details.checkClientFullName(data.getClientFullName());
        tab.details.checkPhone(data.getPhone());

        switch (this) {
            case SCHEDULE_DATE:
                tab.status.checkCurrentStatus(OrderStatus.SCHEDULE_DATE);
                tab.status.checkActivationStatusIsPaid(data.getActivationIsPaid());
                tab.status.checkActivationPricePayment(data.getActivationPrice());
                tab.status.checkActivationDatePayment(data.getActivationDate());
                tab.status.checkNoMaterialsStagePayment();
                tab.status.checkNoActionsStagePayment();
                tab.details.checkNoCompany();
                tab.details.checkDesiredDate(data.getDesiredDate());
                tab.details.checkDesiredTime(data.getDesiredTime());
                // todo time and stepper buttons
                break;
            case WAIT_MASTER:
                tab.status.checkCurrentStatus(OrderStatus.WAIT_MASTER);
                tab.status.checkActivationStatusIsPaid(data.getActivationIsPaid());
                tab.status.checkActivationPricePayment(data.getActivationPrice());
                tab.status.checkActivationDatePayment(data.getActivationDate());
                tab.status.checkNoMaterialsStagePayment();
                tab.status.checkNoActionsStagePayment();
                tab.details.checkCompanyFullName(data.getCompanyFullName());
//                     todo  ask where tab.details.checkScheduledTime(data.getScheduledTime());
        }
    }

    public void checkInfoMasterTab(InfoMasterTabOrderCardClientComponent tab, OrdersIdResponseDto dto) {
        StateRepairBuilder.OrderIdData data = helper.extractOrdersIdData(dto);
        tab.status.checkCurrentStatus(OrderStatus.SCHEDULE_DATE);
        switch (this) {
            case SCHEDULE_DATE:
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

    public void checkDocsTab(DocsTabOrderCardComponent docsTab, OrdersIdResponseDto dto) {
        StateRepairBuilder.OrderIdData data = helper.extractOrdersIdData(dto);
        docsTab.checkFinishLoading();

        switch (this) {
            case SCHEDULE_DATE:
                docsTab.checkFinalPrice("10");
                docsTab.noDocs();
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
