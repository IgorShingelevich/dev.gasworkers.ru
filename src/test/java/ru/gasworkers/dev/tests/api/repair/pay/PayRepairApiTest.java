package ru.gasworkers.dev.tests.api.repair.pay;

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
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMasters;
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
public class PayRepairApiTest extends BaseApiTest {

    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final CompaniesMasters companiesMasters = new CompaniesMasters();
    private final SelectMasterApi selectMasterApi = new SelectMasterApi();
    private final SuggestedServicesApi suggestedServicesApi = new SuggestedServicesApi();
    private final SelectServiceCompanyApi selectServiceCompanyApi = new SelectServiceCompanyApi();

    private final FspBankListApi fspBankListApi = new FspBankListApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();

    private final String sssrDispatcher1Email = "test_gw_dispatcher_sssr1@rambler.ru";
    private final String sssrDispatcher1Password = "1234";
    private final RepairCase repairCase = new RepairCase();
    Integer offerId;
    private Integer orderId;
    private Integer clientObjectId;
    private String orderNumber;
    private Integer equipmentsId;
    private LastOrderInfoResponseDto actualPublishedLastOrderResponse;
    private LastOrderInfoResponseDto hasOfferLastOrderResponse;
    private OrdersInfoResponseDto newBGRepairOrderInfoResponse;
    private OrdersInfoResponseDto beforePaymentOrderInfoResponse;
    private OrdersInfoResponseDto afterPaymentOrderInfoResponse;

    @Test
    @DisplayName("payed repair")
    void payedRepair(@WithThroughUser(withOrderType = @WithOrderType(type = "repair")) User client) {
        String tokenClient = loginApi.getTokenThrough(client);

        step("заказ на ремонт клиента в  состоянии published", () -> {
            step("убедиться что есть единственный последний заказ в статусе ожидания начала", () -> {
                actualPublishedLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                orderId = actualPublishedLastOrderResponse.getData().getId();
                orderNumber = actualPublishedLastOrderResponse.getData().getNumber();
                clientObjectId = actualPublishedLastOrderResponse.getData().getClientObject().getId();
                equipmentsId = actualPublishedLastOrderResponse.getData().getEquipments().get(0).getId();
                LastOrderInfoResponseDto expectedResponse = repairCase.publishedLastOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId);
                assertResponsePartialNoAt(expectedResponse, actualPublishedLastOrderResponse);
            });
            step(" клиент -  проверка orders/info после фоновой регистрации", () -> {
                newBGRepairOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                offerId = newBGRepairOrderInfoResponse.getData().getOffer().getId();
                OrdersInfoResponseDto expectedResponse = repairCase.publishedOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId);
                assertResponsePartialNoATExcludeFields(expectedResponse, newBGRepairOrderInfoResponse, List.of("data.offer"));
            });
        });

        step("диспетчер выбирает   мастера ", () -> {
            String tokenDispatcher = step("диспетчер авторизуется", () -> {
                return loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password));
            });
            Integer masterId = step("диспетчер получает список доступных мастеров ", () -> {
                return companiesMasters.getAcceptedMasters(tokenDispatcher)
                        .statusCode(200)
                        .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId();
            });
            step("диспетчер выбирает первого  мастера ", () -> {
                return selectMasterApi.selectMaster(orderId, masterId, tokenDispatcher)
                        .statusCode(200)
                        .extract().as(SelectMasterResponseDto.class);
            });
        });

        step("заказ на ремонт клиента в  состоянии Есть отклик", () -> {
            step(" клиент карточка последнего заказа - убедиться что СК предложила мастера", () -> {
                hasOfferLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                String orderType = hasOfferLastOrderResponse.getData().getType();
                String orderStatus = hasOfferLastOrderResponse.getData().getStatus();
                assertThat(orderStatus).isEqualTo("published");
                assertThat(orderType).isEqualTo("repair");
                assertThat(hasOfferLastOrderResponse.getData().getCreationStatus()).isEqualTo("select-service");
                Integer activeOffersCount = hasOfferLastOrderResponse.getData().getClientObject().getActiveOffersCount();
                assertThat(activeOffersCount).isEqualTo(1);
            });
            Integer serviceId = step("клиент получает список доступных предложений", () -> {
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
                return filteredServices.get(0).getId();
            });
            step("клиент выбирает предложение", () -> {
                SelectServiceCompanyResponseDto actualResponse = selectServiceCompanyApi.selectServiceCompany(orderId, serviceId, tokenClient)
                        .statusCode(200)
                        .extract().as(SelectServiceCompanyResponseDto.class);
                Integer receiptIdClient = actualResponse.getData().getReceiptId();
                System.out.println("receiptIdClient = " + receiptIdClient);
                SelectServiceCompanyResponseDto expectedResponse = SelectServiceCompanyResponseDto.successResponse(receiptIdClient).build();
                assertThat(actualResponse).isEqualTo(expectedResponse);
            });
            step("клиент получает список банков на оплату", () -> {
                FspBankListResponseDto actualResponse = fspBankListApi.fspBankList(tokenClient)
                        .statusCode(200)
                        .extract().as(FspBankListResponseDto.class);
                Integer availableBanks = actualResponse.getData().size();
                System.out.println("availableBanks = " + availableBanks);
                //ckeck  amount of banks
            });
            step(" Api клиент -  проверка orders/info перед оплатой", () -> {
                beforePaymentOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
            /*String orderStatus = beforePaymentOrderInfoResponse.getData().getStatus();
            String type = beforePaymentOrderInfoResponse.getData().getType();
            String stage = beforePaymentOrderInfoResponse.getData().getStage();
            Integer id = beforePaymentOrderInfoResponse.getData().getId();
            Integer serviceCenterId = beforePaymentOrderInfoResponse.getData().getServiceCenter().getId();
            Double priceSssr = 11.99;
            assertThat(id).isEqualTo(orderId);
            assertThat(orderStatus).isEqualTo("published");
            assertThat(type).isEqualTo("repair");
            assertThat(stage).isEqualTo("repair_initial_departure");
            assertThat(serviceCenterId).isEqualTo(39); //sssrDispatcher
            assertThat(beforePaymentOrderInfoResponse.getData().getServiceCenter().getFullPrice()).isEqualTo(priceSssr);
            return beforePaymentOrderInfoResponse;*/
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
            step("  клиент -  проверка orders/info после оплаты", () -> {
                afterPaymentOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
           /* String orderStatus = afterPaymentOrderInfoResponse.getData().getStatus();
            String type = afterPaymentOrderInfoResponse.getData().getType();
            String stage = afterPaymentOrderInfoResponse.getData().getStage();
            Integer id = afterPaymentOrderInfoResponse.getData().getId();
            Integer serviceCenterId = afterPaymentOrderInfoResponse.getData().getServiceCenter().getId();
            Double priceSssr = 11.99;
            assertThat(id).isEqualTo(orderId);
            assertThat(orderStatus).isEqualTo("date-selecting");
            assertThat(stage).isEqualTo("repair_initial_departure");
            assertThat(serviceCenterId).isEqualTo(39); //sssrDispatcher
            assertThat(afterPaymentOrderInfoResponse.getData().getServiceCenter().getFullPrice()).isEqualTo(priceSssr);
            assertResponse(beforePaymentOrderInfoResponse, afterPaymentOrderInfoResponse);*/

//            assertResponsePartial2(ordersInfoBeforePaymentResponse, afterPaymentOrderInfoResponse, List.of("data.serviceCenter.fullPrice"));
//            compareJsonDifferences(ordersInfoBeforePaymentResponse, afterPaymentOrderInfoResponse);
            });// compare last order response   before and after payment
        });

    }


}
