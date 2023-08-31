package ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.DocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster.InfoMasterTabOrderCardClientComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateBuilder;

import static io.qameta.allure.Allure.step;

@AllArgsConstructor
public enum StateConsultation {
    DRAFT(null, null),
    CLIENT_WAIT_MASTER("Ожидает когда мастер начнет консультацию", null),
    MASTER_START_CONSULTATION("Мастер приступил к работе", null),
    MASTER_FILLED_CONCLUSION("Мастер заполнил данные по заказу", null),
    COMPLETED("Завершен", "Резюме по видеоконсультации создано мастером");

    public static final StateBuilder builder = new StateBuilder();
    private final String state;
    public final String notification;

    public void checkLastOrderComponent(LastOrderProfileClientComponent component, LastOrderInfoResponseDto dto) {
        step("Проверка компонента Последний заказ", () -> {
            StateBuilder.LastOrderInfoData data = builder.extractLastOrderInfoData(dto);
            switch (this) {
                case CLIENT_WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                    component.offersCounter.noComponent();
                    checkDetailsLastOrder(component, data);
                    component.noMasterVisitDateAndTime();
//                 todo   component.checkDesiredTime(data.getDesiredTime());
                    // todo stepper
                    break;
                case DRAFT:
                case COMPLETED:
                    component.offersCounter.noComponent();
                    component.noLastOrderCard();
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);

            }

        });
    }


    public void checkCommonTab(CommonTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        step("Убедиться, что вкладка Описание заказа в состоянии " + this, () -> {
            StateBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
            tab.status.checkStateConsultation(this, data, dto);
            tab.details.detailsConsultation(this, data);
            tab.suggestedMasterRepair.noSuggestedMastersCard();
            tab.buttons.checkStateConsultation(this);
            switch (this) {
                case CLIENT_WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                case MASTER_FILLED_CONCLUSION:
                case COMPLETED:
                    // todo stepper
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }


    public void checkInfoMasterTab(InfoMasterTabOrderCardClientComponent tab, OrdersIdResponseDto dto) {
        step("Убедиться, что вкладка Информация по работам в состоянии " + this, () -> {
            StateBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
            tab.status.checkStateConsultation(this, data, dto);
            tab.suggestedMasterCardRepair.noSuggestedMastersCard();
            tab.approvedMasterCard.checkStateConsultation(this, data);
            tab.buttons.checkStateConsultation(this);
            switch (this) {
                case CLIENT_WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                case MASTER_FILLED_CONCLUSION:
                case COMPLETED:
//                    tab.checkNoInfoBox();
                    tab.repairDetails.noTables();
                    // todo stepper
                    break;
                /*case PUBLISHED:
                case HAS_OFFER:
                    tab.checkNoInfoBox();
                    tab.repairDetails.checkNoRepairDetails();
                    break;
                case SCHEDULE_DATE:
                case WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                    tab.repairDetails.checkFinishLoading();
                    tab.repairDetails.checkMaterialsPrice("0");
                    tab.repairDetails.checkActionsPrice("0");
                    // todo time and stepper buttons notifications
                    break;
                case MATERIAL_INVOICE_ISSUED:
                case MATERIAL_INVOICE_PAID:
                    tab.repairDetails.checkFinishLoading();
                    tab.repairDetails.checkMaterialsFirstEquipmentPrice(dto, 0);
                    tab.repairDetails.checkMaterialsLogisticFeeAdded(dto);
                    tab.repairDetails.checkMaterialsPrice(dto, data);
                    tab.repairDetails.checkActionsPrice("0");
                    break;
                case ACTIONS_INVOICE_ISSUED:
                case ACTIONS_INVOICE_PAID:
                case MASTER_SIGN_ACT:
                case CLIENT_SIGN_ACT:
                    tab.repairDetails.checkMaterialsLogisticFeeAdded(dto);
                    tab.repairDetails.checkMaterialsFirstEquipmentPrice(dto, 0);
                    tab.repairDetails.checkMaterialsPrice(dto, data);
                    tab.repairDetails.checkActionsFirstItemPrice(dto, 0);
                    tab.repairDetails.checkActionsPrice(dto, data);
                    break;*/
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }

    public void checkDocsTab(DocsTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        StateBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
        tab.status.checkStateConsultation(this, data, dto);
        tab.suggestedMastersRepair.noSuggestedMastersCard();
        tab.buttons.checkStateConsultation(this);
        step("Убедиться, что вкладка Документы в состоянии " + this, () -> {
            switch (this) {
                case CLIENT_WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                case MASTER_FILLED_CONCLUSION:
                case COMPLETED:
                    tab.noDocs();
                    tab.checkTotalPrice(String.valueOf(dto.getData().getMaster().getConsultationPrice()));
                    // todo add tab.checkComputedToTalPrice - no field in json
               /* case HAS_OFFER:
                    tab.noDocs();
                    tab.checkNoTotalPrice();
                    break;
                case SCHEDULE_DATE:
                case WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                    tab.noDocs();
                    tab.checkTotalPrice("10");
                    // todo add tab.checkComputedToTalPrice - no field in json
                    break;
                case MATERIAL_INVOICE_ISSUED:
                case MATERIAL_INVOICE_PAID:
                    tab.noDocs();
                    tab.checkTotalPrice("3310");
                    break;
                case ACTIONS_INVOICE_ISSUED:
                case ACTIONS_INVOICE_PAID:
                    tab.noDocs();
                    tab.checkTotalPrice("6300");
                    // todo add tab.checkComputedToTalPrice - no field in json
                    break;
                case MASTER_SIGN_ACT:
                case CLIENT_SIGN_ACT:
                    tab.checkActDoc();
                    tab.checkTotalPrice("6300");
                    // todo add tab.checkComputedToTalPrice - no field in json
                    break;*/
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }

// todo check no select service page
    /*public void checkSelectServicePage(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
        step("Убедиться, что  стр выбора  компании в состоянии " + this, () -> {
            switch (this) {
                case PUBLISHED:
                    page.offersCounter.noOffers();
                    page.companyBoxRepair.checkNoOffers();
                    page.checkPublishedBanner();
                    break;
                case HAS_OFFER:
                    page.offersCounter.amount(page.companyBoxRepair.getAmountOfferBox());
                    page.checkHasOfferBanner();
                    page.companyBoxRepair.checkOfferBoxHasOfferState(0, dto);
                    break;

            }
        });
    }*/

    private void checkDetailsLastOrder(LastOrderProfileClientComponent component, StateBuilder.LastOrderInfoData data) {
        component.checkOrderNumber(data.getOrderNumber());
        component.checkServiceType(ServiceType.CONSULTATION.toString());
        component.checkAddress(data.getAddress());
        component.checkEquipment(data.getEquipments0());
    }

    @Override
    public String toString() {
        return state;
    }

    public String notification() {
        return notification;
    }
}
