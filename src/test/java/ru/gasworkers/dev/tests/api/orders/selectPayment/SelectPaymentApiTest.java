package ru.gasworkers.dev.tests.api.orders.selectPayment;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureFeature;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.administration.getUserWithAdmin.GetUserWithAdminApi;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.SelectConsultationMasterApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterResponseDto;
import ru.gasworkers.dev.api.orders.create.CreateOrderApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderResponseDto;
import ru.gasworkers.dev.api.orders.selectHouse.SelectHouseApi;
import ru.gasworkers.dev.api.orders.selectHouse.dto.SelectHouseResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentResponseDto;
import ru.gasworkers.dev.api.users.client.house.ClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.ClientHousesBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.HousesResponseDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.GetClientHousesApi;
import ru.gasworkers.dev.api.users.client.house.getClientHouses.dto.GetClientHousesResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Выбор мастера онлайн на консультацию сейчас")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.PAYMENT)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class SelectPaymentApiTest extends BaseApiTest {
    private final ClientHousesApi clientHousesApi = new ClientHousesApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrderApi createOrdersApi = new CreateOrderApi();
    private final GetClientHousesApi getClientHousesApi = new GetClientHousesApi();
    private final SelectHouseApi selectHouseApi = new SelectHouseApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final SelectConsultationMasterApi selectConsultationMasterApi = new SelectConsultationMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();

    private final GetUserWithAdminApi getUserWithAdminApi = new GetUserWithAdminApi();


    @ParameterizedTest(name = "{0}")
    @EnumSource(SelectPaymentPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(SelectPaymentPositiveCase testCase, @WithClient User client) {
        String token = loginApi.getTokenPhone(client);
        Integer objectId = step("Add object", () -> {
            return clientHousesApi.addHouse(ClientHousesBuilder.addDefaultHouseRequestDto(), token)
                    .statusCode(200)
                    .extract().as(HousesResponseDto.class).getData().getId();
        });

        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId, token)
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

        SelectHouseResponseDto expectedResponse = step("Select house", () -> {
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

        step("Select payment", () -> {
            SelectPaymentResponseDto selPayActualResponse = selectPaymentApi.selectPayment(testCase.getSelectPaymentDto(orderId, receiptId), token)
                    .statusCode(200)
                    .extract().as(SelectPaymentResponseDto.class);
            String payUrl = selPayActualResponse.getData().getPayUrl();
            Integer paymentId = selPayActualResponse.getData().getPaymentId();
            SelectPaymentResponseDto selPayExpectedResponse = SelectPaymentResponseDto.successResponse(payUrl, paymentId);
            assertEquals(selPayExpectedResponse, selPayActualResponse);
            assertResponse(selPayExpectedResponse, selPayActualResponse);
        });
    }
}

