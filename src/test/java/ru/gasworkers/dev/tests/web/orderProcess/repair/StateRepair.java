package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.pages.client.SelectServicePageClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.DocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster.InfoMasterTabOrderCardClientComponent;

import static io.qameta.allure.Allure.step;

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

    private static final StateRepairBuilder builder = new StateRepairBuilder();
    private final String state;

    public void checkLastOrderComponent(LastOrderProfileClientComponent component, LastOrderInfoResponseDto dto) {
        step("Проверка компонента Последний заказ", () -> {
            StateRepairBuilder.LastOrderInfoData data = builder.extractLastOrderInfoData(dto);
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
            }
        });
    }

    public void checkCommonTab(CommonTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        step("Убедиться, что вкладка Описание заказа в состоянии " + this, () -> {
            StateRepairBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
            tab.details.checkServiceType(ServiceType.REPAIR);
            tab.details.checkAddress(data.getAddress());
            tab.details.checkEquipment(data.getEquipments0());
            tab.details.checkDescription(data.getDescription());
            tab.buttons.buttonSet(this);
            tab.status.statusSet(this, data);
            switch (this) {
                case PUBLISHED:
                    tab.details.checkNoCompany();
                    tab.details.checkDesiredDate(data.getDesiredDate());
                    tab.details.checkDesiredTime(data.getDesiredTime());
                    tab.suggestedMasterRepair.checkNoSuggestedMastersRepair();
                    // todo time and stepper buttons
                    break;
                case HAS_OFFER:
                    StateRepairBuilder.SuggestedMasterRepairCommonTabOrderCardComponent dataSuggestedMaster = builder.extractMastersOrdersId(dto.getData().getMasters().get(0));
                    tab.details.checkNoCompany();
                    tab.details.checkClientFullName(data.getClientFullName());
                    tab.details.checkDesiredDate(data.getDesiredDate());
                    tab.details.checkDesiredTime(data.getDesiredTime());
                    tab.suggestedMasterRepair.checkMasterCountSuggestedMaster(dto.getData().getMasters().size());
                    tab.suggestedMasterRepair.checkHasOfferMastersDetails(tab.suggestedMasterRepair, dataSuggestedMaster, 0);
                    // todo time and stepper buttons
                    break;
                case SCHEDULE_DATE:
                    tab.details.checkNoCompany();
                    tab.details.checkClientFullName(data.getClientFullName());
                    tab.details.checkClientPhone(data.getPhone());
                    tab.details.checkDesiredDate(data.getDesiredDate());
                    tab.details.checkDesiredTime(data.getDesiredTime());
                    // todo time and stepper buttons
                    break;
                case WAIT_MASTER:
                    tab.status.checkNoActionsStagePayment();
                    tab.details.checkClientFullName(data.getClientFullName());
                    tab.details.checkClientPhone(data.getPhone());
                    tab.details.checkCompanyFullName(data.getCompanyFullName());
                    break;
//                     todo  ask where tab.details.checkScheduledTime(data.getScheduledTime());
            }
        });
    }


    public void checkInfoMasterTab(InfoMasterTabOrderCardClientComponent tab, OrdersIdResponseDto dto) {
        step("Убедиться, что вкладка Информация по работам в состоянии " + this, () -> {
            StateRepairBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
            tab.buttons.buttonSet(this);
            tab.status.statusSet(this, data);
            switch (this) {
                case PUBLISHED:
                    tab.checkNoInfoBox();
                    tab.masterCard.checkNoMasterCard();
                    tab.repairDetails.checkNoRepairDetails();
                    tab.suggestedMasterRepair.checkNoSuggestedMastersRepair();
                    break;
                case HAS_OFFER:
                    StateRepairBuilder.SuggestedMasterRepairCommonTabOrderCardComponent dataSuggestedMaster = builder.extractMastersOrdersId(dto.getData().getMasters().get(0));
                    tab.checkNoInfoBox();
                    tab.repairDetails.checkNoRepairDetails();
                    tab.suggestedMasterRepair.checkMasterCountSuggestedMaster(dto.getData().getMasters().size());
                    tab.suggestedMasterRepair.checkHasOfferMastersDetails(tab.suggestedMasterRepair, dataSuggestedMaster, 0);
                    break;
                case SCHEDULE_DATE:
                    tab.masterCard.checkFinishLoading();
                    tab.masterCard.checkMasterFullName(data.getMasterFullName());
                    tab.masterCard.checkMasterAvatar(data.getMasterAvatar());
                    tab.masterCard.checkRegisterDate(data.getMasterRegisterDate());
                    tab.masterCard.checkReviews(data.getMasterReviewCount());
                    tab.masterCard.checkRating(data.getMasterRating());
                    tab.repairDetails.checkFinishLoading();
                    tab.repairDetails.checkMaterialsTotalPrice("0");
                    tab.repairDetails.checkActionsTotalPrice("0");
                    // todo time and stepper buttons notifications
                    break;
            }
        });
    }

    public void checkDocsTab(DocsTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        StateRepairBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
        tab.buttons.buttonSet(this);
        tab.status.statusSet(this, data);
        step("Убедиться, что вкладка Документы в состоянии " + this, () -> {
            switch (this) {
                case PUBLISHED:
                    tab.checkNoDocs();
                    tab.checkNoTotalPrice();
                    tab.suggestedMastersRepair.checkNoSuggestedMastersRepair();
                    tab.buttons.checkShowOnMapButton();
                    tab.buttons.checkCancelButton();
                    break;
                case HAS_OFFER:
                    StateRepairBuilder.SuggestedMasterRepairCommonTabOrderCardComponent dataSuggestedMaster = builder.extractMastersOrdersId(dto.getData().getMasters().get(0));
                    tab.checkNoDocs();
                    tab.checkNoTotalPrice();
                    tab.suggestedMastersRepair.checkMasterCountSuggestedMaster(dto.getData().getMasters().size());
                    tab.suggestedMastersRepair.checkHasOfferMastersDetails(tab.suggestedMastersRepair, dataSuggestedMaster, 0);
                    break;
                case SCHEDULE_DATE:
                    tab.suggestedMastersRepair.checkNoSuggestedMastersRepair();
                    tab.checkFinalPrice("10");
                    break;
            }
        });
    }

    public void checkSelectServicePage(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
        step("Убедиться, что  стр выбора  компании в состоянии " + this, () -> {
            StateRepairHelper helper = new StateRepairHelper();
            int offerIndex = 0;
            int ratingCompany = helper.getSuggestServiceSuggestedMasterCompanyRating(dto);
            int ratingMaster = helper.getCalculateRatingMaster(dto);
            String visitPrice = helper.getServicePageOfferVisitPrice(dto, offerIndex),
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
            }
        });
    }

    @Override
    public String toString() {
        return state;
    }
}
