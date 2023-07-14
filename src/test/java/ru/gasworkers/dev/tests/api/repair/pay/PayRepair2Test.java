//package ru.gasworkers.dev.tests.api.repair.pay;
//
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Owner;
//import io.qameta.allure.Story;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import ru.gasworkers.dev.allure.AllureEpic;
//import ru.gasworkers.dev.allure.AllureFeature;
//import ru.gasworkers.dev.allure.AllureTag;
//import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
//import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
//import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
//import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterApi;
//import ru.gasworkers.dev.api.orders.selectMaster.SelectMasterResponseDto;
//import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
//import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
//import ru.gasworkers.dev.api.orders.selectServiceCompany.SelectServiceCompanyApi;
//import ru.gasworkers.dev.api.orders.selectServiceCompany.dto.SelectServiceCompanyResponseDto;
//import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
//import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
//import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
//import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
//import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
//import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersListResponse;
//import ru.gasworkers.dev.api.users.fspBankList.FspBankListApi;
//import ru.gasworkers.dev.api.users.fspBankList.FspBankListResponseDto;
//import ru.gasworkers.dev.extension.user.User;
//import ru.gasworkers.dev.extension.user.WithOrderType;
//import ru.gasworkers.dev.extension.user.WithThroughUser;
//import ru.gasworkers.dev.tests.api.BaseApiTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.codeborne.pdftest.assertj.Assertions.assertThat;
//import static io.qameta.allure.Allure.step;
//
//@Owner("Igor Shingelevich")
//@Epic(AllureEpic.REPAIR)
//@Feature(AllureFeature.REPAIR)
//@Story("Ремонт")
//@Tag(AllureTag.REGRESSION)
//@Tag(AllureTag.CLIENT)
//@Tag(AllureTag.API)
//public class PayRepair2Test extends BaseApiTest {
//    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
//    private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
//    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
//    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
//    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
//
//    private final FspBankListApi fspBankListApi = new FspBankListApi();
//    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
//    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
//
//    private final  PreconditionSteps preconditionSteps = new PreconditionSteps();
//
//    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
//    private final String sssrDispatcher1Password = "1234";
//    private final RepairCase repairCase = new RepairCase();
//    private Integer orderId;
//    private Integer clientObjectId;
//    private String orderNumber;
//    private Integer equipmentsId;
//    private Integer serviceId;
//    private Integer activeOffersCount;
//    private Integer receiptId;
//
//    private LastOrderInfoResponseDto actualPublishedLastOrderResponse;
//    private OrdersInfoResponseDto actualPublishedOrderInfoResponse;
//
//    private LastOrderInfoResponseDto actualHasOfferLastOrderResponse;
//    private OrdersInfoResponseDto actualHasOfferOrderInfoResponse;
//
//    private OrdersInfoResponseDto actualBeforePaymentOrderInfoResponse;
//    private LastOrderInfoResponseDto actualBeforePaymentLastOrderResponse;
//
//    private OrdersInfoResponseDto dateSelectingOrderInfoResponse;
//    private LastOrderInfoResponseDto dateSelectingLastOrderResponse;
//
//    @Test
//    @DisplayName("payed repair")
//    void payedRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
//        String tokenClient = loginApi.getTokenThrough(client);
//
//        // Step: Create published order precondition
//        step("заказ на ремонт клиента в состоянии published", () -> {
//            CommonFieldsRepairDto publishedOrderData = preconditionSteps.createPublishedOrderPrecondition(tokenClient, client);
//            orderId = publishedOrderData.getOrderId();
//            orderNumber = publishedOrderData.getOrderNumber();
//            clientObjectId = publishedOrderData.getClientObjectId();
//            equipmentsId = publishedOrderData.getEquipmentsId();
//            LastOrderInfoResponseDto expectedPublishedLastOrderResponse = repairCase.publishedLastOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId);
//            assertResponsePartialNoAt(expectedPublishedLastOrderResponse, actualPublishedLastOrderResponse);
//
//            actualPublishedOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
//                    .statusCode(200)
//                    .extract().as(OrdersInfoResponseDto.class);
//            OrdersInfoResponseDto expectedPublishedOrderInfoResponse = repairCase.publishedOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId);
//            assertResponsePartialNoATExcludeFields(expectedPublishedOrderInfoResponse, actualPublishedOrderInfoResponse, List.of("data.offer"));
//        });
//
//        // Step: Select master precondition
//        step("диспетчер выбирает мастера", () -> {
//            preconditionSteps.selectMasterPrecondition(orderId);
//        });
//
//        // Step: Create hasOffer order precondition
//        step("заказ на ремонт клиента в состоянии есть отклик СК", () -> {
//            CommonFieldsRepairDto hasOfferOrderData = preconditionSteps.createHasOfferOrderPrecondition(tokenClient, client, CommonFieldsRepairDto.builder()
//                    .orderId(orderId)
//                    .orderNumber(orderNumber)
//                    .clientObjectId(clientObjectId)
//                    .equipmentsId(equipmentsId)
//                    .build());
//            activeOffersCount = hasOfferOrderData.getActiveOffersCount();
//            LastOrderInfoResponseDto expectedHasOfferLastOrderResponse = repairCase.hasOfferLastOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId, activeOffersCount);
//            assertResponsePartialNoAt(expectedHasOfferLastOrderResponse, actualHasOfferLastOrderResponse);
//
//            actualHasOfferOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
//                    .statusCode(200)
//                    .extract().as(OrdersInfoResponseDto.class);
//            Integer offerId = actualHasOfferOrderInfoResponse.getData().getOffer().getId();
//            OrdersInfoResponseDto expectedHasOfferOrderInfoResponse = repairCase.hasOfferOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId, activeOffersCount, offerId);
//            assertResponsePartialNoAt(expectedHasOfferOrderInfoResponse, actualHasOfferOrderInfoResponse);
//
//            SuggestServicesResponseDto suggestedServiceResponse = suggestedServicesApi.suggestServices(orderId, tokenClient)
//                    .statusCode(200)
//                    .extract().as(SuggestServicesResponseDto.class);
//            List<SuggestServicesResponseDto.Service> services = suggestedServiceResponse.getData().getServices();
//            // Filter companies with non-null prices
//            List<SuggestServicesResponseDto.Service> filteredServices = new ArrayList<>();
//            for (SuggestServicesResponseDto.Service c : services) {
//                if (c.getPrice() != null) {
//                    filteredServices.add(c);
//                }
//            }
//            serviceId = filteredServices.get(0).getId();
//
//            SelectServiceCompanyResponseDto selectServiceCompanyResponse = selectServiceCompanyApi.selectServiceCompany(orderId, serviceId, tokenClient)
//                    .statusCode(200)
//                    .extract().as(SelectServiceCompanyResponseDto.class);
//            receiptId = selectServiceCompanyResponse.getData().getReceiptId();
//            SelectServiceCompanyResponseDto expectedSelectServiceCompanyResponse = SelectServiceCompanyResponseDto.successResponse(receiptId).build();
//            assertThat(selectServiceCompanyResponse).isEqualTo(expectedSelectServiceCompanyResponse);
//        });
//
//        // Step: Pre-payment checks and actions
//        step("клиент карточка заказа - убедиться что перед оплатой", () -> {
//            actualBeforePaymentOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
//                    .statusCode(200)
//                    .extract().as(OrdersInfoResponseDto.class);
//            Integer offerId = actualBeforePaymentOrderInfoResponse.getData().getOffer().getId();
//            OrdersInfoResponseDto expectedBeforePaymentOrderInfoResponse = repairCase.beforePaymentOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId, activeOffersCount, offerId, receiptId);
//            assertResponsePartialNoAt(expectedBeforePaymentOrderInfoResponse, actualBeforePaymentOrderInfoResponse);
//        });
//
//        step("клиент карточка последнего заказа - убедиться что перед оплатой", () -> {
//            actualBeforePaymentLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
//                    .statusCode(200)
//                    .extract().as(LastOrderInfoResponseDto.class);
//            LastOrderInfoResponseDto expectedBeforePaymentLastOrderResponse = repairCase.beforePaymentLastOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId, activeOffersCount);
//            assertResponsePartialNoAt(expectedBeforePaymentLastOrderResponse, actualBeforePaymentLastOrderResponse);
//        });
//
//        step("клиент оплачивает выезд мастера", () -> {
//            SelectPaymentResponseDto actualResponse = selectPaymentApi.payCard(orderId, tokenClient)
//                    .statusCode(200)
//                    .extract().as(SelectPaymentResponseDto.class);
//            String url = actualResponse.getData().getPayUrl();
//            Integer paymentId = actualResponse.getData().getPaymentId();
//            SelectPaymentResponseDto expectedResponse = SelectPaymentResponseDto.successResponse(url, paymentId);
//            assertThat(actualResponse).isEqualTo(expectedResponse);
//        });
//
//        // Step: Date selecting
//        step("клиент карточка заказа - убедиться что Согласование даты и времени", () -> {
//            dateSelectingOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
//                    .statusCode(200)
//                    .extract().as(OrdersInfoResponseDto.class);
//            Integer offerId = dateSelectingOrderInfoResponse.getData().getOffer().getId();
//            OrdersInfoResponseDto expectedDateSelectingOrderInfoResponse = repairCase.dateSelectingOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId, activeOffersCount, offerId, receiptId);
//            assertResponsePartialNoAt(expectedDateSelectingOrderInfoResponse, dateSelectingOrderInfoResponse);
//        });
//
//        step("клиент карточка последнего заказа - убедиться что Согласование даты и времени", () -> {
//            dateSelectingLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
//                    .statusCode(200)
//                    .extract().as(LastOrderInfoResponseDto.class);
//            LastOrderInfoResponseDto expectedDateSelectingLastOrderResponse = repairCase.dateSelectingLastOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId, activeOffersCount);
//            assertResponsePartialNoAt(expectedDateSelectingLastOrderResponse, dateSelectingLastOrderResponse);
//        });
//    }
//
//    // Inner class for API precondition steps
//    private  class PreconditionSteps {
//        private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
//        private final CompaniesMastersApi companiesMastersApi = new CompaniesMastersApi();
//        private final SelectMasterApi selectMasterApi = new SelectMasterApi();
//        private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
//        private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();
//
//        private final FspBankListApi fspBankListApi = new FspBankListApi();
//        private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
//        private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
//
//        public CommonFieldsRepairDto createPublishedOrderPrecondition(String tokenClient, User client) {
//            CommonFieldsRepairDto data = new CommonFieldsRepairDto();
//            // Step implementation for "заказ на ремонт клиента в состоянии published"
//            // Set the necessary data in the 'data' object
//            // ...
//            // Continue with other fields
//            // ...
//            return data;
//        }
//
//        public void selectMasterPrecondition(Integer orderId) {
//            // Step implementation for "диспетчер выбирает мастера"
//            // Use the necessary data from 'data' object
//            // ...
//            // Continue with other fields
//            // ...
//        }
//
//        public CommonFieldsRepairDto createHasOfferOrderPrecondition(String tokenClient, User client, CommonFieldsRepairDto previousData) {
//            CommonFieldsRepairDto data = new CommonFieldsRepairDto();
//            // Step implementation for "заказ на ремонт клиента в состоянии есть отклик СК"
//            // Set the necessary data in the 'data' object using previousData if needed
//            // ...
//            // Continue with other fields
//            // ...
//            return data;
//        }
//
//        // Implement other precondition methods for the remaining steps
//        // ...
//
//        // Add more precondition methods as needed
//        // ...
//    }
//
//    // Helper methods for assertions and other utilities
//    // ...
//}
