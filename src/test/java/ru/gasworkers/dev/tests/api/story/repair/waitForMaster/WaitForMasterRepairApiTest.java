package ru.gasworkers.dev.tests.api.story.repair.waitForMaster;

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
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateApi;
import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateResponseDto;
import ru.gasworkers.dev.api.orders.checkList.SaveCheckListApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdApi;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.orders.materialValues.OrdersMaterialValuesApi;
import ru.gasworkers.dev.api.orders.materialValues.OrdersSaveMaterialValuesApi;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterResponseDto;
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
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.story.repair.RepairTestCase;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("repair wait wor master")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class WaitForMasterRepairApiTest extends BaseApiTest {
    private final UserApi userApi = new UserApi();
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


    @Test
    @DisplayName("wait for master repair")
    void waitForMasterRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        commonFields.setTokenClient(loginApi.getTokenThrough(client));

        step("заказ на ремонт в состоянии published", () -> {
            step("клиент - модель пользователя в  состоянии published ", () -> {
                actualPublishedUserResponse = userApi.getUser(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(UserResponseDto.class);
                commonFields.setClientId(actualPublishedUserResponse.getData().getId());
                commonFields.setClientEmail(actualPublishedUserResponse.getData().getEmail());
                commonFields.setClientGuides0Id(actualPublishedUserResponse.getData().getGuides().get(0).getId());
                commonFields.setClientPhone(actualPublishedUserResponse.getData().getPhone());
                commonFields.setClientNotificationsCount(actualPublishedUserResponse.getData().getUserNotificationsCount());
//                UserResponseDto expectedResponse = repairCase.publishedClient(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualPublishedUserResponse);
            });
            step("клиент карточка последнего заказа - убедиться что в  состоянии published", () -> {
                actualPublishedLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                commonFields.setOrderId(actualPublishedLastOrderResponse.getData().getId());
                commonFields.setOrderNumber(actualPublishedLastOrderResponse.getData().getNumber());
                commonFields.setClientObjectId(actualPublishedLastOrderResponse.getData().getClientObject().getId());
                commonFields.setEquipments0Id(actualPublishedLastOrderResponse.getData().getEquipments().get(0).getId());
//                LastOrderInfoResponseDto expectedResponse = repairCase.publishedLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualPublishedLastOrderResponse);
            });
            step(" клиент карточка заказа - убедиться что в  состоянии published", () -> {
                actualPublishedOrderInfoResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
//                OrdersInfoResponseDto expectedResponse = repairCase.publishedOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoATExcludeFields(expectedResponse, actualPublishedOrderInfoResponse, List.of("data.offer"));
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
//                OrdersIdResponseDto expectedResponse = repairCase.publishedOrderIdAsDispatcherBGRepair(commonFields);
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
//                todo add dtoassert
//                assertThat(actualSelectMasterResponse.getData().getReceiptId()).isNull();
            });
        });

        step("заказ на ремонт в состоянии hasOffer", () -> {
            step(" клиент карточка последнего заказа - убедиться что в состоянии hasOffer", () -> {
                actualHasOfferLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                commonFields.setActiveOffersCount(actualHasOfferLastOrderResponse.getData().getClientObject().getActiveOffersCount());
//                LastOrderInfoResponseDto expectedResponse = repairCase.hasOfferLastOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualHasOfferLastOrderResponse);
            });
            step("клиент карточка заказа - убедиться что в состоянии hasOffer", () -> {
                actualHasOfferOrderInfoResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
//                offerIdHasOffer = actualHasOfferOrderInfoResponse.getData().getOffer().getId();
                commonFields.setOfferIdHasOfferClient(actualHasOfferOrderInfoResponse.getData().getOffer().getId());
//                OrdersInfoResponseDto expectedResponse = repairCase.hasOfferOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualHasOfferOrderInfoResponse);
            });
            step("диспетчер карточка заказа - убедиться что в состоянии hasOffer", () -> {
                System.out.println("actualHasOfferOrderIdResponse");
                actualHasOfferOrderIdResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setMasters0CompletedOrdersCount(actualHasOfferOrderIdResponse.data.getMasters().get(0).getCompletedOrdersCount());
                expectedHasOfferOrderIdResponse = repairCase.hasOfferOrderIdBGRepairAsDispatcher(commonFields);
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
//                assertThat(filteredServices).isNotEmpty();
                commonFields.setServiceId(filteredServices.get(0).getId());
                commonFields.setPossibleOfferId(filteredServices.get(0).getOfferId());
            });
            step("клиент выбирает предложение", () -> {
                SelectServiceCompanyResponseDto actualResponse = selectServiceCompanyApi.selectServiceCompany(commonFields.getOrderId(), commonFields.getServiceId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(SelectServiceCompanyResponseDto.class);
                commonFields.setReceipts0Id(actualResponse.getData().getReceiptId());
//                SelectServiceCompanyResponseDto expectedResponse = SelectServiceCompanyResponseDto.successResponse(commonFields.getReceipts0Id()).build();
//                assertThat(actualResponse).isEqualTo(expectedResponse);
            });
            step("клиент получает список банков на оплату", () -> {
                FspBankListResponseDto actualResponse = fspBankListApi.fspBankList(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(FspBankListResponseDto.class);
//                Integer availableBanks = actualResponse.getData().size();
//                System.out.println("availableBanks = " + availableBanks);
                //check  amount of banks
            });
            step("клиент карточка заказа - убедиться что перед оплатой", () -> {
                System.out.println("beforePaymentOrderIdResponseAsClient");
                actualBeforePaymentOrderInfoResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
//                Integer offerIdBeforePayment = actualBeforePaymentOrderInfoResponse.getData().getOffer().getId();
                commonFields.setOfferIdBeforePayment(actualBeforePaymentOrderInfoResponse.getData().getOffer().getId());
//                OrdersInfoResponseDto expectedResponse = repairCase.beforePaymentOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualBeforePaymentOrderInfoResponse);
            });
            step(" клиент карточка последнего заказа - убедиться что перед оплатой", () -> {
                System.out.println("beforePaymentLastOrderResponse");
                actualBeforePaymentLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
//                LastOrderInfoResponseDto expectedResponse = repairCase.beforePaymentLastOrderInfoBGRepair(commonFields);
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
//                OrdersInfoResponseDto expectedResponse = repairCase.dateSelectingOrderInfoBGRepair(commonFields);
//                assertResponsePartialNoAt(expectedResponse, actualDateSelectingOrderInfoResponse);
            });
            step(" клиент карточка последнего заказа - убедиться что Согласование даты и времени", () -> {
                actualDateSelectingLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
//                LastOrderInfoResponseDto expectedResponse = repairCase.dateSelectingLastOrderInfoBGRepair(commonFields);
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
                assertResponse(expectedResponse, actualResponse);
            });
            step("диспетчер карточка заказа - убедиться что wait-for-master-starting", () -> {
                System.out.println("waitMasterOrderIdResponseAsDispatcher");
                actualWaitMasterAsDispatcherOrderIdResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenDispatcher())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                commonFields.setSelectedDate(client.getSelectedDate());
                expectedWaitMasterAsDispatcherOrderIdResponse = repairCase.waitMasterOrderIdAsDispatcherBGRepair(commonFields);
                assertResponsePartialNoAt(expectedWaitMasterAsDispatcherOrderIdResponse, actualWaitMasterAsDispatcherOrderIdResponse);
            });
            step(" клиент карточка последнего заказа - убедиться что wait-for-master-starting", () -> {
                System.out.println("waitMasterLastOrderResponse");
                actualWaitMasterLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.waitMasterLastOrderInfoBGRepair(commonFields);
                assertResponsePartialNoAt(expectedResponse, actualWaitMasterLastOrderResponse);
            });
            step("  клиент карточка заказа - убедиться что wait-for-master-starting", () -> {
                System.out.println("waitMasterOrdersIdResponseAsClient");
                OrdersIdResponseDto actualResponse = ordersIdApi.ordersId(commonFields.getOrderId(), commonFields.getTokenClient())
                        .statusCode(200)
                        .extract().as(OrdersIdResponseDto.class);
                OrdersIdResponseDto expectedResponse = repairCase.waitMasterOrderIdAsClientBGRepair(commonFields);
                assertResponsePartialNoATExcludeFields(expectedResponse, actualResponse, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));  // vary
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
                assertResponsePartialNoATExcludeFields(expectedWaitMasterAsMasterOrderIdResponse, actualWaitMasterAsMasterOrderIdResponse, List.of("data.offers", "data.serviceCenter.masters.countNotReadNotification"));
            });
        });
    }
}
