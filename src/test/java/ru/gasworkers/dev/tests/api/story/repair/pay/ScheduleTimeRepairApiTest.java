package ru.gasworkers.dev.tests.api.story.repair.pay;

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
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
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

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("Ремонт")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class ScheduleTimeRepairApiTest extends BaseApiTest {
    private final UserApi userApi = new UserApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
    private final FspBankListApi fspBankListApi = new FspBankListApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";
    private final RepairCase repairCase = new RepairCase();
    private String tokenClient;
    private String tokenDispatcher;
    private Integer clientId;
    private String clientEmail;
    private Integer clientGuides0Id;
    private Long clientPhone;
    private Integer clientNotificationsCount;
    private Integer serviceId;
    private Integer masterId;
    private Integer offerIdHasOffer;
    private Integer possibleOfferId;
    private Integer orderId;
    private Integer clientObjectId;
    private String orderNumber;
    private Integer equipmentsId;
    private Integer activeOffersCount;
    private Integer receiptId;
    private UserResponseDto actualPublishedUserResponse;
    private LastOrderInfoResponseDto actualPublishedLastOrderResponse;
    private OrdersInfoResponseDto actualPublishedOrderInfoResponse;
    private LastOrderInfoResponseDto actualHasOfferLastOrderResponse;
    private OrdersInfoResponseDto actualHasOfferOrderInfoResponse;
    private OrdersInfoResponseDto actualBeforePaymentOrderInfoResponse;
    private LastOrderInfoResponseDto actualBeforePaymentLastOrderResponse;
    private OrdersInfoResponseDto dateSelectingOrderInfoResponse;
    private LastOrderInfoResponseDto dateSelectingLastOrderResponse;


    @Test
    @DisplayName("payed repair")
    void payedRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        CommonFieldsRepairDto commonFields = new CommonFieldsRepairDto();


        tokenClient = loginApi.getTokenThrough(client);


        step("заказ на ремонт клиента в  состоянии published", () -> {
            step("клиент - модель пользователя в  состоянии published ", () -> {
                actualPublishedUserResponse = userApi.getUser(tokenClient)
                        .statusCode(200)
                        .extract().as(UserResponseDto.class);
                clientId = actualPublishedUserResponse.getData().getId();
                clientEmail = actualPublishedUserResponse.getData().getEmail();
                clientGuides0Id = actualPublishedUserResponse.getData().getGuides().get(0).getId();
                clientPhone = actualPublishedUserResponse.getData().getPhone();
                clientNotificationsCount = actualPublishedUserResponse.getData().getUserNotificationsCount();
                commonFields.setClientId(clientId);
                commonFields.setClientEmail(clientEmail);
                commonFields.setClientGuides0Id(clientGuides0Id);
                commonFields.setClientPhone(clientPhone);
                commonFields.setClientNotificationsCount(clientNotificationsCount);
                UserResponseDto expectedResponse = repairCase.publishedClient(commonFields);
                assertResponsePartialNoAt(expectedResponse, actualPublishedUserResponse);
            });

            step("клиент карточка последнего заказа - убедиться что есть последний заказ после фоновой регистрации", () -> {
                actualPublishedLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);

                orderId = actualPublishedLastOrderResponse.getData().getId();
                orderNumber = actualPublishedLastOrderResponse.getData().getNumber();
                clientObjectId = actualPublishedLastOrderResponse.getData().getClientObject().getId();
                equipmentsId = actualPublishedLastOrderResponse.getData().getEquipments().get(0).getId();
                commonFields.setOrderId(orderId);
                commonFields.setOrderNumber(orderNumber);
                commonFields.setClientObjectId(clientObjectId);
                commonFields.setEquipmentsId(equipmentsId);
                LastOrderInfoResponseDto expectedResponse = repairCase.publishedLastOrderInfoBGRepair(commonFields);
                assertResponsePartialNoAt(expectedResponse, actualPublishedLastOrderResponse);
            });

            step(" клиент карточка заказа - убедиться что после фоновой регистрации", () -> {
                actualPublishedOrderInfoResponse = ordersInfoApi.ordersInfo(commonFields.getOrderId(), tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                OrdersInfoResponseDto expectedResponse = repairCase.publishedOrderInfoBGRepair(commonFields);
                assertResponsePartialNoATExcludeFields(expectedResponse, actualPublishedOrderInfoResponse, List.of("data.offer"));
            });
        });

        step("диспетчер выбирает мастера ", () -> {
            step("диспетчер авторизуется", () -> {
                tokenDispatcher = loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password));
                commonFields.setTokenDispatcher(tokenDispatcher);
            });
            step("диспетчер получает список доступных мастеров ", () -> {
                masterId = companiesMastersApi.getAcceptedMasters(tokenDispatcher)
                        .statusCode(200)
                        .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId();
                commonFields.setMasterId(masterId);
            });
            step("диспетчер подтверждает выбор первого мастера ", () -> {
                SelectMasterResponseDto actualSelectMasterResponse = selectMasterApi.selectMaster(commonFields.getOrderId(), commonFields.getMasterId(), tokenDispatcher)
                        .statusCode(200)
                        .extract().as(SelectMasterResponseDto.class);
                //todo add dtoassert
                assertThat(actualSelectMasterResponse.getData().getReceiptId()).isNull();
            });
        });

        step("заказ на ремонт клиента в состоянии есть отклик СК", () -> {
            step(" клиент карточка последнего заказа - убедиться что есть отклик СК", () -> {
                actualHasOfferLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                activeOffersCount = actualHasOfferLastOrderResponse.getData().getClientObject().getActiveOffersCount();
                commonFields.setActiveOffersCount(activeOffersCount);
                LastOrderInfoResponseDto expectedResponse = repairCase.hasOfferLastOrderInfoBGRepair(commonFields);
                assertResponsePartialNoAt(expectedResponse, actualHasOfferLastOrderResponse);
            });
            step("клиент карточка заказа - убедиться что есть отклик СК", () -> {
                actualHasOfferOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                offerIdHasOffer = actualHasOfferOrderInfoResponse.getData().getOffer().getId();
                commonFields.setOfferIdHasOffer(offerIdHasOffer);
                OrdersInfoResponseDto expectedResponse = repairCase.hasOfferOrderInfoBGRepair(commonFields);
                assertResponsePartialNoAt(expectedResponse, actualHasOfferOrderInfoResponse);
            });
            step("клиент получает список доступных предложений", () -> {
                SuggestServicesResponseDto suggestedServiceResponse = suggestedServicesApi.suggestServices(orderId, tokenClient)
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
                serviceId = filteredServices.get(0).getId();
                possibleOfferId = filteredServices.get(0).getOfferId();
                commonFields.setServiceId(serviceId);
                commonFields.setPossibleOfferId(possibleOfferId);
//                assertThat(possibleOfferId).isEqualTo(offerId);

            });
            step("клиент выбирает предложение", () -> {
                SelectServiceCompanyResponseDto actualResponse = selectServiceCompanyApi.selectServiceCompany(orderId, serviceId, tokenClient)
                        .statusCode(200)
                        .extract().as(SelectServiceCompanyResponseDto.class);
                receiptId = actualResponse.getData().getReceiptId();
                commonFields.setReceiptId(receiptId);
                SelectServiceCompanyResponseDto expectedResponse = SelectServiceCompanyResponseDto.successResponse(receiptId).build();
                assertThat(actualResponse).isEqualTo(expectedResponse);
            });
            step("клиент получает список банков на оплату", () -> {
                FspBankListResponseDto actualResponse = fspBankListApi.fspBankList(tokenClient)
                        .statusCode(200)
                        .extract().as(FspBankListResponseDto.class);
                Integer availableBanks = actualResponse.getData().size();
                System.out.println("availableBanks = " + availableBanks);
                //check  amount of banks
            });
            step("клиент карточка заказа - убедиться что перед оплатой", () -> {
                actualBeforePaymentOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                Integer offerIdBeforePayment = actualBeforePaymentOrderInfoResponse.getData().getOffer().getId();
                commonFields.setOfferIdBeforePayment(offerIdBeforePayment);
                OrdersInfoResponseDto expectedResponse = repairCase.beforePaymentOrderInfoBGRepair(commonFields);
                assertResponsePartialNoAt(expectedResponse, actualBeforePaymentOrderInfoResponse);
            });
            step(" клиент карточка последнего заказа - убедиться что перед оплатой", () -> {
                actualBeforePaymentLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.beforePaymentLastOrderInfoBGRepair(commonFields);
                assertResponsePartialNoAt(expectedResponse, actualBeforePaymentLastOrderResponse);
            });
            step("клиент оплачивает  выезд мастера", () -> {
                SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(SelectPaymentResponseDto.class);
                String url = actualResponse.getData().getPayUrl();
                Integer paymentId = actualResponse.getData().getPaymentId();
                SelectPaymentResponseDto expectedResponse = SelectPaymentResponseDto.successResponse(url, paymentId);
                assertThat(actualResponse).isEqualTo(expectedResponse);
            });
        });

        step("заказ на ремонт клиента в  состоянии Согласование даты и времени", () -> {
            step("  клиент карточка заказа - убедиться что Согласование даты и времени", () -> {
                dateSelectingOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                Integer offerId = dateSelectingOrderInfoResponse.getData().getOffer().getId();
                OrdersInfoResponseDto expectedResponse = repairCase.dateSelectingOrderInfoBGRepair(commonFields);
                assertResponsePartialNoAt(expectedResponse, dateSelectingOrderInfoResponse);
            });
            step(" клиент карточка последнего заказа - убедиться что Согласование даты и времени", () -> {
                dateSelectingLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                LastOrderInfoResponseDto expectedResponse = repairCase.dateSelectingLastOrderInfoBGRepair(commonFields);
                assertResponsePartialNoAt(expectedResponse, dateSelectingLastOrderResponse);
            });
        });

    }
}
