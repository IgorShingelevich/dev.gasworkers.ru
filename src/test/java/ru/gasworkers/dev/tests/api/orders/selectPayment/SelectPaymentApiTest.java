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
import ru.gasworkers.dev.api.consultation.isStarted.IsStartedApi;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.PickMasterApi;
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.orders.selectObject.SelectObjectApi;
import ru.gasworkers.dev.api.orders.selectObject.dto.SelectObjectResponseDto;
import ru.gasworkers.dev.api.orders.selectPayment.SelectPaymentApi;
import ru.gasworkers.dev.api.users.client.equipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.addObject.AddHouseObjectApi;
import ru.gasworkers.dev.api.users.client.object.getClientObjects.GetClientObjectsApi;
import ru.gasworkers.dev.api.users.client.object.getClientObjects.dto.GetClientObjectResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Выбор мастера онлайн на консультацию сейчас")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.PAYMENT)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.API)
public class SelectPaymentApiTest extends BaseApiTest {
    private final AddHouseObjectApi addObjectApi = new AddHouseObjectApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();
    private final GetClientObjectsApi getClientObjectsApi = new GetClientObjectsApi();
    private final SelectObjectApi selectObjectApi = new SelectObjectApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final IsStartedApi isStartedApi = new IsStartedApi();
    private final PickMasterApi pickMasterApi = new PickMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();
    private final SelectPaymentApi selectPaymentApi = new SelectPaymentApi();
    /*
    https://api.dev.gasworkers.ru/docs#obieekty-POSTapi-v1-users-client-objects
    https://api.dev.gasworkers.ru/docs#obieekty-POSTapi-v1-client-objects--object--equipments
    https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-create
    https://api.dev.gasworkers.ru/docs#konsultacii-GETapi-v1-consultation-masters-online
    https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-masters--master-
    https://api.dev.gasworkers.ru/docs#konsultacii-POSTapi-v1-consultation-masters--master--apply
    https://api.dev.gasworkers.ru/docs#zakazy-POSTapi-v1-orders-select-payment
    */

    @ParameterizedTest(name = "{0}")
    @EnumSource(SelectPaymentPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(SelectPaymentPositiveCase testCase, @WithUser User client) {
        Integer objectId = step("Add object", () -> {
            JsonObject responseObject = JsonParser.parseString(
                    addObjectApi.addObject(AddHouseObjectBuilder.addDefaultHouseObjectRequest()) //todo add to test case
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentObjectId = responseObject.getAsJsonObject("data").get("id").getAsInt();
            return currentObjectId;
        });
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });

        Integer orderId = step("Create order", () -> {
                    JsonObject responseObject = JsonParser.parseString(
                            createOrdersApi.createOrders(testCase.getCreateOrdersDto())
                                    .statusCode(200)
                                    .extract().asString()
                    ).getAsJsonObject();
                    Integer currentOrderId = responseObject.getAsJsonObject("data").get("order_id").getAsInt();
                    System.out.println("CurrentOrderId = " + currentOrderId);
                    return currentOrderId;
                }
        );
        GetClientObjectResponseDto actualResponse = getClientObjectsApi.getClientObjects()
                .statusCode(200)
                .extract().as(GetClientObjectResponseDto.class);
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

        SelectObjectResponseDto expectedResponse = selectObjectApi.selectObject(testCase.getSelectObjectDto(actualObjectId, orderId, equipmentList))
                .statusCode(200)
                .extract().as(SelectObjectResponseDto.class);

        List<Integer> masterIdList = step("Get online masters", () -> {
            String responseString = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId))
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
            return currentMasterIds;
        });
       /* step("Is Started", () -> {
            isStartedApi.isStarted(testCase.getIsStartedDto(orderId))
                    .statusCode(200)
                    .extract().as(IsStartedResponseDto.class);
        });*/
        Integer timetableId = step("Pick master", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0))
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentTimetableId = actualResponseObject.getAsJsonObject("data").get("timetable_id").getAsInt();
            return currentTimetableId;
        });
        Integer receiptId = step("Apply master", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId, timetableId), masterIdList.get(0))
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentReceiptId = actualResponseObject.getAsJsonObject("data").get("receipt_id").getAsInt();
            System.out.println("Receipt id: " + currentReceiptId);
            return currentReceiptId;
        });
        String payUrl = step("Select payment", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    selectPaymentApi.selectPayment(testCase.getSelectPaymentDto(orderId, receiptId))
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            String currentPaymentUrl = actualResponseObject.getAsJsonObject("data").get("pay_url").getAsString();
            System.out.println("Payment url: " + currentPaymentUrl);
            return currentPaymentUrl;
        });

    }
}
