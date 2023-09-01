package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.pages.client.SelectServicePageClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.DocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster.InfoMasterTabOrderCardClientComponent;

import static io.qameta.allure.Allure.step;

@AllArgsConstructor
public enum StateRepair {

    PUBLISHED("Опубликован", " опубликован"),
    HAS_OFFER("Есть предложения", "Отклик на заявку"),
    SCHEDULE_DATE("Согласование даты заказа", "Оплатите счет по заказу"),
    WAIT_MASTER("Мастер в пути", "Назначено время заказа"),
    MASTER_START_WORK("Мастер приступил к работе", null),
    MATERIAL_INVOICE_ISSUED("Выставлен счет на материалы", "Оплатите счет по заказу"),
    MATERIAL_INVOICE_PAID("Оплачен счет на материалы", null),
    ACTIONS_INVOICE_ISSUED("Выставлен счет на работы", "Оплатите счет по заказу"),
    ACTIONS_INVOICE_PAID("Оплачен счет на работы", null),
    MASTER_SIGN_ACT("Мастер заполнил данные по заказу", "Акт выполненных работ сформирован"),
    CLIENT_SIGN_ACT("Клиент подписал акт", "Оставьте отзыв по заявке");

    public static final StateBuilder builder = new StateBuilder();
    private final String state;
    public final String notification;


    public void checkLastOrderComponent(LastOrderProfileClientComponent component, LastOrderInfoResponseDto dto) {
        step("Проверка компонента Последний заказ", () -> {
            StateBuilder.LastOrderInfoData data = builder.extractLastOrderInfoData(dto);
            switch (this) {
                case PUBLISHED:
                    component.offersCounter.noOffers();
                    checkDetailsLastOrder(component, data);
                    component.noMasterVisitDateAndTime();
//                 todo   component.checkDesiredTime(data.getDesiredTime());
                    // todo stepper
                    break;
                case HAS_OFFER:
                    component.offersCounter.amount(data.getOffersCount());
                    checkDetailsLastOrder(component, data);
                    component.noMasterVisitDateAndTime();
//                 todo   component.checkDesiredTime(data.getDesiredTime());
                    // todo stepper
                    break;
                case SCHEDULE_DATE:
                    component.offersCounter.noComponent();
                    checkDetailsLastOrder(component, data);
                    //                 todo   component.checkDesiredTime(data.getDesiredTime());
                    // todo stepper
                    break;
                case WAIT_MASTER:
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_ISSUED:
                case MATERIAL_INVOICE_PAID:
                case ACTIONS_INVOICE_ISSUED:
                case ACTIONS_INVOICE_PAID:
                case MASTER_SIGN_ACT:
                    component.offersCounter.noComponent();
                    checkDetailsLastOrder(component, data);
                    component.noDesiredDate();
                    component.noDesiredTime();
                    //                   todo component.checkMasterVisitDateAndTime(data.getMasterVisitDateAndTime());
                    // todo  stepper
                    break;
                case CLIENT_SIGN_ACT:
                    component.offersCounter.noComponent();
                    component.noLastOrderCard();
                    //                   todo component.checkMasterVisitDateAndTime(data.getMasterVisitDateAndTime());
                    // todo  stepper
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);

            }

        });
    }


    public void checkCommonTab(CommonTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        step("Убедиться, что вкладка Описание заказа в состоянии " + this, () -> {
            StateBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
            tab.status.checkStateRepair(this, data, dto);
            tab.details.detailsRepair(this, data);
            tab.suggestedMasterRepair.checkState(this, dto, 0);
            tab.buttons.checkStateRepair(this);
            switch (this) {
                case PUBLISHED:
                    // todo stepper
                    break;
                case HAS_OFFER:
                    // todo stepper
                    break;
                case SCHEDULE_DATE:
                    // todo stepper
                    break;
                case WAIT_MASTER:
                    // todo stepper
                case MASTER_START_WORK:
                    // todo stepper
                    break;
                case MATERIAL_INVOICE_ISSUED:
                    // todo stepper
                    break;
                case MATERIAL_INVOICE_PAID:
                    // todo stepper
                    break;
                case ACTIONS_INVOICE_ISSUED:
                    // todo stepper
                    break;
                case ACTIONS_INVOICE_PAID:
                    // todo stepper
                    break;
                case MASTER_SIGN_ACT:
                    // todo stepper
                    break;
                case CLIENT_SIGN_ACT:
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
            tab.status.checkStateRepair(this, data, dto);
            tab.suggestedMasterCardRepair.checkState(this, dto, 0);
            tab.approvedMasterCard.checkStateRepair(this, data);
            tab.buttons.checkStateRepair(this);
            switch (this) {
                case PUBLISHED:
                case HAS_OFFER:
                    tab.checkNoInfoBox();
                    tab.repairDetails.noTables();
                    break;
                case SCHEDULE_DATE:
                case WAIT_MASTER:
                case MASTER_START_WORK:
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
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }

    public void checkDocsTab(DocsTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        StateBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
        tab.status.checkStateRepair(this, data, dto);
        tab.suggestedMastersRepair.checkState(this, dto, 0);
        tab.buttons.checkStateRepair(this);
        step("Убедиться, что вкладка Документы в состоянии " + this, () -> {
            switch (this) {
                case PUBLISHED:
                case HAS_OFFER:
                    tab.noDocs();
                    tab.checkNoTotalPrice();
                    break;
                case SCHEDULE_DATE:
                case WAIT_MASTER:
                case MASTER_START_WORK:
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
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }

    public void checkSelectServicePage(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
        step("Убедиться, что  стр выбора  компании в состоянии " + this, () -> {
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
                    page.companyBoxRepair.checkOfferBoxHasOfferState(0, dto);
                    break;

            }
        });
    }

    private void checkDetailsLastOrder(LastOrderProfileClientComponent component, StateBuilder.LastOrderInfoData data) {
        component.checkOrderNumber(data.getOrderNumber());
        component.checkServiceType(data.getServiceType());
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
