package ru.gasworkers.dev.tests.web.orderProcess.consultation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.consultation.cancel.ConsultationCancelApi;
import ru.gasworkers.dev.api.consultation.cancel.dto.ConsultationCancelResponseDto;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.SelectConsultationMasterApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterResponseDto;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderResponseDto;
import ru.gasworkers.dev.api.orders.info.OrdersInfoApi;
import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
import ru.gasworkers.dev.api.orders.selectHouse.SelectHouseApi;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.GetClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.dto.GetClientHousesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoApi;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.extension.user.client.WithHouse;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Отмена консультации")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.PAYMENT)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.WEB)
public class CancelClientPayedConsultationTest extends BaseApiTest {

    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrderApi createOrdersApi = new CreateOrderApi();
    private final GetClientHousesApi getClientHousesApi = new GetClientHousesApi();
    private final SelectHouseApi selectHouseApi = new SelectHouseApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final SelectConsultationMasterApi selectConsultationMasterApi = new SelectConsultationMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final LastOrderInfoApi lastOrderInfoApi = new LastOrderInfoApi();
    private final OrdersInfoApi ordersInfoApi = new OrdersInfoApi();
    private final ConsultationCancelApi consultationCancelApi = new ConsultationCancelApi();
    private final LoginApi loginApi = new LoginApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    @Browser(role = UserRole.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.THIRD_ROLE)
    MasterPages masterPages;

    @ParameterizedTest(name = "{0}")
    @EnumSource(FinishConsultationCase.class)
    @DisplayName("Отмена  оплаченной консультации клиентом")
    void cancelClientPayedConsultationNow(FinishConsultationCase testCase, @WithClient(houses = {@WithHouse}) User client) throws IOException {
        String token = loginApi.getTokenPhone(client);
        Integer houseId = clientHousesApi.houseId(client, token);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create orders", () -> {
            return createOrdersApi.createOrder(testCase.getCreateOrdersDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrderResponseDto.class).getData().getOrderId();
        });
        GetClientHousesResponseDto actualResponse = step("Get client objects", () -> {
            return getClientHousesApi.getHouse(token)
                    .statusCode(200)
                    .extract().as(GetClientHousesResponseDto.class);
        });
        GetClientHousesResponseDto.DataDto firstData = actualResponse.getData()[0];
        Integer firstEquipmentId = firstData.getEquipments()[0].getId();

        List<Integer> equipmentList = new ArrayList<>();
        for (GetClientHousesResponseDto.Equipment equipment : firstData.getEquipments()) {
            equipmentList.add(equipment.getId());
        }
        Integer actualObjectId = firstData.getId();
        System.out.println("equipmentList = " + equipmentList);
        System.out.println("firstEquipmentId = " + firstEquipmentId);
        System.out.println("actualObjectId = " + actualObjectId);

        SelectHouseResponseDto expectedResponse = step("Select object", () -> {
            return selectHouseApi.selectObject(testCase.getSelectObjectDto(actualObjectId, orderId, equipmentList), token)
                    .statusCode(200)
                    .extract().as(SelectHouseResponseDto.class);
        });
        List<Integer> masterIdList = step("Get online masters", () -> {
            String responseString = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId), token)
                    .statusCode(200)
                    .extract().asString();
            JsonObject responseObject = JsonParser.parseString(responseString).getAsJsonObject();
            JsonArray dataArray = responseObject.getAsJsonArray("data");
            List<Integer> currentMasterIds = new ArrayList<>();
            for (JsonElement element : dataArray) {
                JsonObject masterObject = element.getAsJsonObject();
                int masterId = masterObject.get("id").getAsInt();
                currentMasterIds.add(masterId);
            }
            Integer firstMasterId = currentMasterIds.get(0);
            System.out.println("First master id: " + firstMasterId);
            return currentMasterIds;
        });

        Integer timetableId = step("Pick master", () -> {
            return selectConsultationMasterApi.selectMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(PickMasterResponseDto.class).getData().getTimetableId();
        });
        Integer receiptId = step("Apply master", () -> {
            return applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId, timetableId), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(ApplyMasterResponseDto.class).getData().getReceiptId();
        });
        SelectPaymentResponseDto selectPaymentResponse = step("Select payment", () -> {
            return selectPaymentApi.selectPayment(testCase.getSelectPaymentDto(orderId, receiptId), token)
                    .statusCode(200)
                    .extract().as(SelectPaymentResponseDto.class);
        });
        String payUrl = selectPaymentResponse.getData().getPayUrl();
        Integer paymentId = selectPaymentResponse.getData().getPaymentId();
        String masterEmail = step("Get master credentials", () -> {
            String tokenAdmin = loginApi.login(LoginRequestDto.asAdmin())
                    .statusCode(200)
                    .extract().as(LoginResponseDto.class).getData().getToken();
            String actualUserResponse = getUserWithAdminApi.getUserWithAdmin(tokenAdmin, masterIdList.get(0)) //487
                    .statusCode(200)
                    .extract().asString();
            JsonObject responseObject = JsonParser.parseString(actualUserResponse).getAsJsonObject();
            JsonObject dataObject = responseObject.getAsJsonObject("data");
            return dataObject.get("email").getAsString();
        });
        System.out.println("masterEmail = " + masterEmail);
        String phone = ComplexRegistrationRequestDto.builder()
                .phone(client.getPhone())
                .build().getPhone();
        step("убедиться что есть единственный последний заказ в статусе ожидания начала", () -> {
            LastOrderInfoResponseDto lastOrderResponse = lastOrderInfoApi.getLastOrderInfo(token)
                    .statusCode(200)
                    .extract().as(LastOrderInfoResponseDto.class);
            Integer lastOrderId = lastOrderResponse.getData().getId();
            String lastOrderStatus = lastOrderResponse.getData().getStatus();
            assertThat(lastOrderId).isEqualTo(orderId);
            Assertions.assertThat(lastOrderStatus).isEqualTo("wait-for-master-starting");
        });


       step("отмена оплаченной консультации", () -> {
           consultationCancelApi.cancelConsultation(testCase.getConsultationCancelDto(orderId), token)
                   .statusCode(200)
                   .extract().as(ConsultationCancelResponseDto.class);
       });
        step("убедиться что нет единственного последнего заказа после отмены", () -> {
            LastOrderInfoResponseDto noLastOrderResponse = lastOrderInfoApi.getLastOrderInfo(token)
                    .statusCode(200)
                    .extract().as(LastOrderInfoResponseDto.class);
            assertResponse(noLastOrderResponse, LastOrderInfoResponseDto.noLastOrderResponse());
        });
        step("убедиться что карточка заказа после отмены имеет статус canceled", () -> {
            OrdersInfoResponseDto ordersInfoResponse = ordersInfoApi.ordersInfo(orderId, token)
                    .statusCode(200)
                    .extract().as(OrdersInfoResponseDto.class);
            Integer currentOrderId = ordersInfoResponse.getData().getId();
            String currentStatus = ordersInfoResponse.getData().getStatus();
            String currentType = ordersInfoResponse.getData().getType();
            assertThat(currentStatus).isEqualTo("canceled");
            assertThat(currentType).isEqualTo("consultation");
            assertThat(currentOrderId).isEqualTo(orderId);
        });


//        ----------------------------  UI  --------------------------------
        step("авторизация Ролей ", () -> {
            step("авторизация Клиента", () -> {
                clientPages.getLoginPage().open();
//                clientPages.getDriverManager().switchToTab(0);
//                clientPages.getDriverManager().close();
                clientPages.getLoginPage().login(phone, "1234");
                clientPages.getHomePage().checkUrl();
               /* // Enable the Dark Reader extension
                Selenide.executeJavaScript("DarkReader.enable()");

                // Wait for the Dark Reader CSS to be applied
                Selenide.$("body").shouldHave(Condition.attribute("data-darkreader-mode", "dynamic"));
                // Switch back to the first tab*/

            });

            step("авторизация Мастера", () -> {
                masterPages.getLoginPage()
                        .open()
                        .login(masterEmail, "1234");
                masterPages.getHomePage().checkUrl();

            });
            step("Test run credentials ", () -> {
                Allure.addAttachment("Client creds", phone + ": " + "1234" + "/");
                Allure.addAttachment("Master creds", masterEmail + "/" + "1234");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });
        step("Мастер отсутствует уведомление", () -> {
            step("стр Лк мастера ", () -> {
                masterPages.getHomePage().conferenceNotification.noNoticeConsultationComponent();
            });
        });
        step("Клиент отсутствует уведомление", () -> {
            step("стр Лк клиента ", () -> {
                clientPages.getHomePage().consultationNotification.noNoticeConsultationComponent();
            });
            //todo check invoice presence after cancel
            //todo notification money back


        });

    }

}


