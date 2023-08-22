package ru.gasworkers.dev.tests.web.orderProcess.repair;

import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.orders.actions.OrdersActionsApi;
import ru.gasworkers.dev.api.orders.actions.OrdersSaveActionsApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateResponseDto;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListApi;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListResponseDto;
import ru.gasworkers.dev.api.orders.createAct.OrdersCreateActApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.materialValues.OrdersMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.OrdersSaveMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersMaterialValuesResponseDto;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersSaveMaterialValuesResponseDto;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.orders.selectServiceCompany.SelectServiceCompanyApi;
import ru.gasworkers.dev.api.orders.selectServiceCompany.dto.SelectServiceCompanyResponseDto;
import ru.gasworkers.dev.api.orders.sign.OrdersSendSignApi;
import ru.gasworkers.dev.api.orders.sign.OrdersSignApi;
import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersListResponse;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListApi;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListResponseDto;
import ru.gasworkers.dev.api.users.notification.NotificationsApi;
import ru.gasworkers.dev.api.users.notification.NotificationsResponseDto;
import ru.gasworkers.dev.api.users.profile.UserSettingsApi;
import ru.gasworkers.dev.api.users.profile.UserSettingsCommonRequestDto;
import ru.gasworkers.dev.api.users.profile.UserSettingsCommonResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsRepairDto;
import ru.gasworkers.dev.tests.api.story.repair.RepairTestCase;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionRepair extends BaseApiTest {

    public final StateInfo stateInfo = new StateInfo();
    private final RepairTestCase repairCase = new RepairTestCase();
    private final NotificationsApi notificationsApi = new NotificationsApi();
    private final UserSettingsApi userSettingsApi = new UserSettingsApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
    private final OrdersIdApi ordersIdApi = new OrdersIdApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
    private final FspBankListApi fspBankListApi = new FspBankListApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final OrdersApproveDateApi ordersApproveDateApi = new OrdersApproveDateApi();
    private final SaveCheckListApi saveCheckListApi = new SaveCheckListApi();
    private final OrdersMaterialValuesApi ordersMaterialValuesApi = new OrdersMaterialValuesApi();
    private final OrdersSaveMaterialValuesApi ordersSaveMaterialValuesApi = new OrdersSaveMaterialValuesApi();
    private final OrdersActionsApi ordersActionsApi = new OrdersActionsApi();
    private final OrdersSaveActionsApi ordersSaveActionsApi = new OrdersSaveActionsApi();
    private final OrdersCreateActApi ordersCreateActApi = new OrdersCreateActApi();
    private final OrdersSendSignApi ordersSendSignApi = new OrdersSendSignApi();
    private final OrdersSignApi ordersSignApi = new OrdersSignApi();
    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";
    private final String sssrMaster1Email = "test_gas_master_sssr1@rambler.ru";
    private final String sssrMaster1Password = "1234";

    public StateInfo applyPrecondition(User client, StateRepair stateRepair) {
        stateInfo.setCommonFields(commonFields);
        StateInfo result = null;
        switch (stateRepair) {
            case PUBLISHED:
                applyPublishedPrecondition(stateRepair, client, commonFields);
                result = stateInfo.publishedDtoSet();
                break;
            case HAS_OFFER:
                applyPublishedPrecondition(stateRepair, client, commonFields);
                applyHasOfferPrecondition(stateRepair, client, commonFields);
                result = stateInfo.hasOfferDtoSet();
                break;
            case SCHEDULE_DATE:
                applyPublishedPrecondition(stateRepair, client, commonFields);
                applyHasOfferPrecondition(stateRepair, client, commonFields);
                applyScheduleDatePrecondition(stateRepair, client, commonFields);
                result = stateInfo.scheduleDateDtoSet();
                break;
            case WAIT_MASTER:
                applyPublishedPrecondition(stateRepair, client, commonFields);
                applyHasOfferPrecondition(stateRepair, client, commonFields);
                applyScheduleDatePrecondition(stateRepair, client, commonFields);
                applyWaitMasterPreconditionUser(stateRepair, client, commonFields);
                result = stateInfo.waitMasterDtoSet();
                break;
            case MASTER_START_WORK:
                applyPublishedPrecondition(stateRepair, client, commonFields);
                applyHasOfferPrecondition(stateRepair, client, commonFields);
                applyScheduleDatePrecondition(stateRepair, client, commonFields);
                applyWaitMasterPreconditionUser(stateRepair, client, commonFields);
                applyMasterStartWorkPrecondition(stateRepair, client, commonFields);
                result = stateInfo.masterStartWorkDtoSet();
                break;
            case MATERIAL_INVOICE_ISSUED:
                applyPublishedPrecondition(stateRepair, client, commonFields);
                applyHasOfferPrecondition(stateRepair, client, commonFields);
                applyScheduleDatePrecondition(stateRepair, client, commonFields);
                applyWaitMasterPreconditionUser(stateRepair, client, commonFields);
                applyMasterStartWorkPrecondition(stateRepair, client, commonFields);
                applyMaterialInvoiceIssuedPrecondition(stateRepair, client, commonFields);
                result = stateInfo.materialInvoiceIssuedDtoSet();
                break;
            case MATERIAL_INVOICE_PAID:
                applyPublishedPrecondition(stateRepair, client, commonFields);
                applyHasOfferPrecondition(stateRepair, client, commonFields);
                applyScheduleDatePrecondition(stateRepair, client, commonFields);
                applyWaitMasterPreconditionUser(stateRepair, client, commonFields);
                applyMasterStartWorkPrecondition(stateRepair, client, commonFields);
                applyMaterialInvoiceIssuedPrecondition(stateRepair, client, commonFields);
                applyMaterialInvoicePaidPrecondition(stateRepair, client, commonFields);
                result = stateInfo.materialInvoicePaidDtoSet();
                break;
            default:
                throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
        }
        readAllNotifications();
        return result;
    }


    private void readAllNotifications() {
        notificationsApi.readAllNotifications(commonFields.getTokenClient())
                .statusCode(200);
    }

    private void applyPublishedPrecondition(StateRepair stateRepair, User client, CommonFieldsRepairDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.PUBLISHED, () -> {
            commonFields.setTokenClient(loginApi.getTokenThrough(client));
            step(Role.CLIENT + " заказ на ремонт - в состоянии " + StateRepair.PUBLISHED, () -> {
                step(Role.CLIENT + " карточка последнего заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    System.out.println("publishedLastOrderInfo");
                    LastOrderInfoResponseDto lastOrderDto = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    commonFields.setOrderId(lastOrderDto.getData().getId());
                    commonFields.setOrderNumber(lastOrderDto.getData().getNumber());
                    commonFields.setClientObjectId(lastOrderDto.getData().getClientObject().getId());
                    commonFields.setEquipments0Id(lastOrderDto.getData().getEquipments().get(0).getId());
                    stateInfo.setPublishedLastOrderInfo(lastOrderDto);
                });
                stateInfo.setPublishedOrderIdResponse(getOrdersId(commonFields, stateRepair));
                stateInfo.setPublishedNotifications(getNotifications(commonFields, stateRepair));
            });
            step(Role.CLIENT + " получает список доступных предложений", () -> {
                SuggestServicesResponseDto suggestServiceDto = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SuggestServicesResponseDto.class);
                List<SuggestServicesResponseDto.Service> services = suggestServiceDto.getData().getServices();
                // Filter companies with non-null prices
                List<SuggestServicesResponseDto.Service> filteredServices = new ArrayList<>();
                for (SuggestServicesResponseDto.Service c : services) {
                    if (c.getPrice() != null) {
                        filteredServices.add(c);
                    }
                }
                stateInfo.setPublishedSuggestedServiceResponse(suggestServiceDto);
            });
        });
    }

    private void applyHasOfferPrecondition(StateRepair stateRepair, User client, CommonFieldsRepairDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.HAS_OFFER, () -> {
            step(Role.CLIENT + " заполнение профиля после Фоновой Регистрации", () -> {
                UserSettingsCommonResponseDto actualResponse = userSettingsApi.setCommon(UserSettingsCommonRequestDto.defaultBGClientRequest(client), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(UserSettingsCommonResponseDto.class);
                commonFields.setPassportId(actualResponse.getData().getPassport().getId());
                commonFields.setClientId(actualResponse.getData().getId());
                UserSettingsCommonResponseDto expectedResponse = UserSettingsCommonResponseDto.defaultBGUserResponse(client, commonFields);
                expectedResponse.getData().getGuides().get(0).setId(actualResponse.getData().getGuides().get(0).getId());
                expectedResponse.getData().getPassport().setIssuedDate(actualResponse.getData().getPassport().getIssuedDate());
                //todo assert клиент - модель пользователя в  состоянии после заполнения профиля
                // todo change all  dto to include  user details
            });
            step(Role.DISPATCHER + " выбирает мастера ", () -> {
                step(Role.DISPATCHER + " авторизуется", () -> {
                    commonFields.setTokenDispatcher(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password)));
                });
                step(Role.DISPATCHER + " получает список доступных мастеров ", () -> {
                    commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                            .statusCode(200)
                            .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId());
                });
                step(Role.DISPATCHER + " подтверждает выбор первого мастера ", () -> {
                    selectMasterApi.selectMaster(commonFields.getOrderId(), commonFields.getMasterId(), commonFields.getTokenDispatcher())
                            .statusCode(200);
                });
            });
            step("Получение учетных данных  выбранного мастера за  роль администратора", () -> {
                String tokenAdmin = loginApi.login(LoginRequestDto.asAdmin())
                        .statusCode(200)
                        .extract().as(LoginResponseDto.class).getData().getToken();
                System.out.println("getUserWithAdmin: " + commonFields.getMasterId());
                UserResponseDto masterDto = getUserWithAdminApi.getUserWithAdmin(tokenAdmin, commonFields.getMasterId())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class);
                commonFields.setMasterEmail(masterDto.getData().getEmail());
            });
            step(Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.HAS_OFFER, () -> {
                step(Role.CLIENT + " получает список доступных предложений", () -> {
                    SuggestServicesResponseDto suggestedServiceDto = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(SuggestServicesResponseDto.class);
                    List<SuggestServicesResponseDto.Service> services = suggestedServiceDto.getData().getServices();
                    // Filter companies with non-null prices
                    List<SuggestServicesResponseDto.Service> filteredServices = new ArrayList<>();
                    for (SuggestServicesResponseDto.Service c : services) {
                        if (c.getPrice() != null) {
                            filteredServices.add(c);
                        }
                    }
                    assertThat(filteredServices).isNotEmpty();
                    commonFields.setServiceId(filteredServices.get(0).getId());
                    commonFields.setPossibleOfferId(filteredServices.get(0).getOfferId());
                    stateInfo.setHasOfferSuggestedServiceResponse(suggestedServiceDto);

                });
            });
            stateInfo.setHasOfferLastOrderInfo(getLastOrderInfo(commonFields, stateRepair));
            stateInfo.setHasOfferOrderIdClient(getOrdersId(commonFields, stateRepair));
            stateInfo.setHasOfferNotifications(getNotifications(commonFields, stateRepair));
        });
    }

    private void applyScheduleDatePrecondition(StateRepair stateRepair, User client, CommonFieldsRepairDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.SCHEDULE_DATE, () -> {
            step("клиент выбирает предложение", () -> {
                SelectServiceCompanyResponseDto actualResponse = selectServiceCompanyApi.selectServiceCompany(commonFields.getOrderId(), commonFields.getServiceId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectServiceCompanyResponseDto.class);
                commonFields.setReceipts0Id(actualResponse.getData().getReceiptId());
                SelectServiceCompanyResponseDto expectedResponse = SelectServiceCompanyResponseDto.successResponse(commonFields.getReceipts0Id()).build();
            });
            step("клиент получает список банков на оплату", () -> {
                FspBankListResponseDto actualResponse = fspBankListApi.fspBankList(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(FspBankListResponseDto.class);
                Integer availableBanks = actualResponse.getData().size();
                System.out.println("availableBanks = " + availableBanks);
            });
            step("клиент оплачивает  активацию сделки", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
            });
            stateInfo.setScheduleDateLastOrderInfo(getLastOrderInfo(commonFields, stateRepair));
            stateInfo.setScheduleDateOrderIdResponse(getOrdersId(commonFields, stateRepair));
            stateInfo.setScheduleDateNotifications(getNotifications(commonFields, stateRepair));
        });
    }

    private void applyWaitMasterPreconditionUser(StateRepair stateRepair, User client, CommonFieldsRepairDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.WAIT_MASTER, () -> {
            step(Role.DISPATCHER + " подтверждает дату и время в состоянии" + StateRepair.WAIT_MASTER, () -> {
                commonFields.setApproveDate(client.getApproveDate());
                OrdersApproveDateResponseDto actualResponse = ordersApproveDateApi.ordersApproveDate(repairCase.approveDateRequest(commonFields), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersApproveDateResponseDto.class);
            });
            stateInfo.setWaitMasterLastOrderInfo(getLastOrderInfo(commonFields, stateRepair));
            stateInfo.setWaitMasterOrderIdResponse(getOrdersId(commonFields, stateRepair));
            stateInfo.setWaitMasterNotifications(getNotifications(commonFields, stateRepair));
        });
    }

    private void applyMasterStartWorkPrecondition(StateRepair stateRepair, User client, CommonFieldsRepairDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.MASTER_START_WORK, () -> {
            step(Role.MASTER + "  авторизуется", () -> {
                commonFields.setTokenMaster(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrMaster1Email, sssrMaster1Password)));
            });
            step(Role.MASTER + " сохраняет чеклист", () -> {
                SaveCheckListResponseDto actualResponse = saveCheckListApi.saveCheckList(repairCase.saveCheckListRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(SaveCheckListResponseDto.class);
                SaveCheckListResponseDto expectedResponse = SaveCheckListResponseDto.successResponse();
                assertResponse(expectedResponse, actualResponse);
            });
            stateInfo.setMasterStartWorkLastOrderInfo(getLastOrderInfo(commonFields, stateRepair));
            stateInfo.setMasterStartWorkOrderIdResponse(getOrdersId(commonFields, stateRepair));
            stateInfo.setMasterStartWorkNotifications(getNotifications(commonFields, stateRepair));
        });
    }

    private void applyMaterialInvoiceIssuedPrecondition(StateRepair stateRepair, User client, CommonFieldsRepairDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.MATERIAL_INVOICE_ISSUED, () -> {
            step(Role.MASTER + " создает таблицу материалов - в состоянии " + StateRepair.MATERIAL_INVOICE_ISSUED, () -> {
                OrdersMaterialValuesResponseDto actualResponse = ordersMaterialValuesApi.materialValuesTable(repairCase.materialValuesRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersMaterialValuesResponseDto.class);
                OrdersMaterialValuesResponseDto expectedResponse = OrdersMaterialValuesResponseDto.successResponse();
                assertResponse(expectedResponse, actualResponse);
            });
            step(Role.MASTER + " сохраняет таблицу материалов - в состоянии " + StateRepair.MATERIAL_INVOICE_ISSUED, () -> {
                OrdersSaveMaterialValuesResponseDto actualResponse = ordersSaveMaterialValuesApi.saveMaterialValues(repairCase.saveMaterialValuesRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersSaveMaterialValuesResponseDto.class);
                OrdersSaveMaterialValuesResponseDto expectedResponse = OrdersSaveMaterialValuesResponseDto.oneMaterialResponse();
                assertResponse(expectedResponse, actualResponse);
            });
            stateInfo.setMaterialInvoiceIssuedLastOrderInfo(getLastOrderInfo(commonFields, stateRepair));
            stateInfo.setMaterialInvoiceIssuedOrderIdResponse(getOrdersId(commonFields, stateRepair));
            stateInfo.setMaterialInvoiceIssuedNotifications(getNotifications(commonFields, stateRepair));
        });
    }

    private void applyMaterialInvoicePaidPrecondition(StateRepair stateRepair, User client, CommonFieldsRepairDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.MATERIAL_INVOICE_PAID, () -> {
            step(Role.CLIENT + " оплачивает счет на Материалы - в состоянии " + StateRepair.MATERIAL_INVOICE_PAID, () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
                stateInfo.setMaterialInvoicePaidLastOrderInfo(getLastOrderInfo(commonFields, stateRepair));
                stateInfo.setMaterialInvoicePaidOrderIdResponse(getOrdersId(commonFields, stateRepair));
                stateInfo.setMaterialInvoicePaidNotifications(getNotifications(commonFields, stateRepair));
            });
        });
    }

    private LastOrderInfoResponseDto getLastOrderInfo(CommonFieldsRepairDto commonFields, StateRepair state) {
        System.out.println(state + ": LastOrderInfo");
        return step("API: " + Role.CLIENT + " карточка последнего заказа - в состоянии " + state, () -> {
            return lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(LastOrderInfoResponseDto.class);
        });
    }

    private OrdersIdResponseDto getOrdersId(CommonFieldsRepairDto commonFields, StateRepair state) {
        System.out.println(state + ": OrdersId");
        return step("API: " + Role.CLIENT + " карточка заказа - в состоянии " + state, () -> {
            return ordersIdApi.orderId(commonFields.getOrderId(), commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(OrdersIdResponseDto.class);
        });
    }

    private NotificationsResponseDto getNotifications(CommonFieldsRepairDto commonFields, StateRepair state) {
        System.out.println(state + ": Notifications");
        return step("API: " + Role.CLIENT + " уведомления - в состоянии " + state, () -> {
            return notificationsApi.getNotifications(commonFields.getTokenClient())
                    .statusCode(200)
                    .extract().as(NotificationsResponseDto.class);
        });
    }

}
