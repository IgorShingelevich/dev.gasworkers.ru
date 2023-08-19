package ru.gasworkers.dev.tests.web.orderProcess.repair;

import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.orders.selectServiceCompany.SelectServiceCompanyApi;
import ru.gasworkers.dev.api.orders.selectServiceCompany.dto.SelectServiceCompanyResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersListResponse;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListApi;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListResponseDto;
import ru.gasworkers.dev.api.users.settings.UserSettingsApi;
import ru.gasworkers.dev.api.users.settings.UserSettingsCommonRequestDto;
import ru.gasworkers.dev.api.users.settings.UserSettingsCommonResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.CommonFieldsRepairDto;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class PreconditionRepair extends BaseApiTest {

    public final StateInfo stateInfo = new StateInfo();
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
    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";
    LastOrderInfoResponseDto publishedLastOrderInfo;
    OrdersIdResponseDto publishedOrderIdResponse;
    SuggestServicesResponseDto publishedSuggestedServiceResponse;
    LastOrderInfoResponseDto hasOfferLastOrderInfo;
    OrdersIdResponseDto hasOfferOrderIdClient;
    SuggestServicesResponseDto hasOfferSuggestedServiceResponse;

    LastOrderInfoResponseDto scheduleTimeLastOrderResponse;
    OrdersIdResponseDto scheduleTimeOrderIdResponseClient;

    public StateInfo applyPrecondition(User client, StateRepair stateRepair) {
        stateInfo.setCommonFields(commonFields);
        switch (stateRepair) {
            case PUBLISHED:
                applyPublishedPrecondition(client, commonFields);
                return stateInfo.publishedDtoSet();
            case HAS_OFFER:
                applyPublishedPrecondition(client, commonFields);
                applyHasOfferPrecondition(client, commonFields);
                return stateInfo.hasOfferDtoSet();
            case SCHEDULE_DATE:
                applyPublishedPrecondition(client, commonFields);
                applyHasOfferPrecondition(client, commonFields);
                applyScheduleDatePrecondition(client, commonFields);
                return stateInfo.scheduleDateDtoSet();
        }
        return null; // or throw an exception if the state is not handled
    }

    private void applyPublishedPrecondition(User client, CommonFieldsRepairDto commonFields) {
        step("API: предусловие - " + Role.CLIENT + " заказ на ремонт в состоянии " + StateRepair.PUBLISHED, () -> {
            commonFields.setTokenClient(loginApi.getTokenThrough(client));
            step(Role.CLIENT + " заказ на ремонт - в состоянии " + StateRepair.PUBLISHED, () -> {
                step(Role.CLIENT + " карточка последнего заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    System.out.println("publishedLastOrderInfo");
                    publishedLastOrderInfo = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    commonFields.setOrderId(publishedLastOrderInfo.getData().getId());
                    commonFields.setOrderNumber(publishedLastOrderInfo.getData().getNumber());
                    commonFields.setClientObjectId(publishedLastOrderInfo.getData().getClientObject().getId());
                    commonFields.setEquipments0Id(publishedLastOrderInfo.getData().getEquipments().get(0).getId());
                });
                step(Role.CLIENT + " карточка заказа - в состоянии " + StateRepair.PUBLISHED, () -> {
                    publishedOrderIdResponse = ordersIdApi.orderId(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(OrdersIdResponseDto.class);
                });
            });
            step(Role.CLIENT + " получает список доступных предложений", () -> {
                publishedSuggestedServiceResponse = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SuggestServicesResponseDto.class);
                List<SuggestServicesResponseDto.Service> services = publishedSuggestedServiceResponse.getData().getServices();
                // Filter companies with non-null prices
                List<SuggestServicesResponseDto.Service> filteredServices = new ArrayList<>();
                for (SuggestServicesResponseDto.Service c : services) {
                    if (c.getPrice() != null) {
                        filteredServices.add(c);
                    }
                }
            });
            stateInfo.setPublishedLastOrderInfo(publishedLastOrderInfo);
            stateInfo.setPublishedOrderIdResponse(publishedOrderIdResponse);
            stateInfo.setPublishedSuggestedServiceResponse(publishedSuggestedServiceResponse);
        });
    }

    private void applyHasOfferPrecondition(User client, CommonFieldsRepairDto commonFields) {
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

                step(Role.CLIENT + " карточка последнего заказа в состоянии " + StateRepair.HAS_OFFER, () -> {
                    System.out.println("hasOfferLastOrderInfo");
                    hasOfferLastOrderInfo = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(LastOrderInfoResponseDto.class);
                    commonFields.setOrderId(hasOfferLastOrderInfo.getData().getId());
                });
                step(Role.CLIENT + " карточка заказа в состоянии " + StateRepair.HAS_OFFER, () -> {
                    System.out.println("hasOfferOrdersId");
                    hasOfferOrderIdClient = ordersIdApi.orderId(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(OrdersIdResponseDto.class);
                    commonFields.setOfferIdHasOfferClient(hasOfferOrderIdClient.getData().getOffer().getId());
                });
                step(Role.CLIENT + " получает список доступных предложений", () -> {
                    hasOfferSuggestedServiceResponse = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                            .statusCode(200)
                            .extract().as(SuggestServicesResponseDto.class);
                    List<SuggestServicesResponseDto.Service> services = hasOfferSuggestedServiceResponse.getData().getServices();
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
                });
            });
            stateInfo.setHasOfferLastOrderInfo(hasOfferLastOrderInfo);
            stateInfo.setHasOfferSuggestedServiceResponse(hasOfferSuggestedServiceResponse);
            stateInfo.setHasOfferOrderIdClient(hasOfferOrderIdClient);
        });
    }

    private void applyScheduleDatePrecondition(User client, CommonFieldsRepairDto commonFields) {
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
            step("клиент оплачивает  выезд мастера", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
            });
            step("клиент карточка заказа - в  состоянии согласование даты и времени", () -> {
                System.out.println("scheduleTimeOrderIdResponseAsClient");
                scheduleTimeOrderIdResponseClient = ordersIdApi.orderId(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
            });
            step(" клиент карточка последнего заказа - в  состоянии согласование даты и времени", () -> {
                System.out.println("scheduleTimeLastOrderResponseAsClient");
                scheduleTimeLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
            });
            stateInfo.setScheduleDateLastOrderInfo(scheduleTimeLastOrderResponse);
            stateInfo.setScheduleDateOrderIdResponse(scheduleTimeOrderIdResponseClient);
        });
    }
}
