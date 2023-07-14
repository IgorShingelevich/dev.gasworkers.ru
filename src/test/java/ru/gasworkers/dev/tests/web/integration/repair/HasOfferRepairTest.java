package ru.gasworkers.dev.tests.web.integration.repair;

import io.qameta.allure.*;
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
import ru.gasworkers.dev.api.orders.selectServiceCompany.SelectServiceCompanyApi;
import ru.gasworkers.dev.api.orders.suggestedServices.SuggestedServicesApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.api.users.companies.masters.CompaniesMastersApi;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersListResponse;
import ru.gasworkers.dev.api.users.fspBankList.FspBankListApi;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithOrderType;
import ru.gasworkers.dev.extension.user.WithThroughUser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.DispatcherPages;
import ru.gasworkers.dev.tests.api.BaseApiTest;
import ru.gasworkers.dev.tests.api.repair.pay.RepairCase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.REPAIR)
@Feature(AllureFeature.REPAIR)
@Story("Ремонт")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.WEB)
public class HasOfferRepairTest extends BaseApiTest {


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
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    //dispatcher
    @Browser(role = Role.DISPATCHER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    DispatcherPages dispatcherPages;
    private Integer orderId;
    private Integer clientObjectId;
    private String orderNumber;
    private Integer equipmentsId;
    private Integer serviceId;
    private Integer activeOffersCount;
    private Integer receiptId;

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
        String tokenClient = loginApi.getTokenThrough(client);

        step("заказ на ремонт клиента в  состоянии published", () -> {
            step("клиент карточка последнего заказа - убедиться что есть последний заказ после фоновой регистрации", () -> {
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
            step(" клиент карточка заказа - убедиться что после фоновой регистрации", () -> {
                actualPublishedOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                OrdersInfoResponseDto expectedResponse = repairCase.publishedOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId);
                assertResponsePartialNoATExcludeFields(expectedResponse, actualPublishedOrderInfoResponse, List.of("data.offer"));
            });
        });

        step("диспетчер выбирает мастера ", () -> {
            String tokenDispatcher = step("диспетчер авторизуется", () -> {
                return loginApi.getUserToken(LoginRequestDto.asUserEmail(sssrDispatcher1Email, sssrDispatcher1Password));
            });
            Integer masterId = step("диспетчер получает список доступных мастеров ", () -> {
                return companiesMastersApi.getAcceptedMasters(tokenDispatcher)
                        .statusCode(200)
                        .extract().as(CompaniesMastersListResponse.class).getData().get(0).getId();
            });
            step("диспетчер выбирает первого мастера ", () -> {
                return selectMasterApi.selectMaster(orderId, masterId, tokenDispatcher)
                        .statusCode(200)
                        .extract().as(SelectMasterResponseDto.class);
            });
        });

        step("заказ на ремонт клиента в состоянии есть отклик СК", () -> {
            step(" клиент карточка последнего заказа - убедиться что есть отклик СК", () -> {
                actualHasOfferLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(tokenClient)
                        .statusCode(200)
                        .extract().as(LastOrderInfoResponseDto.class);
                activeOffersCount = actualHasOfferLastOrderResponse.getData().getClientObject().getActiveOffersCount();
                LastOrderInfoResponseDto expectedResponse = repairCase.hasOfferLastOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId, activeOffersCount);
                assertResponsePartialNoAt(expectedResponse, actualHasOfferLastOrderResponse);
            });
            step("клиент карточка заказа - убедиться что есть отклик СК", () -> {
                actualHasOfferOrderInfoResponse = ordersInfoApi.ordersInfo(orderId, tokenClient)
                        .statusCode(200)
                        .extract().as(OrdersInfoResponseDto.class);
                Integer offerId = actualHasOfferOrderInfoResponse.getData().getOffer().getId();
                OrdersInfoResponseDto expectedResponse = repairCase.hasOfferOrderInfoBGRepair(orderId, orderNumber, clientObjectId, equipmentsId, activeOffersCount, offerId);
                assertResponsePartialNoAt(expectedResponse, actualHasOfferOrderInfoResponse);
            });
        });


//    ------------------------------------------------- UI -----------------------------------------------------------
        step("авторизация Ролей ", () -> {
            step("авторизация Клиента", () -> {
                clientPages.getLoginPage().open();
                clientPages.getLoginPage().login(client.getEmail(), "1111");
                clientPages.getHomePage().checkUrl();
            });

            step("авторизация Мастера", () -> {
                dispatcherPages.getLoginPage()
                        .open()
                        .login(sssrDispatcher1Email, "1234");
                dispatcherPages.getHomePage().checkUrl();
            });
            step("Test run credentials ", () -> {
                Allure.addAttachment("Client creds", client.getEmail() + ": " + "1111" + "/");
                Allure.addAttachment("Master creds", sssrDispatcher1Email + "/" + "1234");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });

       /* step("клиент - лк после поучения офера от СК", () -> {
            step(" клиент карточка последнего заказа - убедиться что есть отклик СК", () -> {
                clientPages.getHomePage()
                        .open()
                        .checkUrl();
                clientPages.getHomePage().checkLastOrderInfo(actualHasOfferLastOrderResponse);
            });
            step("клиент карточка заказа - убедиться что есть отклик СК", () -> {
                clientPages.getHomePage()
                        .open()
                        .checkUrl();
                clientPages.getHomePage().checkOrderInfo(actualHasOfferOrderInfoResponse);
            });

        });*/
    }


}
