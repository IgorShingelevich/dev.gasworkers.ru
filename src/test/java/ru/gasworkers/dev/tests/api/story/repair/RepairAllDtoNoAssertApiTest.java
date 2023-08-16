package ru.gasworkers.dev.tests.api.story.repair;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.user.UserApi;
import ru.gasworkers.dev.api.auth.user.UserResponseDto;
import ru.gasworkers.dev.api.orders.actions.OrdersActionsApi;
import ru.gasworkers.dev.api.orders.actions.OrdersSaveActionsApi;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersActionsResponseDto;
import ru.gasworkers.dev.api.orders.actions.dto.OrdersSaveActionsResponseDto;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateResponseDto;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListApi;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListResponseDto;
import ru.gasworkers.dev.api.orders.createAct.OrdersCreateActApi;
import ru.gasworkers.dev.api.orders.createAct.OrdersCreateActResponseDto;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.orders.materialValues.OrdersMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.OrdersSaveMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersMaterialValuesResponseDto;
import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersSaveMaterialValuesResponseDto;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.orders.selectServiceCompany.SelectServiceCompanyApi;
import ru.gasworkers.dev.api.orders.selectServiceCompany.dto.SelectServiceCompanyResponseDto;
import ru.gasworkers.dev.api.orders.sign.OrdersSendSignApi;
import ru.gasworkers.dev.api.orders.sign.OrdersSignApi;
import ru.gasworkers.dev.api.orders.sign.dto.OrdersSendSignResponseDto;
import ru.gasworkers.dev.api.orders.sign.dto.OrdersSignResponseDto;
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
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("repair all dto no assert")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.API)
public class RepairAllDtoNoAssertApiTest extends BaseApiTest {
    private final UserApi userApi = new UserApi();
    private final UserSettingsApi userSettingsApi = new UserSettingsApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
    private final FspBankListApi fspBankListApi = new FspBankListApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final OrdersIdApi ordersIdApi = new OrdersIdApi();
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
    private final String sssrMaster1Email = "test_gas_master_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";
    private final String sssrMaster1Password = "1234";
    private final RepairTestCase repairCase = new RepairTestCase();
    private UserResponseDto actualPublishedUserResponse;

    private LastOrderInfoResponseDto actualPublishedLastOrderResponse;
    private OrdersInfoResponseDto actualPublishedOrderInfoResponse;
    private OrdersIdResponseDto actualPublishedOrderIdAsDispatcherResponse;

    private LastOrderInfoResponseDto actualHasOfferLastOrderResponse;
    private OrdersInfoResponseDto actualHasOfferOrderInfoResponse;
    private OrdersIdResponseDto actualHasOfferOrderIdResponse;
    private OrdersIdResponseDto expectedHasOfferOrderIdResponse;

    private OrdersInfoResponseDto actualBeforePaymentOrderInfoResponse;
    private LastOrderInfoResponseDto actualBeforePaymentLastOrderResponse;
    private OrdersIdResponseDto actualBeforePaymentOrderIdResponse;

    private OrdersInfoResponseDto actualDateSelectingOrderInfoResponse;
    private LastOrderInfoResponseDto actualDateSelectingLastOrderResponse;
    private OrdersIdResponseDto expectedDateSelectingLastOrderResponse;
    private OrdersIdResponseDto actualDateSelectingOrderIdResponse;

    private LastOrderInfoResponseDto actualWaitMasterLastOrderResponse;
    private OrdersIdResponseDto actualWaitMasterAsMasterOrderIdResponse;
    private OrdersIdResponseDto expectedWaitMasterAsMasterOrderIdResponse;
    private OrdersIdResponseDto actualWaitMasterAsDispatcherOrderIdResponse;
    private OrdersIdResponseDto expectedWaitMasterAsDispatcherOrderIdResponse;

    private LastOrderInfoResponseDto actualMasterStartWorkingLastOrderResponse;
    private OrdersIdResponseDto actualMasterStartWorkingAsMasterOrderIdResponse;
    private OrdersIdResponseDto expectedMasterStartWorkingAsMasterOrderIdResponse;

    private OrdersIdResponseDto materialInvoiceIssuedOrderIdResponseAsMaster;
    private LastOrderInfoResponseDto materialInvoiceIssuedLastOrderResponse;

    private LastOrderInfoResponseDto materialInvoicePaidLastOrderResponse;
    private OrdersIdResponseDto materialInvoicePaidOrderIdResponseAsMaster;

    private LastOrderInfoResponseDto actionsInvoiceIssuedLastOrderResponse;
    private OrdersIdResponseDto actionsInvoiceIssuedOrderIdResponseAsMaster;
    private OrdersIdResponseDto actionsInvoiceIssuedOrderIdResponseAsClient;

    private LastOrderInfoResponseDto actionsInvoicePaidLastOrderResponse;
    private OrdersIdResponseDto actionsInvoicePaidOrderIdResponseAsClient;
    private OrdersIdResponseDto actionsInvoicePaidOrderIdResponseAsMaster;

    private LastOrderInfoResponseDto actSignedMasterLastOrderResponse;
    private OrdersIdResponseDto actSignedMasterOrderIdResponseAsClient;
    private OrdersIdResponseDto actSignedMasterOrderIdResponseAsMaster;

    private LastOrderInfoResponseDto actSignedClientLastOrderResponse;
    private OrdersIdResponseDto actSignedClientOrderIdResponseAsMaster;
    private OrdersIdResponseDto actSignedClientOrderIdResponseAsClient;


    @Test
    @DisplayName("all dto repair")
    void allDtoRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        commonFields.setTokenClient(loginApi.getTokenThrough(client));

        step("клиент - заказ на ремонт клиента - в состоянии published", () -> {
            step("клиент - модель пользователя - в состоянии published ", () -> {
                actualPublishedUserResponse = userApi.getUser(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class);
                commonFields.setClientId(actualPublishedUserResponse.getData().getId());
                commonFields.setClientEmail(actualPublishedUserResponse.getData().getEmail());
                commonFields.setClientGuides0Id(actualPublishedUserResponse.getData().getGuides().get(0).getId());
                commonFields.setClientPhone(actualPublishedUserResponse.getData().getPhone());
                commonFields.setClientNotificationsCount(actualPublishedUserResponse.getData().getUserNotificationsCount());
                UserResponseDto expectedResponse = repairCase.publishedClient(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualPublishedUserResponse);
            });
            step("клиент заполнение профиля после Фоновой Регистрации", () -> {
                UserSettingsCommonResponseDto actualResponse = userSettingsApi.setCommon(UserSettingsCommonRequestDto.defaultBGClientRequest(client), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(UserSettingsCommonResponseDto.class);
                commonFields.setPassportId(actualResponse.getData().getPassport().getId());
                commonFields.setClientId(actualResponse.getData().getId());
                UserSettingsCommonResponseDto expectedResponse = UserSettingsCommonResponseDto.defaultBGUserResponse(client, commonFields);
                expectedResponse.getData().getGuides().get(0).setId(actualResponse.getData().getGuides().get(0).getId());
                expectedResponse.getData().getPassport().setIssuedDate(actualResponse.getData().getPassport().getIssuedDate());
//                assertResponsePartialNoATExcludeFields(expectedResponse, actualResponse, List.of("data.userNotificationsCount"));
                //todo assert клиент - модель пользователя в  состоянии после заполнения профиля
                // todo change all  dto to include  user details
            });
            step("клиент карточка последнего заказа - убедиться что в  состоянии published", () -> {
                actualPublishedLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                commonFields.setOrderId(actualPublishedLastOrderResponse.getData().getId());
                commonFields.setOrderNumber(actualPublishedLastOrderResponse.getData().getNumber());
                commonFields.setClientObjectId(actualPublishedLastOrderResponse.getData().getClientObject().getId());
                commonFields.setEquipments0Id(actualPublishedLastOrderResponse.getData().getEquipments().get(0).getId());
                LastOrderInfoResponseDto expectedResponse = repairCase.publishedLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualPublishedLastOrderResponse);
            });
            step(" клиент карточка заказа - убедиться что в  состоянии published", () -> {
                actualPublishedOrderInfoResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                OrdersInfoResponseDto expectedResponse = repairCase.publishedOrderInfoBGRepair(commonFields);
                assertResponsePartialNoATExcludeFields(expectedResponse, actualPublishedOrderInfoResponse, List.of("data.offer"));
            });
        });

        step("диспетчер выбирает мастера ", () -> {
            step("диспетчер авторизуется", () -> {
                commonFields.setTokenDispatcher(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password)));
            });
            step("диспетчер карточка заказа - убедиться что в состоянии published", () -> {
                System.out.println("publishedOrderIdAsDispatcherResponse");
                actualPublishedOrderIdAsDispatcherResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setClientRefererCode(actualPublishedOrderIdAsDispatcherResponse.getData().getClient().getRefererCode());
                commonFields.setClientRoles0PivotModelId(actualPublishedOrderIdAsDispatcherResponse.getData().getClient().getRoles().get(0).getPivot().getModelId());
                commonFields.setOfferIdPublishedByIdDispatcher(actualPublishedOrderIdAsDispatcherResponse.getData().getOffers().get(0).getId());
                OrdersIdResponseDto expectedResponse = repairCase.publishedOrderIdAsDispatcherBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualPublishedOrderIdAsDispatcherResponse);
            });
            step("диспетчер получает список доступных мастеров ", () -> {
                commonFields.setMasterId(companiesMastersApi.getAcceptedMasters(commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId());
            });
            step("диспетчер подтверждает выбор первого мастера ", () -> {
                SelectMasterResponseDto actualSelectMasterResponse = selectMasterApi.selectMaster(commonFields.getOrderId(), commonFields.getMasterId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(SelectMasterResponseDto.class);
                //todo add dtoassert
//                assertThat(actualSelectMasterResponse.getData().getReceiptId()).isNull();
            });
        });

        step("заказ на ремонт в состоянии hasOffer", () -> {
            step(" клиент карточка последнего заказа - убедиться что в состоянии hasOffer", () -> {
                actualHasOfferLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
//                activeOffersCount = actualHasOfferLastOrderResponse.getData().getClientObject().getActiveOffersCount();
                commonFields.setActiveOffersCount(actualHasOfferLastOrderResponse.getData().getClientObject().getActiveOffersCount());
                LastOrderInfoResponseDto expectedResponse = repairCase.hasOfferLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualHasOfferLastOrderResponse);
            });
            step("клиент карточка заказа - убедиться что в состоянии hasOffer", () -> {
                actualHasOfferOrderInfoResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
//                offerIdHasOffer = actualHasOfferOrderInfoResponse.getData().getOffer().getId();
                commonFields.setOfferIdHasOfferClient(actualHasOfferOrderInfoResponse.getData().getOffer().getId());
                OrdersInfoResponseDto expectedResponse = repairCase.hasOfferOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualHasOfferOrderInfoResponse);
            });
            step("диспетчер карточка заказа - убедиться что в состоянии hasOffer", () -> {
                System.out.println("actualHasOfferOrderIdResponseAsDispatcher");
                actualHasOfferOrderIdResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setMasters0CompletedOrdersCount(actualHasOfferOrderIdResponse.data.getMasters().get(0).getCompletedOrdersCount());
                expectedHasOfferOrderIdResponse = repairCase.hasOfferOrderIdBGRepairAsDispatcher(commonFields);
//                assertResponsePartialNoAt(expectedHasOfferOrderIdResponse, actualHasOfferOrderIdResponse);
//                assertResponsePartialNoATExcludeFields(expectedHasOfferOrderIdResponse, actualHasOfferOrderIdResponse, List.of("data.client.countNotReadNotification"));  // vary 1 or 2 cannot determine
            });
            step("клиент получает список доступных предложений", () -> {
                SuggestServicesResponseDto suggestedServiceResponse = suggestedServicesApi.suggestServices(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SuggestServicesResponseDto.class);
                List<SuggestServicesResponseDto.Service> services = suggestedServiceResponse.getData().getServices();
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
            step("клиент выбирает предложение", () -> {
                SelectServiceCompanyResponseDto actualResponse = selectServiceCompanyApi.selectServiceCompany(commonFields.getOrderId(), commonFields.getServiceId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectServiceCompanyResponseDto.class);
//                receiptId = actualResponse.getData().getReceiptId();
                commonFields.setReceipts0Id(actualResponse.getData().getReceiptId());
                SelectServiceCompanyResponseDto expectedResponse = SelectServiceCompanyResponseDto.successResponse(commonFields.getReceipts0Id()).build();
//                assertThat(actualResponse).isEqualTo(expectedResponse);
            });
            step("клиент получает список банков на оплату", () -> {
                FspBankListResponseDto actualResponse = fspBankListApi.fspBankList(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(FspBankListResponseDto.class);
                Integer availableBanks = actualResponse.getData().size();
                System.out.println("availableBanks = " + availableBanks);
                //check  amount of banks
            });
            step("клиент карточка заказа - убедиться что перед оплатой", () -> {
                System.out.println("beforePaymentOrderInfoResponseAsClient");
                actualBeforePaymentOrderInfoResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
//                Integer offerIdBeforePayment = actualBeforePaymentOrderInfoResponse.getData().getOffer().getId();
                commonFields.setOfferIdBeforePayment(actualBeforePaymentOrderInfoResponse.getData().getOffer().getId());
                OrdersInfoResponseDto expectedResponse = repairCase.beforePaymentOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualBeforePaymentOrderInfoResponse);
            });
            step(" клиент карточка последнего заказа - убедиться что перед оплатой", () -> {
                System.out.println("beforePaymentLastOrderResponse");
                actualBeforePaymentLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.beforePaymentLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualBeforePaymentLastOrderResponse);
            });

        });

        step("заказ на ремонт в  состоянии Согласование даты и времени", () -> {
            step("клиент оплачивает  выезд мастера", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
            });
            step("клиент карточка заказа - убедиться что Согласование даты и времени", () -> {
                System.out.println("dateSelectingOrderInfoResponseAsClient");
                actualDateSelectingOrderInfoResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                OrdersInfoResponseDto expectedResponse = repairCase.dateSelectingOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualDateSelectingOrderInfoResponse);
            });
            step(" клиент карточка последнего заказа - убедиться что Согласование даты и времени", () -> {
                actualDateSelectingLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.dateSelectingLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualDateSelectingLastOrderResponse);
            });
            step("диспетчер карточка заказа - убедиться что Согласование даты и времени", () -> {
                System.out.println("dateSelectingOrderIdResponseAsDispatcher");
                commonFields.setApproveDate(client.getApproveDate());
                actualDateSelectingOrderIdResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                expectedDateSelectingLastOrderResponse = repairCase.dateSelectingOrderIdBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedDateSelectingLastOrderResponse, actualDateSelectingOrderIdResponse);  // falls
            });
        });

        step("заказ на ремонт в  состоянии wait-for-master-starting", () -> {
            step("диспетчер подтверждает дату и время", () -> {
                commonFields.setApproveDate(client.getApproveDate());
                OrdersApproveDateResponseDto actualResponse = ordersApproveDateApi.ordersApproveDate(repairCase.approveDateRequest(commonFields), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersApproveDateResponseDto.class);
                OrdersApproveDateResponseDto expectedResponse = OrdersApproveDateResponseDto.successResponse();
//                assertResponse(expectedResponse, actualResponse);
            });
            step("диспетчер карточка заказа - убедиться что wait-for-master-starting", () -> {
                System.out.println("waitMasterOrderIdResponseAsDispatcher");
                actualWaitMasterAsDispatcherOrderIdResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setSelectedDate(client.getSelectedDate());
                expectedWaitMasterAsDispatcherOrderIdResponse = repairCase.waitMasterOrderIdAsDispatcherBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedWaitMasterAsDispatcherOrderIdResponse, actualWaitMasterAsDispatcherOrderIdResponse);
            });
            step(" клиент карточка последнего заказа - убедиться что wait-for-master-starting", () -> {
                System.out.println("waitMasterLastOrderResponse");
                actualWaitMasterLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.waitMasterLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualWaitMasterLastOrderResponse);
            });
            step("  клиент карточка заказа - убедиться что wait-for-master-starting", () -> {
                System.out.println("waitMasterOrdersIdResponseAsClient");
                OrdersIdResponseDto actualResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.waitMasterOrderIdAsClientBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actualResponse, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));  // vary
            });
            step("мастер авторизуется", () -> {
                commonFields.setTokenMaster(loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrMaster1Email, sssrMaster1Password)));
            });
            step("мастер карточка заказа - убедиться что wait-for-master-starting", () -> {
                System.out.println("waitMasterOrderIdResponseAsMaster");
                actualWaitMasterAsMasterOrderIdResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                expectedWaitMasterAsMasterOrderIdResponse = repairCase.waitMasterOrderIdAsMasterBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedWaitMasterAsMasterOrderIdResponse, actualWaitMasterAsMasterOrderIdResponse, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));
            });
        });

        step("заказ на ремонт в состоянии master-start-working", () -> {
            step("мастер сохраняет чеклист", () -> {
                SaveCheckListResponseDto actualResponse = saveCheckListApi.saveCheckList(repairCase.saveCheckListRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(SaveCheckListResponseDto.class);
                SaveCheckListResponseDto expectedResponse = SaveCheckListResponseDto.successResponse();
//                assertResponse(expectedResponse, actualResponse);
            });
            step("мастер карточка заказа - убедиться что master-start-working", () -> {
                System.out.println("masterStartWorkingOrderIdResponseAsMaster");
                actualMasterStartWorkingAsMasterOrderIdResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                expectedMasterStartWorkingAsMasterOrderIdResponse = repairCase.masterStartWorkingOrderIdAsMasterBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedMasterStartWorkingAsMasterOrderIdResponse, actualMasterStartWorkingAsMasterOrderIdResponse, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));
            });
            step(" клиент карточка последнего заказа - убедиться что master-start-working", () -> {
                System.out.println("masterStartWorkingLastOrderResponse");
                actualMasterStartWorkingLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.masterStartWorkingLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualMasterStartWorkingLastOrderResponse);
            });
        });

        step("заказ на ремонт в состоянии material invoice issued", () -> {
            step("мастер создает таблицу материалов", () -> {
                OrdersMaterialValuesResponseDto actualResponse = ordersMaterialValuesApi.materialValuesTable(repairCase.materialValuesRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersMaterialValuesResponseDto.class);
                OrdersMaterialValuesResponseDto expectedResponse = OrdersMaterialValuesResponseDto.successResponse();
//                assertResponse(expectedResponse, actualResponse);
            });
            step("мастер сохраняет таблицу материалов", () -> {
                OrdersSaveMaterialValuesResponseDto actualResponse = ordersSaveMaterialValuesApi.saveMaterialValues(repairCase.saveMaterialValuesRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersSaveMaterialValuesResponseDto.class);
                OrdersSaveMaterialValuesResponseDto expectedResponse = OrdersSaveMaterialValuesResponseDto.oneMaterialResponse();
//                assertResponse(expectedResponse, actualResponse);
            });
            step("мастер карточка заказа - убедиться что material invoice issued", () -> {
                System.out.println("materialInvoiceIssuedOrderIdResponseAsMaster");
                materialInvoiceIssuedOrderIdResponseAsMaster = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setReceipts1Id(materialInvoiceIssuedOrderIdResponseAsMaster.getData().getReceipts().get(1).getId());
                OrdersIdResponseDto expectedResponse = repairCase.materialInvoiceIssuedOrderIdAsMasterBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, materialInvoiceIssuedOrderIdResponseAsMaster, List.of("data.offers"));
            });
            step(" клиент карточка последнего заказа - убедиться что material invoice issued", () -> {
                System.out.println("materialInvoiceIssuedLastOrderResponse");
                materialInvoiceIssuedLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.materialInvoiceIssuedLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, materialInvoiceIssuedLastOrderResponse);
            });
        });

        step("заказ на ремонт в состоянии materialInvoicePaid", () -> {
            step("клиент оплачивает счет на Материалы", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment0Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment0Id(actualResponse.getData().getPaymentId());
            });
            step(" клиент карточка последнего заказа - убедиться что paidMaterialInvoice", () -> {
                System.out.println("materialInvoicePaidLastOrderResponse");
                materialInvoicePaidLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.materialInvoicePaidLastOrderInfoBUGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, materialInvoicePaidLastOrderResponse);
            });
            step("мастер карточка заказа - убедиться что materialInvoicePaid", () -> {
                System.out.println("materialInvoicePaidOrderIdResponseAsMaster");
                materialInvoicePaidOrderIdResponseAsMaster = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.materialInvoicePaidOrderIdAsMasterBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, materialInvoicePaidOrderIdResponseAsMaster, List.of("data.offers"));
            });
        });

        step("заказ на ремонт в состоянии actions-invoice-issued", () -> {
            step("мастер создает таблицу проведенных работ по заказу", () -> {
                OrdersActionsResponseDto actualResponse = ordersActionsApi.actionsTable(repairCase.actionsRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersActionsResponseDto.class);
                OrdersActionsResponseDto expectedResponse = OrdersActionsResponseDto.successResponse();
//                assertResponse(expectedResponse, actualResponse);
            });
            step("мастер сохраняет таблицу проведенных работ по заказу", () -> {
                OrdersSaveActionsResponseDto actualResponse = ordersSaveActionsApi.saveActions(repairCase.saveActionsRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersSaveActionsResponseDto.class);
                OrdersSaveActionsResponseDto expectedResponse = OrdersSaveActionsResponseDto.oneActionResponse();
//                assertResponse(expectedResponse, actualResponse);
            });
            step("мастер карточка заказа - убедиться что actions-invoice-issued", () -> {
                System.out.println("actionsInvoiceIssuedOrderIdResponseAsMaster");
                actionsInvoiceIssuedOrderIdResponseAsMaster = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setReceipts2Id(actionsInvoiceIssuedOrderIdResponseAsMaster.getData().getReceipts().get(2).getId());
                OrdersIdResponseDto expectedResponse = repairCase.actionsInvoiceIssuedOrderIdAsMasterBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actionsInvoiceIssuedOrderIdResponseAsMaster, List.of("data.offers"));
            });
            step(" клиент карточка последнего заказа - убедиться что actions-invoice-issued", () -> {
                System.out.println("actionsInvoiceIssuedLastOrderResponse");
                actionsInvoiceIssuedLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.actionsInvoiceIssuedLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actionsInvoiceIssuedLastOrderResponse);
            });
            step("  клиент карточка заказа - убедиться что actions-invoice-issued", () -> {
                System.out.println("actionsInvoiceIssuedOrderIdResponseAsClient");
                actionsInvoiceIssuedOrderIdResponseAsClient = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.actionsInvoiceIssuedOrderIdAsClientBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actionsInvoiceIssuedOrderIdResponseAsClient, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));
            });
        });

        step("заказ на ремонт в состоянии actions-invoice-paid", () -> {
            step("клиент оплачивает счет на Работы", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                commonFields.setPayment2Url(actualResponse.getData().getPayUrl());
                commonFields.setPayment2Id(actualResponse.getData().getPaymentId());
            });
            step(" клиент карточка последнего заказа - убедиться что actions-invoice-paid", () -> {
                System.out.println("actionsInvoicePaidLastOrderResponse");
                actionsInvoicePaidLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.actionsInvoicePaidLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actionsInvoicePaidLastOrderResponse);
            });
            step(" клиент карточка заказа - убедиться что actions-invoice-paid", () -> {
                System.out.println("actionsInvoicePaidOrderIdResponseAsClient");
                actionsInvoicePaidOrderIdResponseAsClient = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.actionsInvoicePaidOrderIdAsClientBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actionsInvoicePaidOrderIdResponseAsClient, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));
            });
            step("мастер карточка заказа - убедиться что actions-invoice-paid", () -> {
                System.out.println("actionsInvoicePaidOrderIdResponseAsMaster");
                actionsInvoicePaidOrderIdResponseAsMaster = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.actionsInvoicePaidOrderIdAsMasterBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actionsInvoicePaidOrderIdResponseAsMaster, List.of("data.offers"));
            });
        });

        step("заказ на ремонт в состоянии мастер  подписывает Акт", () -> {
            step("мастер создает Акт", () -> {
                OrdersCreateActResponseDto actualResponse = ordersCreateActApi.createAct(repairCase.createActRequest(commonFields), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersCreateActResponseDto.class);
                OrdersCreateActResponseDto expectedResponse = OrdersCreateActResponseDto.defaultResponse();
//                assertResponse(expectedResponse, actualResponse);
            });

            step("мастер карточка заказа - убедиться что Акт подписан", () -> {
                System.out.println("actSignedMasterOrderIdResponseAsMaster");
                actSignedMasterOrderIdResponseAsMaster = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.actSignedMasterOrderIdAsMasterBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actSignedMasterOrderIdResponseAsMaster, List.of("data.offers"));
            });
            step(" клиент карточка последнего заказа - убедиться что Акт подписан", () -> {
                System.out.println("actSignedMasterLastOrderResponse");
                actSignedMasterLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.actSignedMasterLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actSignedMasterLastOrderResponse);
            });
            step(" клиент карточка заказа - убедиться что Акт подписан", () -> {
                System.out.println("actSignedMasterOrderIdResponseAsClient");
                actSignedMasterOrderIdResponseAsClient = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.actSignedMasterOrderIdAsClientBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actSignedMasterOrderIdResponseAsClient, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));
            });
        });

        step("заказ на ремонт в состоянии клиент  подписывает Акт", () -> {
            step(" клиент - запрос на подпись Акта", () -> {
                OrdersSendSignResponseDto actualResponse = ordersSendSignApi.sendSign(repairCase.sendSignRequest(commonFields), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersSendSignResponseDto.class);
                OrdersSendSignResponseDto expectedResponse = OrdersSendSignResponseDto.successResponse();
//                assertResponse(expectedResponse, actualResponse);
            });
            step(" клиент -  подпись Акта", () -> {
                OrdersSignResponseDto actualResponse = ordersSignApi.sign(repairCase.signRequest(commonFields), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersSignResponseDto.class);
                OrdersSignResponseDto expectedResponse = OrdersSignResponseDto.successResponse();
//                assertResponse(expectedResponse, actualResponse);
            });
            step("мастер карточка заказа - убедиться что Акт подписан Клиентом", () -> {
                System.out.println("actSignedClientOrderIdResponseAsMaster");
                actSignedClientOrderIdResponseAsMaster = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenMaster())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                //refresh completedOrdersCount
                commonFields.setMasters0CompletedOrdersCount(actSignedClientOrderIdResponseAsMaster.getData().getMasters().get(0).getCompletedOrdersCount());
                OrdersIdResponseDto expectedResponse = repairCase.actSignedClientOrderIdAsMasterBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actSignedClientOrderIdResponseAsMaster, List.of("data.offers"));
            });
            step(" клиент карточка последнего заказа - убедиться что Акт подписан Клиентом", () -> {
                System.out.println("actSignedClientLastOrderResponse");
                actSignedClientLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.actSignedClientLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actSignedClientLastOrderResponse);
            });
            step(" клиент карточка заказа - убедиться что Акт подписан Клиентом", () -> {
                System.out.println("actSignedClientOrderIdResponseAsClient");
                actSignedClientOrderIdResponseAsClient = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.actSignedClientOrderIdAsClientBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actSignedClientOrderIdResponseAsClient, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));
            });
        });
    }

}

