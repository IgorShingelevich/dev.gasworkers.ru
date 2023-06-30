package ru.gasworkers.dev.tests.api.consultation.masters.apply;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
import ru.gasworkers.dev.api.consultation.isStarted.dto.IsStartedResponseDto;
import ru.gasworkers.dev.api.consultation.masters.apply.ApplyMasterApi;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterResponseDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.OnlineMastersApi;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersResponseDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.PickMasterApi;
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.users.client.equipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.addObject.AddHouseObjectApi;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Owner("Igor Shingelevich")
@Epic(AllureEpic.CONSULTATION)
@Feature(AllureFeature.CONSULTATION_NOW)
@Story("Подтверждение выбора мастера онлайн")
@Tag(AllureTag.REGRESSION)
@Tag(AllureTag.CONSULTATION)
@Tag(AllureTag.CLIENT)
@Tag(AllureTag.MASTER)
@Tag(AllureTag.API)
public class ApplyMasterApiTest extends BaseApiTest {
    private final AddHouseObjectApi addObjectApi = new AddHouseObjectApi();
    private final AddEquipmentApi addEquipmentApi = new AddEquipmentApi();
    private final CreateOrdersApi createOrdersApi = new CreateOrdersApi();
    private final OnlineMastersApi onlineMastersApi = new OnlineMastersApi();
    private final IsStartedApi isStartedApi = new IsStartedApi();
    private final PickMasterApi pickMasterApi = new PickMasterApi();
    private final ApplyMasterApi applyMasterApi = new ApplyMasterApi();

    @ParameterizedTest(name = "{0}")
    @EnumSource(ApplyMasterPositiveCase.class)
    @Tag(AllureTag.POSITIVE)
    @DisplayName("Success case:")
    void positiveTestCase(ApplyMasterPositiveCase testCase, @WithUser User client) {
        Integer objectId = step("Add object", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    addObjectApi.addObject(AddHouseObjectBuilder.addDefaultHouseObjectRequest()) //todo add to test case
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentObjectId = actualResponseObject.getAsJsonObject("data").get("id").getAsInt();
            return currentObjectId;
        });
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create order", () -> {
            JsonObject actualResponseObject = JsonParser.parseReader(
                    new JsonReader(new StringReader(
                            createOrdersApi.createOrders(testCase.getCreateOrdersDto())
                                    .statusCode(200)
                                    .extract().asString()
                    ))
            ).getAsJsonObject();
            return actualResponseObject.getAsJsonObject("data").get("order_id").getAsInt();
        });
        List<Integer> masterIdList = step("Get online masters list", () -> {
            OnlineMastersResponseDto responseDto = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId))
                    .statusCode(200)
                    .extract()
                    .as(OnlineMastersResponseDto.class);
            // Asserting that at least one master is returned
            assertNotNull(responseDto.getData().get(0).getId());
            // get List of master id from responseDto
            List<Integer> currentMasterIdList = new ArrayList<>();
            for (OnlineMastersResponseDto.MasterDto element : responseDto.getData()) {
                currentMasterIdList.add(element.getId());
            }
            return currentMasterIdList;
        });
        step("Is Started", () -> {
            isStartedApi.isStarted(testCase.getIsStartedDto(orderId))
                    .statusCode(200)
                    .extract().as(IsStartedResponseDto.class);
        });
        Integer timetableId = step("Pick master", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0))
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentTimetableId = actualResponseObject.getAsJsonObject("data").get("timetable_id").getAsInt();
            System.out.println("Timetable ID: " + currentTimetableId);
            return currentTimetableId;
        });
        step("Apply master", () -> {
            applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId, timetableId), masterIdList.get(0))
                    .statusCode(200)
                    .extract().as(ApplyMasterResponseDto.class);
            //todo add assertions
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(ApplyMasterNegativeCase.class)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Negative case:")
    void negativeTestCase(ApplyMasterNegativeCase testCase, @WithUser User client) {
        Integer objectId = step("Add object", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    addObjectApi.addObject(AddHouseObjectBuilder.addDefaultHouseObjectRequest()) //todo add to test case
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentObjectId = actualResponseObject.getAsJsonObject("data").get("id").getAsInt();
            return currentObjectId;
        });
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), objectId)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create order", () -> {
            JsonObject actualResponseObject = JsonParser.parseReader(
                    new JsonReader(new StringReader(
                            createOrdersApi.createOrders(testCase.getCreateOrdersDto())
                                    .statusCode(200)
                                    .extract().asString()
                    ))
            ).getAsJsonObject();
            return actualResponseObject.getAsJsonObject("data").get("order_id").getAsInt();
        });
        List<Integer> masterIdList = step("Get online masters list", () -> {
            OnlineMastersResponseDto responseDto = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId))
                    .statusCode(200)
                    .extract()
                    .as(OnlineMastersResponseDto.class);
            // Asserting that at least one master is returned
            assertNotNull(responseDto.getData().get(0).getId());
            // get List of master id from responseDto
            List<Integer> currentMasterIdList = new ArrayList<>();
            for (OnlineMastersResponseDto.MasterDto element : responseDto.getData()) {
                currentMasterIdList.add(element.getId());
            }
            return currentMasterIdList;
        });
        step("Is Started", () -> {
            isStartedApi.isStarted(testCase.getIsStartedDto(orderId))
                    .statusCode(200)
                    .extract().as(IsStartedResponseDto.class);
        });
        Integer timetableId = step("Pick master", () -> {
            JsonObject actualResponseObject = JsonParser.parseString(
                    pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0))
                            .statusCode(200)
                            .extract().asString()
            ).getAsJsonObject();
            Integer currentTimetableId = actualResponseObject.getAsJsonObject("data").get("timetable_id").getAsInt();
            System.out.println("Timetable ID: " + currentTimetableId);
            return currentTimetableId;
        });
        step("Apply master", () -> {
            applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId, timetableId), masterIdList.get(0))
                    .statusCode(422)
                    .extract().as(ApplyMasterResponseDto.class);
            //todo add assertions
        });
    }


}
