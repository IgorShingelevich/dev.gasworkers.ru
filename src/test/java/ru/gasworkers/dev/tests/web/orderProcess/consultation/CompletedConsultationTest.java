package ru.gasworkers.dev.tests.web.orderProcess.consultation;

import com.codeborne.selenide.Selenide;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.auth.login.LoginApi;
import ru.gasworkers.dev.api.auth.login.dto.LoginRequestDto;
import ru.gasworkers.dev.api.auth.login.dto.LoginResponseDto;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.SelectConsultationMasterApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterResponseDto;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderResponseDto;
import ru.gasworkers.dev.api.orders.selectHouse.SelectHouseApi;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseRequestDto;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentRequestDto;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.GetClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.dto.GetClientHousesResponseDto;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.extension.user.client.WithHouse;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.MasterPages;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateTest.BaseWebSTClientConsultationTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Видеоконсультация")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.WEB)
public class CompletedConsultationTest extends BaseWebSTClientConsultationTest {

    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrderApi createOrdersApi = new CreateOrderApi();
    private final GetClientHousesApi getClientHousesApi = new GetClientHousesApi();
    private final SelectHouseApi selectHouseApi = new SelectHouseApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final SelectConsultationMasterApi selectConsultationMasterApi = new SelectConsultationMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    private final LoginApi loginApi = new LoginApi();
    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();
    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;
    @Browser(role = UserRole.MASTER, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.THIRD_ROLE)
    MasterPages masterPages;

    @Disabled
    @Test
    @DisplayName("Консультация - в состоянии completed")
    void completedConsultation(@WithClient(houses = {@WithHouse}) User client) {
        String token = loginApi.getTokenPhone(client);
        Integer houseId = clientHousesApi.houseId(client, token);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(AddEquipmentRequestDto.defaultBoilerEquipment(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create orders", () -> {
            return createOrdersApi.createOrder(CreateOrderRequestDto.builder()
                            .type("consultation")
                            .houseId(String.valueOf(houseId))
                            .build(), token)
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
            return selectHouseApi.selectObject(SelectHouseRequestDto.builder()
                            .clientObjectId(houseId)
                            .orderId(orderId)
                            .equipment(equipmentList)
                            .build(), token)
                    .statusCode(200)
                    .extract().as(SelectHouseResponseDto.class);
        });
        List<Integer> masterIdList = step("Get online masters", () -> {
            String responseString = onlineMastersApi.getOnlineMasters(OnlineMastersRequestDto.builder()
                            .orderId(orderId)
                            .search("rating")
                            .build(), token)
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
            return selectConsultationMasterApi.selectMaster(PickMasterRequestDto.builder()
                            .orderId(orderId)
                            .online(true)
                            .build(), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(PickMasterResponseDto.class).getData().getTimetableId();
        });
        Integer receiptId = step("Apply master", () -> {
            return applyMasterApi.applyMaster(ApplyMasterRequestDto.builder()
                            .orderId(orderId)
                            .timetableId(timetableId)
                            .description("test description")
                            .now(true)
                            .build(), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(ApplyMasterResponseDto.class).getData().getReceiptId();
        });
        SelectPaymentResponseDto selectPaymentResponse = step("Select payment", () -> {
            return selectPaymentApi.selectPayment(SelectPaymentRequestDto.builder()
                            .orderId(orderId)
                            .receiptId(receiptId)
                            .type("fps")
                            .build(), token)
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
                Selenide.sleep(6000);
                masterPages.getConferencePage().checkFinishLoading();
            });
        });
        step("Клиент принимает консультацию", () -> {
            step("стр Лк клиента с уведомлением", () -> {
                clientPages.getHomePage().consultationNotification.checkClientJoinConferenceButtonState();
                clientPages.getHomePage().consultationNotification.mainButton();
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
                // wait for client enter the conference
                Selenide.sleep(6000);
                masterPages.getConferencePage().endButton();
                masterPages.getResumeConferencePage().checkUrl();
                masterPages.getResumeConferencePage().fillResume("test-resume");
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
        step("Проверка карточки заказа после видеоконсультации", () -> {
            step("стр Лк клиента", () -> {
//                sleep(30000);
//                clientPages.getDriverManager().refresh();
                clientPages.getOrderCardPage().checkUrl();
                clientPages.getOrderCardPage().nav.infoMaster();
                clientPages.getOrderCardPage().infoMasterTab.checkResumeConference("test-resume");
            });
            step("стр Лк мастера", () -> {
//                masterPages.getDriverManager().refresh();
                masterPages.getOrderCardPage().checkUrl();
                masterPages.getOrderCardPage().navInfoMaster();
                masterPages.getOrderCardPage().tabInfoMaster.checkResumeConference("test-resume");
            });
        });

    }
}



