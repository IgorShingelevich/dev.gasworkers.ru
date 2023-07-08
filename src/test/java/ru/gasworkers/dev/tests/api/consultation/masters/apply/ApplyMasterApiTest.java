package ru.gasworkers.dev.tests.api.consultation.masters.apply;

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
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterResponseDto;
import ru.gasworkers.dev.api.orders.create.CreateOrdersApi;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersResponseDto;
import ru.gasworkers.dev.api.users.client.house.HouseApi;
import ru.gasworkers.dev.api.users.client.house.addEquipment.AddEquipmentApi;
import ru.gasworkers.dev.api.users.client.house.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithHouse;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

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
    private final HouseApi houseApi = new HouseApi();
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
    void positiveTestCase(ApplyMasterPositiveCase testCase, @WithUser(houses = {@WithHouse}) User client) {
        String token = loginApi.getTokenPhone(client);
        Integer houseId = houseApi.houseId(client, token);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create order", () -> {
            return createOrdersApi.createOrders(testCase.getCreateOrdersDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrdersResponseDto.class).getData().getOrderId();
        });
        List<Integer> masterIdList = step("Get online masters list", () -> {
            OnlineMastersResponseDto responseDto = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId), token)
                    .statusCode(200)
                    .extract()
                    .as(OnlineMastersResponseDto.class);
            // get List of master id from responseDto
            List<Integer> currentMasterIdList = new ArrayList<>();
            for (OnlineMastersResponseDto.MasterDto element : responseDto.getData()) {
                currentMasterIdList.add(element.getId());
            }
            return currentMasterIdList;
        });
        step("Is Started", () -> {
            isStartedApi.isStarted(testCase.getIsStartedDto(orderId), token)
                    .statusCode(200)
                    .extract().as(IsStartedResponseDto.class);
        });

        Integer timetableId = step("Pick master", () -> {
            return pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(PickMasterResponseDto.class).getData().getTimetableId();
        });
        step("Apply master", () -> {
            applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId, timetableId), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(ApplyMasterResponseDto.class);
            //todo add assertions
        });
    }

    @ParameterizedTest(name = "{0}")
    @EnumSource(ApplyMasterNegativeCase.class)
    @Tag(AllureTag.NEGATIVE)
    @DisplayName("Negative case:")
    void negativeTestCase(ApplyMasterNegativeCase testCase, @WithUser(houses = {@WithHouse}) User client) {
        String token = loginApi.getTokenPhone(client);
        Integer houseId = houseApi.houseId(client, token);
        step("Add equipment", () -> {
            addEquipmentApi.addEquipment(testCase.getAddEquipmentDto(), houseId, token)
                    .statusCode(200)
                    .extract().as(AddEquipmentResponseDto.class);
        });
        Integer orderId = step("Create order", () -> {
            return createOrdersApi.createOrders(testCase.getCreateOrdersDto(), token)
                    .statusCode(200)
                    .extract().as(CreateOrdersResponseDto.class).getData().getOrderId();
        });
        List<Integer> masterIdList = step("Get online masters list", () -> {
            OnlineMastersResponseDto responseDto = onlineMastersApi.getOnlineMasters(testCase.getOnlineMastersDto(orderId), token)
                    .statusCode(200)
                    .extract()
                    .as(OnlineMastersResponseDto.class);
            // get List of master id from responseDto
            List<Integer> currentMasterIdList = new ArrayList<>();
            for (OnlineMastersResponseDto.MasterDto element : responseDto.getData()) {
                currentMasterIdList.add(element.getId());
            }
            return currentMasterIdList;
        });
        step("Is Started", () -> {
            isStartedApi.isStarted(testCase.getIsStartedDto(orderId), token)
                    .statusCode(200)
                    .extract().as(IsStartedResponseDto.class);
        });
        Integer timetableId = step("Pick master", () -> {
            return pickMasterApi.pickMaster(testCase.getPickMasterDto(orderId), masterIdList.get(0), token)
                    .statusCode(200)
                    .extract().as(PickMasterResponseDto.class).getData().getTimetableId();
        });
        step("Apply master", () -> {
            applyMasterApi.applyMaster(testCase.getApplyMasterDto(orderId, timetableId), masterIdList.get(0), token)
                    .statusCode(422)
                    .extract().as(ApplyMasterResponseDto.class);
            //todo add assertions
        });
    }


}
