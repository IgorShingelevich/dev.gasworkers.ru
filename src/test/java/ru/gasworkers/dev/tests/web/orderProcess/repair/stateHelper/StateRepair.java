package ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.pages.client.SelectServicePageClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.DocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster.InfoMasterTabOrderCardClientComponent;

import static io.qameta.allure.Allure.step;

@AllArgsConstructor
public enum StateRepair {
    DRAFT("Черновик", null),
    PUBLISHED("опубликован", " опубликован"),
    PUBLISHED_STOPPED_COUNTDOWN("опубликован", " опубликован"), //  select service  page state
    CANCEL_CLIENT_PUBLISHED("Заказ отменен", null),
    REFUSED_OFFER_DISPATCHER("Диспетчер отклонил новый тендер", null),
    HAS_SERVICE_OFFER("Отклик на заявку", "Отклик на заявку"),
    HAS_SUPER_OFFER("Отклик на заявку", "Отклик на заявку"),
    CANCEL_CLIENT_HAS_OFFER("Заказ отменен", "Заказ отменен"),
    CANCEL_DISPATCHER_HAS_OFFER("Заказ отменен", "Заказ отменен"),
    CLIENT_PAID_SUPER_ACTIVATION("Согласование даты заказа", "Отклик на заявку"),  // yellow state, activation is paid, assigned superMaster, client wait SuperDispatcher to assign the actual Service
    SUPER_DISPATCHER_ASSIGN_SERVICE("Согласование даты заказа", "Отклик на заявку"), // assignedService
    SCHEDULE_SERVICE_MASTER("Согласование даты заказа", null), // assignedServiceMaster and Prices
    WAIT_MASTER("Мастер в пути", "Назначено время заказа"), //assignedTime
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
//                 todo   component.checkSelectedTime(data.getDesiredTime());
                    // todo desired time and date
                    // todo stepper
                    break;
                case CANCEL_CLIENT_PUBLISHED:
                    component.offersCounter.noComponent();
                    checkDetailsLastOrder(component, data);
                    component.noMasterVisitDateAndTime();
//                 todo   component.checkSelectedTime(data.getDesiredTime());
                    // todo desired time and date
                    // todo stepper
                    break;
                case HAS_SUPER_OFFER:
                case HAS_SERVICE_OFFER:
                    component.offersCounter.amount(data.getOffersCount());
                    checkDetailsLastOrder(component, data);
                    component.noMasterVisitDateAndTime();
//                 todo   component.checkDesiredTime(data.getDesiredTime());
                    // todo stepper
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION:
                case SUPER_DISPATCHER_ASSIGN_SERVICE:
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
                case CLIENT_SIGN_ACT:

                    component.offersCounter.noComponent();
                    checkDetailsLastOrder(component, data);
                    component.noDesiredDate();
                    component.noDesiredTime();
                    //                   todo component.checkMasterVisitDateAndTime(data.getMasterVisitDateAndTime());
                    // todo  stepper
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);

            }

        });
    }


    public void checkCommonTab(UserRole role, CommonTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        step("Убедиться, что вкладка Описание заказа в состоянии " + this, () -> {
            StateBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
            tab.status.checkStateRepair(role, this, data, dto);
            tab.details.detailsRepair(this, data);
            tab.suggestedMasterRepair.checkState(this, dto, 0);
            tab.buttons.checkStateRepair(role, this);
            switch (this) {
                case PUBLISHED:
                    // todo stepper
                    break;
                case CANCEL_CLIENT_PUBLISHED:
                    // todo stepper
                    break;
                case HAS_SUPER_OFFER:
                case HAS_SERVICE_OFFER:
                    // todo stepper
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION:
                case SUPER_DISPATCHER_ASSIGN_SERVICE:
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


    public void checkInfoMasterTab(UserRole role, InfoMasterTabOrderCardClientComponent tab, OrdersIdResponseDto dto) {
        step("Убедиться, что вкладка Информация по работам в состоянии " + this, () -> {
            StateBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
            tab.status.checkStateRepair(role, this, data, dto);
            tab.suggestedMasterCardRepair.checkState(this, dto, 0);
            tab.approvedMasterCard.checkStateRepair(this, data);
            tab.buttons.checkStateRepair(role, this);
            switch (this) {
                case PUBLISHED:
                case HAS_SUPER_OFFER:
                case HAS_SERVICE_OFFER:
                    switch (role) {
                        case CLIENT:
                            tab.checkInfoNoExistNotification();
                            break;
                        case DISPATCHER:
                            tab.repairDetails.checkMaterialsPrice("0");
                            tab.repairDetails.checkActionsPrice("0");
                            break;
                        default:
                            throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
                    }
                    break;
                case CANCEL_CLIENT_PUBLISHED:
                case CANCEL_CLIENT_HAS_OFFER:
                case CANCEL_DISPATCHER_HAS_OFFER:
                    tab.repairDetails.checkMaterialsPrice("0");
                    tab.repairDetails.checkActionsPrice("0");
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION:
                case SUPER_DISPATCHER_ASSIGN_SERVICE:
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

    public void checkDocsTab(UserRole role, DocsTabOrderCardComponent tab, OrdersIdResponseDto dto) {
        StateBuilder.OrderIdData data = builder.extractOrdersIdData(dto);
        tab.status.checkStateRepair(role, this, data, dto);
        tab.suggestedMastersRepair.checkState(this, dto, 0);
        tab.buttons.checkStateRepair(role, this);
        step("Убедиться, что вкладка Документы в состоянии " + this, () -> {
            switch (this) {
                case PUBLISHED:
                case HAS_SUPER_OFFER:
                case HAS_SERVICE_OFFER:
                    tab.noDocs();
                    tab.noTotalPrice();
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION:
                case SUPER_DISPATCHER_ASSIGN_SERVICE:
                case WAIT_MASTER:
                case MASTER_START_WORK:
                    tab.noDocs();
                    tab.checkTotalPrice("9008.00");
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
            page.checkFinishLoadingRepair();
            page.countdownComponent.checkState(this, dto); //todo check focusMasterCrd
            page.countdownComponent.checkMasterCardState(this, dto); //todo check focusMasterCrd
            page.suggestedConsultationBanner.checkState(this);
            switch (this) {
                case PUBLISHED:
                    page.offersCounter.noOffers();
                    page.upperPublishedRepairInfoBox.checkPublishedState();
//                    page.countdown.checkPublishedActiveState();
//                    page.suggestedConsultationBannerComponent.checkFinishLoading();
//                    page.suggestedConsultationBannerComponent.checkOpened();
                    page.respondedCompaniesBox.noOffers();
                    break;
                case HAS_SUPER_OFFER:
                case HAS_SERVICE_OFFER:
//                    page.offersCounter.amount(page.respondedCompaniesBox.getAmountOfferBox());
//                    page.checkHasOfferBanner(); //deleted
//                    page.countdown.checkHasOfferState(dto);
//                    page.suggestedConsultationBannerComponent.checkFinishLoading();
//                    page.suggestedConsultationBannerComponent.checkClosed();
                    page.respondedCompaniesBox.checkOfferBoxHasOfferState(0, dto);
                    page.upperPublishedRepairInfoBox.noUpperInfoBox();

                    break;
                default:
                    page.notAvailable();

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
