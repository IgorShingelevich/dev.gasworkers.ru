package ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.administration.totalPrice.TotalPriceResponseDto;
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
    PUBLISHED("Новый заказ", " опубликован"),
    PUBLISHED_STOPPED_COUNTDOWN("Новый заказ", " опубликован"), //  select service  page state
    REFUSED_OFFER_DISPATCHER("Диспетчер отклонил новый тендер", null),
    HAS_SERVICE_OFFER("Новый заказ", "Отклик на заявку"),
    HAS_SUPER_OFFER_SD_PROCESS("Новый заказ", "Отклик на заявку"),
    CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS("Согласование даты заказа", "Отклик на заявку"),  // yellow state, activation is paid, assigned superMaster, client wait SuperDispatcher to assign the actual Service
    SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS("Согласование даты заказа", "Отклик на заявку"), // assignedService
    SERVICE_SCHEDULED_MASTER_SD_PROCESS("Согласование даты заказа", "В заказе произошли следующие изменения"), // assignedServiceMaster and Prices
    WAIT_SERVICE_MASTER_SD_PROCESS("Мастер в пути", "Согласованы дата и время заказа"), //assignedTime
    MASTER_START_WORK("Мастер приступил к работе", null),
    MATERIAL_INVOICE_ISSUED("Мастер приступил к работе", "Оплатите счет по заказу"),
    MATERIAL_INVOICE_PAID("Мастер приступил к работе", null),
    ACTIONS_INVOICE_ISSUED("Мастер приступил к работе", "Оплатите счет по заказу"),
    ACTIONS_INVOICE_PAID("Завершен", "Оставьте отзыв по заявке"), //order is finished
    MASTER_SIGN_ACT("Мастер заполнил данные по заказу", "Акт выполненных работ сформирован"), //obsolete
    CLIENT_SIGN_ACT("Клиент подписал акт", "Оставьте отзыв по заявке"), //obsolete
    CANCEL_CLIENT_PUBLISHED("Заказ отменен", null),
    CANCEL_CLIENT_HAS_OFFER("Заказ отменен", "Заказ отменен"),
    CANCEL_DISPATCHER_HAS_OFFER("Заказ отменен", "Заказ отменен"),
    CANCELED("Заказ отменен", "Заказ отменен");

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
                case HAS_SUPER_OFFER_SD_PROCESS:
                case HAS_SERVICE_OFFER:
                    component.offersCounter.amount(data.getOffersCount());
                    checkDetailsLastOrder(component, data);
                    component.noMasterVisitDateAndTime();
//                 todo   component.checkDesiredTime(data.getDesiredTime());
                    // todo stepper
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                    component.offersCounter.noComponent();
                    checkDetailsLastOrder(component, data);
                    //                 todo   component.checkDesiredTime(data.getDesiredTime());
                    // todo stepper
                    break;
                case WAIT_SERVICE_MASTER_SD_PROCESS:
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
                case HAS_SUPER_OFFER_SD_PROCESS:
                case HAS_SERVICE_OFFER:
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                    // todo stepper
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                    // todo stepper
                    break;
                case WAIT_SERVICE_MASTER_SD_PROCESS:
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
                case HAS_SUPER_OFFER_SD_PROCESS:
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
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                case WAIT_SERVICE_MASTER_SD_PROCESS:
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

    public void checkDocsTab(UserRole role, DocsTabOrderCardComponent tab, OrdersIdResponseDto ordersDto, TotalPriceResponseDto totalPriceDto) {
        StateBuilder.OrderIdData data = builder.extractOrdersIdData(ordersDto);
        tab.status.checkStateRepair(role, this, data, ordersDto);
        tab.suggestedMastersRepair.checkState(this, ordersDto, 0);
        tab.buttons.checkStateRepair(role, this);
        step("Убедиться, что вкладка Документы в состоянии " + this, () -> {
            switch (this) {
                case PUBLISHED:
                case HAS_SUPER_OFFER_SD_PROCESS:
                case HAS_SERVICE_OFFER:
                    tab.noDocs();
                    tab.noTotalPrice();
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                    tab.noDocs();
                    tab.checkTotalPrice("9008.00");
                    // todo add tab.checkComputedToTalPrice - no field in json
                    break;
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                case WAIT_SERVICE_MASTER_SD_PROCESS:
                case MASTER_START_WORK:
                    tab.noDocs();
                    tab.checkTotalPrice("13008.00");
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
                    tab.checkTotalPrice("16298");
//                    tab.checkTotalPrice(totalPriceDto.getData().getTotal().toString());
                    // todo add tab.checkComputedToTalPrice - no field in json
                    break;
                case MASTER_SIGN_ACT:
                case CLIENT_SIGN_ACT:
                    tab.checkActDoc();
                    tab.checkTotalPrice("16298");
                    // todo add tab.checkComputedToTalPrice - no field in json
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }

    public void checkSelectServicePage1(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
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
                case HAS_SUPER_OFFER_SD_PROCESS:
                case HAS_SERVICE_OFFER:
//                    page.offersCounter.amount(page.respondedCompaniesBox.getAmountOfferBox());
//                    page.checkHasOfferBanner(); //deleted
//                    page.countdown.checkHasOfferState(dto);
//                    page.suggestedConsultationBannerComponent.checkFinishLoading();
//                    page.suggestedConsultationBannerComponent.checkClosed();
                    page.respondedCompaniesBox.checkOfferBoxHasOfferState(0, dto);
                    page.upperPublishedRepairInfoBox.noUpperInfoBox();
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                case WAIT_SERVICE_MASTER_SD_PROCESS:
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_ISSUED:
                case MATERIAL_INVOICE_PAID:
                case ACTIONS_INVOICE_ISSUED:
                case ACTIONS_INVOICE_PAID:
                    page.checkNotAvailable();
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }


    public void checkSelectServicePage(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
        step("Убедиться, что  стр выбора  компании в состоянии " + this, () -> {

            // Check for prioritized cases first
            if (this == CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS ||
                    this == SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS ||
                    this == SERVICE_SCHEDULED_MASTER_SD_PROCESS ||
                    this == WAIT_SERVICE_MASTER_SD_PROCESS ||
                    this == MASTER_START_WORK ||
                    this == MATERIAL_INVOICE_ISSUED ||
                    this == MATERIAL_INVOICE_PAID ||
                    this == ACTIONS_INVOICE_ISSUED ||
                    this == ACTIONS_INVOICE_PAID) {
                page.checkNotAvailable();
                return;
            }

            page.checkFinishLoadingRepair();
            page.countdownComponent.checkState(this, dto);
            page.countdownComponent.checkMasterCardState(this, dto);
            page.suggestedConsultationBanner.checkState(this);

            switch (this) {
                case PUBLISHED:
                    page.offersCounter.noOffers();
                    page.upperPublishedRepairInfoBox.checkPublishedState();
                    page.respondedCompaniesBox.noOffers();
                    break;
                case HAS_SUPER_OFFER_SD_PROCESS:
                case HAS_SERVICE_OFFER:
                    page.respondedCompaniesBox.checkOfferBoxHasOfferState(0, dto);
                    page.upperPublishedRepairInfoBox.noUpperInfoBox();
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
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
