package ru.gasworkers.dev.tests.web.integration.consultation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.*;
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
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.PickMasterApi;
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.orders.selectObject.SelectObjectApi;
import ru.gasworkers.dev.api.orders.selectObject.dto.SelectObjectResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.users.client.equipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.HouseApi;
import ru.gasworkers.dev.api.users.client.house.HouseBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectResponseDTO;
import ru.gasworkers.dev.api.users.client.house.getHouse.GetHouseApi;
import ru.gasworkers.dev.api.users.client.house.getHouse.dto.GetClientObjectResponseDto;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Интеграция Консультация Клиент Мастер - консультация сейчас")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.PAYMENT)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class ConsultationClientMasterScenarioTest extends BaseApiTest {

    private final HouseApi houseApi = new HouseApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();
    private final GetHouseApi getClientObjectsApi = new GetHouseApi();
    private final SelectObjectApi selectObjectApi = new SelectObjectApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final PickMasterApi pickMasterApi = new PickMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final LoginApi loginApi = new LoginApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    @Browser(role = Role.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    @Browser(role = Role.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.THIRD_ROLE)
    MasterPages masterPages;

    @ParameterizedTest(name = "{0}")
    @EnumSource(ConsultationMasterClientScenarioCase.class)
    @DisplayName("Консультация сейчас")
    void consultationNow(ConsultationMasterClientScenarioCase testCase, @WithUser User client) {
        String token = loginApi.getToken(client);
        Integer objectId = step("Add object", () -> {
            return houseApi.addHouse(HouseBuilder.addDefaultHouseRequestDto(), token)
                    .statusCode(200)
                    .extract().as(AddHouseObjectResponseDTO.class).getData().getId();
        });

        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });

        Integer orderId = step("Create order", () -> {
            JsonObject responseObject = JsonParser.parseString(
                    createOrdersApi.createOrders(testCase.getCreateOrdersDto(), token)
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentOrderId = responseObject.getAsJsonObject("data").get("order_id").getAsInt();
            System.out.println("CurrentOrderId = " + currentOrderId);
            return currentOrderId;
        });

        GetClientObjectResponseDto actualResponse = step("Get client objects", () -> {
            return getClientObjectsApi.getClientObjects(token)
                    .statusCode(200)
                    .extract().as(GetClientObjectResponseDto.class);
        });

        GetClientObjectResponseDto.DataDto firstData = actualResponse.getData()[0];
        Integer firstEquipmentId = firstData.getEquipments()[0].getId();

        List<Integer> equipmentList = new ArrayList<>();
        for (GetClientObjectResponseDto.Equipment equipment : firstData.getEquipments()) {
            equipmentList.add(equipment.getId());
        }

        Integer actualObjectId = firstData.getId();
        System.out.println("equipmentList = " + equipmentList);
        System.out.println("firstEquipmentId = " + firstEquipmentId);
        System.out.println("actualObjectId = " + actualObjectId);

        SelectObjectResponseDto expectedResponse = step("Select object", () -> {
            return selectObjectApi.selectObject(testCase.getSelectObjectDto(actualObjectId, orderId, equipmentList), token)
                    .statusCode(200)
                    .extract().as(SelectObjectResponseDto.class);
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
            JsonObject actualResponseObject = JsonParser.parseString(
                    pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0), token)
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentTimetableId = actualResponseObject.getAsJsonObject("data").get("timetable_id").getAsInt();
            return currentTimetableId;
        });

        Integer receiptId = step("Apply master", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId, timetableId), masterIdList.get(0), token)
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentReceiptId = actualResponseObject.getAsJsonObject("data").get("receipt_id").getAsInt();
            System.out.println("Receipt id: " + currentReceiptId);
            return currentReceiptId;
        });

        String payUrl = step("Select payment", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    selectPaymentApi.selectPayment(testCase.getSelectPaymentDto(orderId, receiptId), token)
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            String currentPaymentUrl = actualResponseObject.getAsJsonObject("data").get("pay_url").getAsString();
            System.out.println("Payment url: " + currentPaymentUrl);
            return currentPaymentUrl;
        });
        String masterEmail = step("Get master credentials", () -> {
            LoginResponseDto actualAdminResponse = loginApi.login(LoginRequestDto.asAdmin())
                    .statusCode(200)
                    .extract().as(LoginResponseDto.class);

            LoginResponseDto.DataDto loginData = actualAdminResponse.getData();
            String tokenAdmin = loginData.getToken();
            System.out.println(" tokenAdmin = " + tokenAdmin);

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
        step("авторизация Ролей ", () -> {
            step("авторизация Клиента", () -> {
                clientPages.getLoginPage()
                        .open()
                        .login(phone, "1234");
//            clientPages.getHomePage().checkFinishLoading(client.fullName, client.sinceDate);  // TODO fix fullName order
            });
            step("авторизация Мастера", () -> {
                masterPages.getLoginPage()
                        .open()
                        .login(masterEmail, "1234");
                masterPages.getHomePage()
                        .checkFinishLoading();
            });
            step("Test run credentials ", () -> {
                Allure.addAttachment("Client creds", phone + ": " + "1234" + "/");
                Allure.addAttachment("Master creds", masterEmail + "/" + "1234");
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                Allure.addAttachment("RunStartTime: ", date);
            });
        });


    }
}


