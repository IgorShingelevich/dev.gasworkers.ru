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
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.PickMasterApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterResponseDto;
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersResponseDto;
import ru.gasworkers.dev.api.orders.selectHouse.SelectHouseApi;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.users.client.house.HouseApi;
import ru.gasworkers.dev.api.users.client.house.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.getHouse.GetHouseApi;
import ru.gasworkers.dev.api.users.client.house.getHouse.dto.GetHouseResponseDto;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithHouse;
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
    private final GetHouseApi getHouseApi = new GetHouseApi();
    private final SelectHouseApi selectHouseApi = new SelectHouseApi();
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
    void consultationNow(ConsultationMasterClientScenarioCase testCase, @WithUser(houses = {@WithHouse}) User client) {
        String token = loginApi.getToken(client);
        Integer houseId = houseApi.houseId(client, token);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create orders", () -> {
            return createOrdersApi.createOrders(testCase.getCreateOrdersDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrdersResponseDto.class).getData().getOrderId();
        });
        GetHouseResponseDto actualResponse = step("Get client objects", () -> {
            return getHouseApi.getHouse(token)
                    .statusCode(200)
                    .extract().as(GetHouseResponseDto.class);
        });
        GetHouseResponseDto.DataDto firstData = actualResponse.getData()[0];
        Integer firstEquipmentId = firstData.getEquipments()[0].getId();

        List<Integer> equipmentList = new ArrayList<>();
        for (GetHouseResponseDto.Equipment equipment : firstData.getEquipments()) {
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
            return pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0), token)
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
        step("Мастер начинает  консультацию", () -> {
            step("стр Лк мастера с уведомлением", () -> {
                masterPages.getHomePage().conferenceNotification.checkMasterStartConferenceButtonState();
                masterPages.getHomePage().conferenceNotification.mainButton();
            });
            step("стр QR кода", () -> {
                masterPages.getConferenceQrPage().checkUrl();
                masterPages.getConferenceQrPage().outlineButton();
            });
            step("стр Видео конференции", () -> {
                masterPages.getConferencePage().checkFinishLoading();
            });
        });
        step("Клиент принимает консультацию", () -> {
            step("стр Лк клиента с уведомлением", () -> {
                clientPages.getHomePage().conferenceNotification.checkClientJoinConferenceButtonState();
                clientPages.getHomePage().conferenceNotification.mainButton();
            });
            step("стр QR кода", () -> {
                clientPages.getConferenceQrPage().checkUrl();
                clientPages.getConferenceQrPage().outlineButton();
            });
            step("стр Видео конференции", () -> {
                clientPages.getConferencePage().checkFinishLoading();
            });
        });
        step("Завершение видеоконсультации", () -> {
            step("Мастер завершает  консультацию", () -> {
                masterPages.getConferencePage().endButton();
                masterPages.getResumeConferencePage().checkUrl();
                masterPages.getResumeConferencePage().fillResume("test");
                masterPages.getResumeConferencePage().primaryButton();
                masterPages.getOrderCardPage().checkUrl();
                //TODO: check orderCard state

            });
            step("Клиент завершает  консультацию", () -> {
                clientPages.getEndConferencePage().checkUrl();
                clientPages.getEndConferencePage().primaryButton();
                clientPages.getOrderCardPage().checkUrl();
                //TODO: check orderCard state
            });
        });

    }

}


